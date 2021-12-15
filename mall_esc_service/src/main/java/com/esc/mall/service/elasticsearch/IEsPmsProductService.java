package com.esc.mall.service.elasticsearch;

import com.esc.mall.product.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 搜索系统 商品管理 接口层
 *
 * @author jiaorun
 * @date 2021/11/9 12:06
 **/
public interface IEsPmsProductService {

    /**
     * 从数据库中导入所有商品到ES
     *
     * @return int
     * @author jiaorun
     * @data 2021/11/9 12:09
     */
    int importAll();

    /**
     * 根据ID创建商品
     *
     * @param id
     * @return com.esc.mall.product.document.EsProduct
     * @author jiaorun
     * @data 2021/11/9 16:17
     */
    EsProduct create(Long id);

    /**
     * 根据ID删除商品
     *
     * @param id
     * @return void
     * @author jiaorun
     * @data 2021/11/9 16:18
     */
    void delete(Long id);

    /**
     * 根据ID批量删除商品
     *
     * @param ids
     * @return void
     * @author jiaorun
     * @data 2021/11/9 16:18
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据名称或副标题或关键字搜索
     *
     * @param keyword  搜索条件
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return java.util.List<com.esc.mall.product.document.EsProduct>
     * @author jiaorun
     * @data 2021/11/9 16:26
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
