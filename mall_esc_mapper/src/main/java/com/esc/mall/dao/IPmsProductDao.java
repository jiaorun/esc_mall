package com.esc.mall.dao;

import com.esc.mall.api.common.BaseMallDaoInterface;
import com.esc.mall.model.PmsProduct;
import com.esc.mall.product.document.EsProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品信息自定义dao
 * @author jiaorun
 * @date 2021/11/9 14:22
 **/
@Component
public interface IPmsProductDao extends BaseMallDaoInterface<PmsProduct> {

    /**
     * 搜索系统 查询所有商品
     * @author jiaorun
     * @data 2021/11/9 14:27
     * @param id
     * @return java.util.List<com.esc.mall.product.document.EsProduct>
     */
    List<EsProduct> selectEsProductList(@Param("id") Long id);
}
