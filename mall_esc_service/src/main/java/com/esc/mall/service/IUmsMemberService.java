package com.esc.mall.service;

import com.esc.mall.api.result.MallResult;

/**
 * 会员登录注册 业务接口层
 * @author jiaorun
 * @date 2021/09/14 20:15
 **/
public interface IUmsMemberService {

    /**
     * 生成验证码
     * @author jiaorun
     * @date 2021/09/15 09:51
     * @param telephone 手机号
     * @return java.lang.String
     */
    String generateAuthCode(String telephone);
}
