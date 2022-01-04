package com.esc.mall.controller;

import com.esc.mall.api.page.CommonPage;
import com.esc.mall.api.result.MallResult;
import com.esc.mall.dto.pms.brand.PmsBrandAddDTO;
import com.esc.mall.dto.pms.brand.PmsBrandQueryDTO;
import com.esc.mall.dto.pms.brand.PmsBrandUpdateDTO;
import com.esc.mall.exception.Asserts;
import com.esc.mall.exception.ResultInfoEnum;
import com.esc.mall.model.PmsBrand;
import com.esc.mall.service.IPmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 商品品牌 控制层
 *
 * @author jiaorun
 * @date 2021/12/8 14:26
 **/
@Api(tags = {"商品品牌 控制层"})
@RequestMapping("/v1/pms/brands")
@RestController
public class PmsBrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    private final IPmsBrandService pmsBrandService;

    @Autowired
    public PmsBrandController(IPmsBrandService pmsBrandService) {
        this.pmsBrandService = pmsBrandService;
    }

    @ApiOperation("获取所有商品品牌")
    @GetMapping("/allPmsBrand")
    //TODO @PreAuthorize("hasAuthority('pms:brand:read')")
    public MallResult<List<PmsBrand>> getAllPmsBrand() {
        return MallResult.success(pmsBrandService.getAllBrand());
    }

    @ApiOperation("分页获取商品品牌列表")
    @GetMapping("/list")
    //TODO @PreAuthorize("hasauthority('pms:brand:read')")
    public MallResult<List<PmsBrand>> getPmsBrand(@ModelAttribute PmsBrandQueryDTO dto) {
        List<PmsBrand> brandList = pmsBrandService.getBrandList(dto);
        return new MallResult(ResultInfoEnum.SUCCESS, CommonPage.restPage(brandList));
    }

    @ApiOperation("添加商品品牌")
    @PostMapping("/create")
    public MallResult createPmsBrand(@RequestBody @Validated PmsBrandAddDTO dto) {
        int count = pmsBrandService.createPmsBrand(dto);
        if (count != 1) {
            LOGGER.info("createPmsBrand failed:{}", dto);
            Asserts.fail("创建商品品牌失败!");
        }
        return MallResult.success();
    }

    @ApiOperation("编辑商品品牌")
    @PatchMapping("/update/{id}")
    public MallResult updatePmsBrand(@PathVariable("id") Long id, @RequestBody @Valid PmsBrandUpdateDTO dto) {
        int count = pmsBrandService.updatePmsBrand(id, dto);
        if (count != 1) {
            LOGGER.info("updatePmsBrand failed:{}", dto);
            Asserts.fail("编辑商品品牌失败!");
        }
        return MallResult.success();
    }

    @ApiOperation("查询品牌详情")
    @GetMapping("/{id}")
    public MallResult getById(@PathVariable("id") Long id) {
        PmsBrand pmsBrand = pmsBrandService.getById(id);
        return MallResult.success(pmsBrand);
    }

    @ApiOperation("删除商品品牌")
    @DeleteMapping("/{id}")
    public MallResult delete(@PathVariable("id") Long id) {
        int count = pmsBrandService.delete(id);
        if (count != 1) {
            LOGGER.info("deletePmsBrand failed:{}", id);
            Asserts.fail("删除商品品牌失败!");
        }
        return MallResult.success();
    }
}
