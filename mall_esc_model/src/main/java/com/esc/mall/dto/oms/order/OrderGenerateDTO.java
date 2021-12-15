package com.esc.mall.dto.oms.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单生成 请求参数
 *
 * @author jiaorun
 * @date 2021/12/15 11:10
 **/
@ApiModel(value = "订单生成 请求参数")
@Data
public class OrderGenerateDTO implements Serializable {

    private static final long serialVersionUID = -1556147333079667912L;

    @ApiModelProperty(value = "收货地址ID", example = "1001", required = true)
    private Long memberReceiveAddressId;

    @ApiModelProperty(value = "优惠券ID", example = "1002", required = true)
    private Long couponId;

    @ApiModelProperty(value = "使用的积分数", example = "100", required = true)
    private Integer useIntegration;

    @ApiModelProperty(value = "支付方式(1:支付宝 2:微信 3:钱包)", example = "1", required = true)
    private Integer payType;
}
