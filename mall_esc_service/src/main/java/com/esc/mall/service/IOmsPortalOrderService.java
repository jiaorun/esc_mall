package com.esc.mall.service;

import com.esc.mall.api.result.MallResult;
import com.esc.mall.dto.oms.order.OrderGenerateDTO;

/**
 * 前台订单管理 接口层
 *
 * @author jiaorun
 * @date 2021/12/15 11:04
 **/
public interface IOmsPortalOrderService {

    /**
     * 生成订单
     *
     * @author jiaorun
     * @data 2021/12/15 11:16
     * @param dto
     * @return com.esc.mall.api.result.MallResult
     */
    MallResult generateOrder(OrderGenerateDTO dto);

    /**
     * 取消单个超时订单
     *
     * @author jiaorun
     * @data 2021/12/15 11:46
     * @param orderId
     * @return void
     */
    void cancelOrder(Long orderId);
}
