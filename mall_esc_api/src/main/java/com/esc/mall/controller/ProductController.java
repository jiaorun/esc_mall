package com.esc.mall.controller;

import com.esc.mall.api.page.CommonPage;
import com.esc.mall.api.result.MallResult;
import com.esc.mall.dto.product.PmsProductQueryDTO;
import com.esc.mall.exception.ResultInfoEnum;
import com.esc.mall.model.PmsProduct;
import com.esc.mall.service.IPmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品信息 控制层
 * @author jiaorun
 * @date 2021/08/16 10:23
 **/
@Api(tags = {"商品详情 控制层"})
@RequestMapping("v1/products")
@RestController
public class ProductController {

    private final IPmsProductService pmsProductService;

    /**
     * 构造器方式注入service对象
     * @param pmsProductService
     */
    @Autowired
    public ProductController(IPmsProductService pmsProductService) {
        this.pmsProductService = pmsProductService;
    }

    @ApiOperation(value = "商品列表")
    @GetMapping(value = "/list")
    public MallResult<PmsProduct> list(PmsProductQueryDTO dto) {
        List<PmsProduct> pmsProductList = pmsProductService.queryPmsProductList(dto);
        return new MallResult(ResultInfoEnum.SUCCESS, CommonPage.restPage(pmsProductList));
    }
}
