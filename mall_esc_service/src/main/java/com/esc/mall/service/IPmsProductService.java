package com.esc.mall.service;

import com.esc.mall.dto.product.PmsProductQueryDTO;
import com.esc.mall.model.PmsProduct;

import java.util.List;

/**
 * 商品 业务接口层
 * @author jiaorun
 * @date 2021/08/16 17:19
 **/
public interface IPmsProductService {

    /**
     * 分页查询商品列表
     * @author jiaorun
     * @date 2021/08/16 17:21
     * @param dto
     * @return java.util.List<com.esc.mall.model.PmsProduct>
     */
    List<PmsProduct> queryPmsProductList(PmsProductQueryDTO dto);
}
