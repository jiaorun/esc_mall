package com.esc.mall.controller;

import com.esc.mall.api.result.MallResult;
import com.esc.mall.dto.oms.order.OrderGenerateDTO;
import com.esc.mall.service.IOmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台订单管理 控制层
 *
 * @author jiaorun
 * @date 2021/12/15 11:52
 **/
@Api(tags = {"前台订单管理 控制层"})
@RequestMapping("v1/oms/order")
@RestController
public class OmsPortalOrderController {

    private final IOmsPortalOrderService omsPortalOrderService;

    @Autowired
    public OmsPortalOrderController(IOmsPortalOrderService omsPortalOrderService) {
        this.omsPortalOrderService = omsPortalOrderService;
    }

    @ApiOperation("生成订单")
    @PostMapping("/generate")
    public MallResult generateOrder(@RequestBody OrderGenerateDTO dto) {
        return omsPortalOrderService.generateOrder(dto);
    }
}
