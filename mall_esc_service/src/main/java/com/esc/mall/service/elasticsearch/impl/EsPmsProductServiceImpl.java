package com.esc.mall.service.elasticsearch.impl;

import com.esc.mall.dao.IPmsProductDao;
import com.esc.mall.product.document.EsProduct;
import com.esc.mall.product.repository.EsProductRepository;
import com.esc.mall.service.elasticsearch.IEsPmsProductService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 搜索系统 商品管理 接口实现层
 * @author jiaorun
 * @date 2021/11/9 12:07
 **/
@Service
public class EsPmsProductServiceImpl implements IEsPmsProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsPmsProductServiceImpl.class);

    private final IPmsProductDao pmsProductDao;

    private final EsProductRepository esProductRepository;

    @Autowired
    public EsPmsProductServiceImpl(IPmsProductDao pmsProductDao,
                                   EsProductRepository esProductRepository) {
        this.pmsProductDao = pmsProductDao;
        this.esProductRepository = esProductRepository;
    }

    @Override
    public int importAll() {
        List<EsProduct> esProductList = pmsProductDao.selectEsProductList(null);
        Iterable<EsProduct> esProductIterable = esProductRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct esProduct = null;
        List<EsProduct> esProductList = pmsProductDao.selectEsProductList(id);
        if (esProductList != null && !esProductList.isEmpty()) {
            esProduct = esProductRepository.save(esProductList.get(0));
        }
        return esProduct;
    }

    @Override
    public void delete(Long id) {
        esProductRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            esProductRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
