package com.esc.mall.dto.oss;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OSS上传文件的回调结果
 *
 * @author jiaorun
 * @date 2021/12/15 15:39
 **/
@ApiModel(value = "OSS上传文件的回调结果")
@Data
public class OssCallbackResultDTO {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件大小")
    private String size;

    @ApiModelProperty("文件的mimeType")
    private String mimeType;

    @ApiModelProperty("图片文件的宽")
    private String width;

    @ApiModelProperty("图片文件的高")
    private String height;
}
