package com.esc.mall.service;

import com.esc.mall.dto.pms.brand.PmsBrandAddDTO;
import com.esc.mall.dto.pms.brand.PmsBrandQueryDTO;
import com.esc.mall.dto.pms.brand.PmsBrandUpdateDTO;
import com.esc.mall.model.PmsBrand;

import java.util.List;

/**
 * 商品品牌 业务接口层
 *
 * @author jiaorun
 * @date 2021/12/8 14:32
 **/
public interface IPmsBrandService {

    /**
     * 获取所有品牌
     *
     * @return java.util.List<com.esc.mall.model.PmsBrand>
     * @author jiaorun
     * @data 2021/12/8 14:33
     */
    List<PmsBrand> getAllBrand();

    /**
     * 分页获取品牌列表
     *
     * @param dto
     * @return java.util.List<com.esc.mall.model.PmsBrand>
     * @author jiaorun
     * @data 2021/12/8 14:44
     */
    List<PmsBrand> getBrandList(PmsBrandQueryDTO dto);

    /**
     * 添加
     *
     * @param dto
     * @return int
     * @author jiaorun
     * @data 2021/12/8 17:13
     */
    int createPmsBrand(PmsBrandAddDTO dto);

    /**
     * 编辑
     *
     * @param id
     * @param dto
     * @return int
     * @author jiaorun
     * @data 2021/12/9 19:26
     */
    int updatePmsBrand(Long id, PmsBrandUpdateDTO dto);

    /**
     * 查询
     *
     * @param id
     * @return com.esc.mall.model.PmsBrand
     * @author jiaorun
     * @data 2021/12/9 19:30
     */
    PmsBrand getById(Long id);

    /**
     * 删除
     *
     * @param id
     * @return int
     * @author jiaorun
     * @data 2021/12/9 19:31
     */
    int delete(Long id);
}
