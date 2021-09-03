package com.esc.mall.dto.product;

import com.esc.mall.api.page.PageReqBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品查询 请求参数
 * @author jiaorun
 * @date 2021/08/16 15:59
 **/
@ApiModel(value = "商品查询 请求参数")
@Data
public class PmsProductQueryDto extends PageReqBasic implements Serializable {

    private static final long serialVersionUID = 4647509317467542592L;

    @ApiModelProperty(value = "名称", example = "iPhone12")
    private String name;

    @ApiModelProperty(value = "货号", example = "7437799")
    private String productSn;

    @ApiModelProperty(value = "分类编号", example = "19")
    private Long productCategoryId;

    @ApiModelProperty(value = "品牌号", example = "51")
    private Long brandId;

    @ApiModelProperty(value = "上架状态：0-下架； 1-上架", example = "1")
    private Integer publishStatus;

    @ApiModelProperty(value = "审核状态：0-未审核；1-审核通过", example = "1")
    private Integer verifyStatus;
}
