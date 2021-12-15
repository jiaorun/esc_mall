package com.esc.mall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的接收者
 *
 * @author jiaorun
 * @date 2021/12/15 11:01
 **/
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {

    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @RabbitHandler
    public void handle(Long orderId) {
        LOGGER.info("receive delay message:{}", orderId);
    }
}
