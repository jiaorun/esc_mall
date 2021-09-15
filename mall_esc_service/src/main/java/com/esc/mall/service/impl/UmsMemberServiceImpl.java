package com.esc.mall.service.impl;

import com.esc.mall.RedisUtils;
import com.esc.mall.api.common.Constant;
import com.esc.mall.api.result.MallResult;
import com.esc.mall.service.IUmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 会员登录注册 业务实现层
 * @author jiaorun
 * @date 2021/09/14 20:17
 **/
@Service
public class UmsMemberServiceImpl implements IUmsMemberService {

    @Value("${auth_code.length}")
    private String authCodeLength;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    private final RedisUtils redisUtils;

    @Autowired
    public UmsMemberServiceImpl(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public String generateAuthCode(String telephone) {
        //1. 生成验证码
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < Integer.parseInt(authCodeLength); i++) {
            sb.append(random.nextInt(10));
        }
        //2. 验证码绑定手机号并存储到redis
        redisUtils.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisUtils.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }
}
