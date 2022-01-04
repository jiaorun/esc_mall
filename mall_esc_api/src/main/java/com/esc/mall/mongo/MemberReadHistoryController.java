package com.esc.mall.mongo;

import com.esc.mall.api.result.MallResult;
import com.esc.mall.document.MemberReadHistory;
import com.esc.mall.exception.Asserts;
import com.esc.mall.service.mongo.IMemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员浏览商品记录 控制层
 *
 * @author jiaorun
 * @date 2021/12/14 14:53
 **/
@Api(tags = {"会员浏览商品记录 控制层"})
@RequestMapping("/v1/ums/member/readHistory")
@RestController
public class MemberReadHistoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberReadHistoryController.class);

    private final IMemberReadHistoryService memberReadHistoryService;

    @Autowired
    public MemberReadHistoryController(IMemberReadHistoryService memberReadHistoryService) {
        this.memberReadHistoryService = memberReadHistoryService;
    }

    @ApiOperation("生成浏览记录")
    @PostMapping("/create")
    public MallResult createMemberReadHistory(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count != 1) {
            LOGGER.info("create memberReadHistory failed:{}", memberReadHistory);
            Asserts.fail("生成浏览记录失败！");
        }
        return MallResult.success();
    }

    @ApiOperation("获取会员浏览记录")
    @GetMapping("/list")
    public MallResult<List<MemberReadHistory>> getReadHistoryByMemberId(@RequestParam("memberId") Long memberId) {
        List<MemberReadHistory> list = memberReadHistoryService.list(memberId);
        return MallResult.success(list);
    }

    @ApiOperation("删除浏览记录")
    @DeleteMapping("/delete")
    public MallResult delete(@RequestParam("ids") List<String> ids) {
        MallResult mallResult = new MallResult();
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            mallResult = MallResult.success();
        } else {
            LOGGER.info("delete memberReadHistory failed:{}", ids);
            Asserts.fail("删除浏览记录失败！");
        }
        return mallResult;
    }
}
