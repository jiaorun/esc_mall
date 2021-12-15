package com.esc.mall.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.esc.mall.dto.oss.OssCallbackParamDTO;
import com.esc.mall.dto.oss.OssCallbackResultDTO;
import com.esc.mall.dto.oss.OssPolicyResultDTO;
import com.esc.mall.service.IOssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * OSS文件上传 业务实现层
 *
 * @author jiaorun
 * @date 2021/12/15 15:45
 **/
@Service
public class OssServiceImpl implements IOssService {

    private static Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);

    @Value("${aliyun.oss.policy.expire}")
    private int ALIYUN_OSS_EXPIRE;

    @Value("${aliyun.oss.maxSize}")
    private int ALIYUN_OSS_MAX_SIZE;

    @Value("${aliyun.oss.callback}")
    private String ALIYUN_OSS_CALLBACK;

    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKET_NAME;

    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;

    @Value("${aliyun.oss.dir.prefix}")
    private String ALIYUN_OSS_DIR_PREFIX;

    private OSS ossClient;

    @Autowired
    public void setOssClient(OSS ossClient) {
        this.ossClient = ossClient;
    }

    @Override
    public OssPolicyResultDTO policy() {
        OssPolicyResultDTO result = new OssPolicyResultDTO();
        // 存储目录
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dir = ALIYUN_OSS_DIR_PREFIX + format.format(new Date());
        // 签名有效期
        long expireEndTime = Timestamp.valueOf(LocalDateTime.now()).getTime() + ALIYUN_OSS_EXPIRE * 1000;
        Date expiration = new Date(expireEndTime);
        // 文件大小
        long maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
        // 回调
        OssCallbackParamDTO callbackParamDTO = new OssCallbackParamDTO();
        callbackParamDTO.setCallbackUrl(ALIYUN_OSS_CALLBACK);
        callbackParamDTO.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callbackParamDTO.setCallbackBodyType("application/x-www-form-urlencoded");
        //提交节点
        StringBuilder sb = new StringBuilder();
        sb.append("http://").append(ALIYUN_OSS_BUCKET_NAME).append(".").append(ALIYUN_OSS_ENDPOINT);
        try {
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callbackParamDTO).toString().getBytes(StandardCharsets.UTF_8));
            // 返回结果
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setCallback(callbackData);
            result.setHost(sb.toString());
        } catch (Exception e) {
            LOGGER.info("签名生成失败！");
        }
        return result;
    }

    @Override
    public OssCallbackResultDTO callback(HttpServletRequest request) {
        OssCallbackResultDTO result = new OssCallbackResultDTO();
        String fileName = request.getParameter("fileName");
        fileName = "http://".concat(ALIYUN_OSS_BUCKET_NAME).concat(".").concat(ALIYUN_OSS_ENDPOINT).concat("/").concat(fileName);
        result.setFileName(fileName);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setWidth(request.getParameter("width"));
        result.setHeight(request.getParameter("height"));
        return result;
    }
}
