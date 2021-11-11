package com.esc.mall.service.elasticsearch;

import com.esc.mall.product.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 搜索系统 商品管理 接口层
 * @author jiaorun
 * @date 2021/11/9 12:06
 **/
public interface IEsPmsProductService {

    /**
     * 从数据库中导入所有商品到ES
     * @author jiaorun
     * @data 2021/11/9 12:09
     * @return int
     */
    int importAll();

    /**
     * 根据ID创建商品
     * @author jiaorun
     * @data 2021/11/9 16:17
     * @param id
     * @return com.esc.mall.product.document.EsProduct
     */
    EsProduct create(Long id);

    /**
     * 根据ID删除商品
     * @author jiaorun
     * @data 2021/11/9 16:18
     * @param id
     * @return void
     */
    void delete(Long id);

    /**
     * 根据ID批量删除商品
     * @author jiaorun
     * @data 2021/11/9 16:18
     * @param ids
     * @return void
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据名称或副标题或关键字搜索
     * @author jiaorun
     * @data 2021/11/9 16:26
     * @param keyword 搜索条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return java.util.List<com.esc.mall.product.document.EsProduct>
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
