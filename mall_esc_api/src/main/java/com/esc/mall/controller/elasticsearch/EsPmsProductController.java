package com.esc.mall.controller.elasticsearch;

import com.esc.mall.api.page.CommonPage;
import com.esc.mall.api.result.MallResult;
import com.esc.mall.exception.Asserts;
import com.esc.mall.exception.ResultInfoEnum;
import com.esc.mall.product.document.EsProduct;
import com.esc.mall.service.elasticsearch.IEsPmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索系统 商品管理 控制层
 * @author jiaorun
 * @date 2021/11/9 15:28
 **/
@Api(tags = {"搜索系统 商品管理 控制层"})
@RequestMapping("v1/es/products")
@RestController
public class EsPmsProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsPmsProductController.class);

    private final IEsPmsProductService esPmsProductService;

    @Autowired
    public EsPmsProductController(IEsPmsProductService esPmsProductService) {
        this.esPmsProductService = esPmsProductService;
    }

    @ApiOperation(value = "导入数据库所有商品到ES系统")
    @PostMapping("/importAll")
    public MallResult<Integer> importAllProduct() {
        int count = esPmsProductService.importAll();
        return MallResult.success(count);
    }

    @ApiOperation(value = "根据ID创建商品")
    @PostMapping("/create/{id}")
    public MallResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esPmsProductService.create(id);
        if (esProduct == null) {
            LOGGER.info("es createProduct failed:{}", id);
            Asserts.fail("ES系统创建商品失败！");
        }
        return MallResult.success(esProduct);
    }

    @ApiOperation(value = "根据ID删除商品")
    @DeleteMapping("/delete/{id}")
    public MallResult delete(@PathVariable("id") Long id) {
        esPmsProductService.delete(id);
        return MallResult.success();
    }

    @ApiOperation(value = "根据ID批量删除商品")
    @DeleteMapping("/delete/batch")
    public MallResult deleteBatch(@RequestParam("ids") List<Long> ids) {
        esPmsProductService.deleteBatch(ids);
        return MallResult.success();
    }

    @ApiOperation(value = "搜索")
    @GetMapping("/search")
    public MallResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductList = esPmsProductService.search(keyword, pageNum, pageSize);
        return MallResult.success(CommonPage.restPage(esProductList));
    }
}
