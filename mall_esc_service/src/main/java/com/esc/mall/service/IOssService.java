package com.esc.mall.service;

import com.esc.mall.dto.oss.OssCallbackResultDTO;
import com.esc.mall.dto.oss.OssPolicyResultDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * OSS文件上传 接口层
 *
 * @author jiaorun
 * @date 2021/12/15 15:43
 **/
public interface IOssService {

    /**
     * OSS上传策略生成
     *
     * @author jiaorun
     * @data 2021/12/15 15:43
     * @return com.esc.mall.dto.oss.OssPolicyResultDTO
     */
    OssPolicyResultDTO policy();

    /**
     * OSS上传成功回调
     *
     * @author jiaorun
     * @data 2021/12/15 15:44
     * @param request
     * @return com.esc.mall.dto.oss.OssCallbackResultDTO
     */
    OssCallbackResultDTO callback(HttpServletRequest request);
}
