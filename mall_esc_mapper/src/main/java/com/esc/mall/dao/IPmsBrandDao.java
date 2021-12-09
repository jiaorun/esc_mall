package com.esc.mall.dao;

import com.esc.mall.api.common.BaseMallDaoInterface;
import com.esc.mall.dto.pms.brand.PmsBrandQueryDTO;
import com.esc.mall.model.PmsBrand;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品品牌自定义dao
 * @author jiaorun
 * @date 2021/12/8 14:59
 **/
@Component
public interface IPmsBrandDao extends BaseMallDaoInterface<PmsBrand> {

}
