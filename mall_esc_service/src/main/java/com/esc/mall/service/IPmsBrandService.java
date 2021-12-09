package com.esc.mall.service;

import com.esc.mall.dto.pms.brand.PmsBrandAddDTO;
import com.esc.mall.dto.pms.brand.PmsBrandQueryDTO;
import com.esc.mall.dto.pms.brand.PmsBrandUpdateDTO;
import com.esc.mall.model.PmsBrand;

import java.util.List;

/**
 * 商品品牌 业务接口层
 * @author jiaorun
 * @date 2021/12/8 14:32
 **/
public interface IPmsBrandService {

    /**
     * 获取所有品牌
     * @author jiaorun
     * @data 2021/12/8 14:33
     * @return java.util.List<com.esc.mall.model.PmsBrand>
     */
    List<PmsBrand> getAllBrand();

    /**
     * 分页获取品牌列表
     * @author jiaorun
     * @data 2021/12/8 14:44
     * @param dto
     * @return java.util.List<com.esc.mall.model.PmsBrand>
     */
    List<PmsBrand> getBrandList(PmsBrandQueryDTO dto);

    /**
     * 添加
     * @author jiaorun
     * @data 2021/12/8 17:13
     * @param dto
     * @return int
     */
    int createPmsBrand(PmsBrandAddDTO dto);

    /**
     * 编辑
     * @author jiaorun
     * @data 2021/12/9 19:26
     * @param id
     * @param dto
     * @return int
     */
    int updatePmsBrand(Long id, PmsBrandUpdateDTO dto);

    /**
     * 查询
     * @author jiaorun
     * @data 2021/12/9 19:30
     * @param id
     * @return com.esc.mall.model.PmsBrand
     */
    PmsBrand getById(Long id);

    /**
     * 删除
     * @author jiaorun
     * @data 2021/12/9 19:31
     * @param id
     * @return int
     */
    int delete(Long id);
}
