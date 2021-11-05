package com.esc.mall.dto.ums.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 后台用户注册 请求参数
 * @author jiaorun
 * @date 2021/11/4 19:54
 **/
@ApiModel(value = "后台用户注册 请求参数")
@Data
public class UmsAdminRegisterDTO implements Serializable {

    private static final long serialVersionUID = 1129312449587999619L;

    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    @NotBlank(message = "用户名不能为空")
    @Size(max = 20, message = "用户名最大长度为20位")
    private String username;

    @ApiModelProperty(value = "密码", example = "12345678", required = true)
    @NotBlank(message = "密码不能为空")
    @Size(max = 20, message = "密码最大长度为20位")
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱", example = "18727620519@163.com", required = true)
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式有误")
    private String email;

    @ApiModelProperty(value = "昵称", example = "admin")
    @Size(max = 20, message = "昵称最大长度为20位")
    private String nickName;

    @ApiModelProperty(value = "备注信息", example = "测试用户")
    private String note;
}
