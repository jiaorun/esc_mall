package com.esc.mall.dto.ums.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 会员注册 请求参数
 * @author jiaorun
 * @date 2021/09/15 10:48
 **/
@ApiModel(value = "会员注册 请求参数")
@Data
public class UmsMemberRegisterDTO implements Serializable {

    private static final long serialVersionUID = 5903768192542737685L;

    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    @NotBlank(message = "用户名不能为空")
    @Size(max = 20, message = "用户名最大长度为20位")
    private String username;

    @ApiModelProperty(value = "昵称", example = "润", required = true)
    @NotBlank(message = "昵称不能为空")
    @Size(max = 15, message = "昵称最大长度为15位")
    private String nickName;

    @ApiModelProperty(value = "密码", example = "12345678", required = true)
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, message = "密码最少长度为8位")
    private String password;

    @ApiModelProperty(value = "手机号", example = "18727620519", required = true)
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String telephone;

    @ApiModelProperty(value = "邮箱", example = "18727620519@163.com", required = true)
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式有误")
    private String email;

    @ApiModelProperty(value = "身份证", example = "411322199508163015", required = true)
    @NotBlank(message = "身份证不能为空")
    @Pattern(regexp = "(^\\d{18}$)|(^\\d{15}$)", message = "身份证格式有误")
    private String idCard;

    @ApiModelProperty(value = "验证码", example = "564547", required = true)
    @NotBlank(message = "验证码不能为空")
    @Length(min = 6, max = 6, message = "验证码为6位数字")
    private String authCode;
}
