package com.esc.mall.controller;

import com.esc.mall.api.result.MallResult;
import com.esc.mall.dto.oss.OssCallbackResultDTO;
import com.esc.mall.dto.oss.OssPolicyResultDTO;
import com.esc.mall.service.IOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * OSS文件上传 控制层
 *
 * @author jiaorun
 * @date 2021/12/15 16:38
 **/
@Api(tags = {"OSS文件上传 控制层"})
@RequestMapping("v1/aliyun/oss")
@RestController
public class OssController {

    private IOssService ossService;

    @Autowired
    public OssController(IOssService ossService) {
        this.ossService = ossService;
    }

    @ApiOperation("OSS文件上传生成签名")
    @GetMapping("/policy")
    public MallResult<OssPolicyResultDTO> policy() {
        OssPolicyResultDTO result = ossService.policy();
        return MallResult.success(result);
    }

    @ApiOperation("OSS文件上传成功回调")
    @PostMapping("/callback")
    public MallResult<OssCallbackResultDTO> callback(HttpServletRequest request) {
        OssCallbackResultDTO result = ossService.callback(request);
        return MallResult.success(result);
    }
}
