package com.esc.mall.dto.pms.brand;

import com.esc.mall.api.page.PageReqBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品品牌查询 请求参数
 *
 * @author jiaorun
 * @date 2021/12/8 14:36
 **/
@ApiModel(value = "商品品牌查询 请求参数")
@Data
public class PmsBrandQueryDTO extends PageReqBasic implements Serializable {

    private static final long serialVersionUID = -2871510279285501230L;

    @ApiModelProperty(value = "商品名称", example = "华为")
    private String name;

    @ApiModelProperty(value = "是否为品牌制造商(0:不是 1:是)", example = "0")
    private Integer factoryStatus;
}
