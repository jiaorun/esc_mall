package com.esc.mall.dto.ums.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 后台用户登录 请求参数
 * @author jiaorun
 * @date 2021/11/5 9:43
 **/
@ApiModel(value = "后台用户登录 请求参数")
@Data
public class UmsAdminLoginDTO implements Serializable {

    private static final long serialVersionUID = -547639586681711874L;

    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", example = "123456", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;
}
