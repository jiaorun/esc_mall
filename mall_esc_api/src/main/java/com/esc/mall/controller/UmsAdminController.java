package com.esc.mall.controller;

import com.esc.mall.api.result.MallResult;
import com.esc.mall.dto.ums.admin.UmsAdminLoginDTO;
import com.esc.mall.dto.ums.admin.UmsAdminRegisterDTO;
import com.esc.mall.exception.Asserts;
import com.esc.mall.exception.ResultInfoEnum;
import com.esc.mall.service.IUmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户登录注册 控制层
 * @author jiaorun
 * @date 2021/11/3 15:32
 **/
@Api(tags = {"后台用户登录注册 控制层"})
@RequestMapping("v1/admin")
@RestController
public class UmsAdminController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminController.class);

    private final IUmsAdminService umsAdminService;

    @Autowired
    public UmsAdminController(IUmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public MallResult register(@RequestBody UmsAdminRegisterDTO dto) {
        int count = umsAdminService.register(dto);
        if(count != 1) {
            LOGGER.info("createAdmin failed:{}", dto);
            Asserts.fail("用户注册失败！");
        }
        return MallResult.success();
    }

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public MallResult login(@RequestBody UmsAdminLoginDTO dto) {
        String token = umsAdminService.login(dto.getUsername(), dto.getPassword());
        if(token == null) {
            return MallResult.failed(ResultInfoEnum.USERNAME_PASSWORD_ERROR);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return MallResult.success(tokenMap);
    }
}
