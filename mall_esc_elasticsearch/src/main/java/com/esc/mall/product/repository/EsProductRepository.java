package com.esc.mall.product.repository;

import com.esc.mall.product.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * 商品ES操作类
 *
 * @author jiaorun
 * @date 2021/11/9 11:37
 **/
@Component
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {

    /**
     * 搜索查询
     *
     * @param name     商品名称
     * @param subTitle 副标题
     * @param keywords 关键字
     * @param pageable 分页
     * @return java.util.List<com.esc.mall.product.document.EsProduct>
     * @author jiaorun
     * @data 2021/11/11 20:01
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable pageable);
}
