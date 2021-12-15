package com.esc.mall.dto.pms.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 商品品牌编辑 请求参数
 *
 * @author jiaorun
 * @date 2021/12/9 19:25
 **/
@ApiModel(value = "商品品牌编辑 请求参数")
@Data
public class PmsBrandUpdateDTO implements Serializable {

    private static final long serialVersionUID = 688726656235562263L;

    @ApiModelProperty(value = "种类", example = "100")
    private Integer sort;

    @ApiModelProperty(value = "是否为品牌制造商(0:不是 1:是)", example = "0")
    private Integer factoryStatus;

    @ApiModelProperty(value = "展示状态", example = "1")
    private Integer showStatus;

    @ApiModelProperty(value = "商品数量", example = "100")
    private Integer productCount;

    @ApiModelProperty(value = "产品评论熟练", example = "10")
    private Integer productCommentCount;

    @ApiModelProperty(value = "品牌Logo", example = "https://gimg2.baidu.com/image_search/744ebf81a4c7044c402d92a6059242da682.jpg")
    @Size(max = 255, message = "品牌Logo最大长度为255")
    private String logo;

    @ApiModelProperty(value = "专区大图", example = "https://gimg2.baidu.com/image_search/q=a80&n=0&g=0n&fmt=jpeg")
    @Size(max = 255, message = "专区大图最大长度为255")
    private String bigPic;

    @ApiModelProperty(value = "品牌故事", example = "HUAWEI")
    private String brandStory;
}
