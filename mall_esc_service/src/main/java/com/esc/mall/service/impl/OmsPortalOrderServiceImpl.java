package com.esc.mall.service.impl;

import com.esc.mall.api.result.MallResult;
import com.esc.mall.component.CancelOrderSender;
import com.esc.mall.dto.oms.order.OrderGenerateDTO;
import com.esc.mall.service.IOmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理 业务实现层
 *
 * @author jiaorun
 * @date 2021/12/15 11:17
 **/
@Service
public class OmsPortalOrderServiceImpl implements IOmsPortalOrderService {

    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    private CancelOrderSender cancelOrderSender;

    @Autowired
    public void setCancelOrderSender(CancelOrderSender cancelOrderSender) {
        this.cancelOrderSender = cancelOrderSender;
    }

    @Override
    public MallResult generateOrder(OrderGenerateDTO dto) {
        //TODO 执行一系列下单操作
        LOGGER.info("proces geterateOrder!");
        /**
         * 下单完成后开启一个延迟消息，用于当用户没有付款时取消订单(orderId应该再下单后生成)，
         * 这里随便设置一个orderId，用于测试
         */
        Long orderId = 11L;
        sendDelayMessageCancelOrder(orderId);
        return MallResult.success("下单成功!", null);
    }

    @Override
    public void cancelOrder(Long orderId) {
        //执行一系列操作后，取消订单, 这里不实现逻辑
        LOGGER.info("process cancelOrder orderId:{}", orderId);
    }

    /**
     * 发送订单取消延迟消息
     *
     * @author jiaorun
     * @data 2021/12/15 11:42
     * @param orderId
     * @return void
     */
    private void sendDelayMessageCancelOrder(Long orderId) {
        //设置订单超时时间,这里测试设置为30s
        long delayTimes = 30 * 1000;
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
}
