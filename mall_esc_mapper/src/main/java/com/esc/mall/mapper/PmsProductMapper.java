package com.esc.mall.mapper;

import com.esc.mall.api.common.BaseMallDaoInterface;
import com.esc.mall.model.PmsProduct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jiaorun
 * @date 2021/08/16 16:30
 **/
@Mapper
@Component
public interface PmsProductMapper extends BaseMallDaoInterface<PmsProduct> {
    
    int deleteByPrimaryKey(Long id);

    int insert(PmsProduct record);

    int insertSelective(PmsProduct record);

    PmsProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProduct record);

    int updateByPrimaryKeyWithBLOBs(PmsProduct record);

    int updateByPrimaryKey(PmsProduct record);
}