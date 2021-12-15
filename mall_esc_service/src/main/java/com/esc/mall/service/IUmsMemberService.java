package com.esc.mall.service;

import com.esc.mall.dto.ums.member.UmsMemberRegisterDTO;

/**
 * 会员登录注册 业务接口层
 *
 * @author jiaorun
 * @date 2021/09/14 20:15
 **/
public interface IUmsMemberService {

    /**
     * 生成验证码
     *
     * @param telephone 手机号
     * @return java.lang.String
     * @author jiaorun
     * @date 2021/09/15 09:51
     */
    String generateAuthCode(String telephone);

    /**
     * 会员注册
     *
     * @param dto
     * @return void
     * @author jiaorun
     * @date 2021/09/15 11:08
     */
    int register(UmsMemberRegisterDTO dto);
}
