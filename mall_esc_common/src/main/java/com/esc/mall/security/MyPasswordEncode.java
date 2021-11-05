package com.esc.mall.security;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义密码加密和比较方式
 * @author jiaorun
 * @date 2021/11/5 15:44
 **/
@Component
public class MyPasswordEncode implements PasswordEncoder {

    @Value("${password_salt}")
    private String PASSWORD_SALT;

    @Value("${password_encode_num}")
    private int PASSWORD_ENCODE_NUM;

    @Override
    public String encode(CharSequence charSequence) {
        String encodePassword = new SimpleHash("MD5", charSequence.toString(), PASSWORD_SALT, PASSWORD_ENCODE_NUM).toString();
        return encodePassword;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
