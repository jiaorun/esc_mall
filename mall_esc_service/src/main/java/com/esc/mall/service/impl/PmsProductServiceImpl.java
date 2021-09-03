package com.esc.mall.service.impl;

import com.esc.mall.dto.product.PmsProductQueryDto;
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

    private PmsProductMapper pmsProductMapper;

    /**
     * 构造器方式注入mapper对象
     * @param pmsProductMapper
     */
    @Autowired
    public PmsProductServiceImpl(PmsProductMapper pmsProductMapper) {
        this.pmsProductMapper = pmsProductMapper;
    }

    @Override
    public List<PmsProduct> queryPmsProductList(PmsProductQueryDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return pmsProductMapper.selectListByKeyword(dto);
    }
}
