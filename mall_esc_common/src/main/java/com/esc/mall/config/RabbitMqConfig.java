package com.esc.mall.config;

import com.esc.mall.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author jiaorun
 * @date 2021/12/15 10:25
 **/
@Configuration
public class RabbitMqConfig {

    /**
     * 订单消息实际消费队列所绑定的交换机
     *
     * @return org.springframework.amqp.core.DirectExchange
     * @author jiaorun
     * @data 2021/12/15 10:30
     */
    @Bean
    DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单延迟队列所绑定的交换机
     *
     * @return org.springframework.amqp.core.DirectExchange
     * @author jiaorun
     * @data 2021/12/15 10:31
     */
    @Bean
    DirectExchange orderTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单实际消费队列
     *
     * @return org.springframework.amqp.core.Queue
     * @author jiaorun
     * @data 2021/12/15 10:33
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }

    /**
     * 订单延迟队列 (死信队列)
     *
     * @return org.springframework.amqp.core.Queue
     * @author jiaorun
     * @data 2021/12/15 10:34
     */
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
                //到期后转发的交换机
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                //到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouterKey())
                .build();
    }

    /**
     * 将订单队列绑定到交换机
     *
     * @return org.springframework.amqp.core.Binding
     * @author jiaorun
     * @data 2021/12/15 10:37
     */
    @Bean
    Binding orderBinding(DirectExchange orderDirect, Queue orderQueue) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouterKey());
    }

    /**
     * 将订单延迟队列绑定到交换机
     *
     * @return org.springframework.amqp.core.Binding
     * @author jiaorun
     * @data 2021/12/15 10:39
     */
    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlDirect, Queue orderTtlQueue) {
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlDirect)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouterKey());
    }
}
