package com.esc.mall.dto;

import lombok.Getter;

/**
 * 消息队列枚举配置
 *
 * @author jiaorun
 * @date 2021/12/15 10:19
 **/
@Getter
public enum QueueEnum {

    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cacel"),

    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl", "mall_order.cancel.ttl", "mall.order.cancel.ttl");

    /**
     * 交换机名称
     */
    private String exchange;

    /**
     * 队列名称
     */
    private String name;

    /**
     * 路由键
     */
    private String routerKey;

    QueueEnum(String exchange, String name, String routerKey) {
        this.exchange = exchange;
        this.name = name;
        this.routerKey = routerKey;
    }
}
