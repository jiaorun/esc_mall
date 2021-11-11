package com.esc.mall.service.impl;

import com.esc.mall.dao.IPmsProductDao;
import com.esc.mall.dto.product.PmsProductQueryDTO;
import com.esc.mall.mapper.PmsProductMapper;
import com.esc.mall.model.PmsProduct;
import com.esc.mall.service.IPmsProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品 业务实现层
 * @author jiaorun
 * @date 2021/08/16 17:21
 **/
@Service
public class PmsProductServiceImpl implements IPmsProductService {

    private final IPmsProductDao pmsProductDao;

    /**
     * 构造器方式注入mapper对象
     * @param pmsProductDao
     */
    @Autowired
    public PmsProductServiceImpl(IPmsProductDao pmsProductDao) {
        this.pmsProductDao = pmsProductDao;
    }

    @Override
    public List<PmsProduct> queryPmsProductList(PmsProductQueryDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return pmsProductDao.selectListByKeyword(dto);
    }
}
