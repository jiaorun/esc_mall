package com.esc.mall.controller;

import com.esc.mall.api.result.MallResult;
import com.esc.mall.dto.ums.member.UmsMemberRegisterDTO;
import com.esc.mall.exception.Asserts;
import com.esc.mall.service.IUmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 会员登录注册 控制层
 * @author jiaorun
 * @date 2021/09/14 20:13
 **/
@Api(tags = {"会员登录注册 控制层"})
@RequestMapping("v1/ums/members")
@RestController
public class UmsMemberController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberController.class);

    private final IUmsMemberService umsMemberService;

    @Autowired
    public UmsMemberController(IUmsMemberService umsMemberService) {
        this.umsMemberService = umsMemberService;
    }

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public MallResult getAuthCode(@RequestParam String telephone) {
        String authCode = umsMemberService.generateAuthCode(telephone);
        if (StringUtils.isBlank(authCode)) {
            LOGGER.info("authCode failed:{}", telephone);
            Asserts.fail("验证码获取失败，请重试！");
        }
        return MallResult.success("验证码获取成功！", authCode);
    }

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public MallResult register(@RequestBody @Valid UmsMemberRegisterDTO dto) {
        int count = umsMemberService.register(dto);
        if (count != 1) {
            Asserts.fail("会员注册失败！");
        }
        return MallResult.success("会员注册成功", null);
    }
}
