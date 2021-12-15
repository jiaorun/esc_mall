package com.esc.mall.service.impl;

import com.esc.mall.BeanCopierUtils;
import com.esc.mall.dao.IPmsBrandDao;
import com.esc.mall.dto.pms.brand.PmsBrandAddDTO;
import com.esc.mall.dto.pms.brand.PmsBrandQueryDTO;
import com.esc.mall.dto.pms.brand.PmsBrandUpdateDTO;
import com.esc.mall.exception.Asserts;
import com.esc.mall.mapper.PmsBrandMapper;
import com.esc.mall.model.PmsBrand;
import com.esc.mall.model.PmsBrandExample;
import com.esc.mall.service.IPmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 商品品牌 业务实现层
 *
 * @author jiaorun
 * @date 2021/12/8 14:44
 **/
@Service
@Transactional
public class PmsBrandServiceImpl implements IPmsBrandService {

    private final PmsBrandMapper pmsBrandMapper;

    private final IPmsBrandDao pmsBrandDao;

    @Autowired
    public PmsBrandServiceImpl(PmsBrandMapper pmsBrandMapper, IPmsBrandDao pmsBrandDao) {
        this.pmsBrandMapper = pmsBrandMapper;
        this.pmsBrandDao = pmsBrandDao;
    }

    @Override
    public List<PmsBrand> getAllBrand() {
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public List<PmsBrand> getBrandList(PmsBrandQueryDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return pmsBrandDao.selectListByKeyword(dto);
    }

    @Override
    public int createPmsBrand(PmsBrandAddDTO dto) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanCopierUtils.copyProperties(dto, pmsBrand);
        // 校验是否存在相同品牌名称
        PmsBrandExample example = new PmsBrandExample();
        example.createCriteria().andNameEqualTo(dto.getName());
        List<PmsBrand> pmsBrandList = pmsBrandMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(pmsBrandList)) {
            Asserts.fail("存在相同品牌名称！");
        }
        return pmsBrandMapper.insertSelective(pmsBrand);
    }

    @Override
    public int updatePmsBrand(Long id, PmsBrandUpdateDTO dto) {
        // 验证商品是否存在
        PmsBrand pmsBrand = pmsBrandMapper.selectByPrimaryKey(id);
        if (pmsBrand == null) {
            Asserts.fail("该商品品牌不存在，编辑失败！");
        }
        PmsBrand param = new PmsBrand();
        BeanCopierUtils.copyProperties(dto, param);
        param.setId(id);
        return pmsBrandMapper.updateByPrimaryKeySelective(param);
    }

    @Override
    public PmsBrand getById(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }
}
