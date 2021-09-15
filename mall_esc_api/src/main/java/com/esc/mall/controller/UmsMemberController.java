package com.esc.mall.controller;

import com.esc.mall.api.result.MallResult;
import com.esc.mall.service.IUmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员登录注册 控制层
 * @author jiaorun
 * @date 2021/09/14 20:13
 **/
@Api(tags = {"会员登录注册 控制层"})
@RequestMapping("v1/sso")
@RestController
public class UmsMemberController {

    private final IUmsMemberService umsMemberService;

    @Autowired
    public UmsMemberController(IUmsMemberService umsMemberService) {
        this.umsMemberService = umsMemberService;
    }

    @ApiOperation(value = "获取验证码")
    @GetMapping("/getAuthCode")
    public MallResult getAuthCode(@RequestParam String telephone) {
        String authCode = umsMemberService.generateAuthCode(telephone);
        return MallResult.success("验证码获取成功！", authCode);
    }


}
