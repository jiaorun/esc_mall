package com.esc.mall.dto.oss;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取OSS上传文件授权 返回参数
 *
 * @author jiaorun
 * @date 2021/12/15 15:29
 **/
@ApiModel(value = "获取OSS上传文件授权 返回参数")
@Data
public class OssPolicyResultDTO {

    @ApiModelProperty("用户表单上传的策略，经过base64过的字符串")
    private String policy;

    @ApiModelProperty("对policy签名后的字符串")
    private String signature;

    @ApiModelProperty("上传文件夹路径前缀")
    private String dir;

    @ApiModelProperty("oss对外服务的访问域名")
    private String host;

    @ApiModelProperty("上传成功后的回调设置")
    private String callback;
}
