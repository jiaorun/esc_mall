package com.esc.mall.service.impl;

import com.esc.mall.BeanCopierUtils;
import com.esc.mall.DateUtils;
import com.esc.mall.RedisUtils;
import com.esc.mall.dto.ums.member.UmsMemberRegisterDTO;
import com.esc.mall.exception.Asserts;
import com.esc.mall.mapper.UmsMemberMapper;
import com.esc.mall.model.UmsMember;
import com.esc.mall.model.UmsMemberExample;
import com.esc.mall.security.MyPasswordEncode;
import com.esc.mall.service.IUmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 会员登录注册 业务实现层
 * @author jiaorun
 * @date 2021/09/14 20:17
 **/
@Service
@Transactional
public class UmsMemberServiceImpl implements IUmsMemberService {

    @Value("${auth_code.length}")
    private String authCodeLength;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    @Value("${default_member_level}")
    private Long DEFAULT_MEMBER_LEVEL;

    private final RedisUtils redisUtils;

    private final UmsMemberMapper umsMemberMapper;

    private final MyPasswordEncode myPasswordEncode;

    @Autowired
    public UmsMemberServiceImpl(RedisUtils redisUtils,
                                UmsMemberMapper umsMemberMapper,
                                MyPasswordEncode myPasswordEncode) {
        this.redisUtils = redisUtils;
        this.umsMemberMapper = umsMemberMapper;
        this.myPasswordEncode = myPasswordEncode;
    }

    @Override
    public String generateAuthCode(String telephone) {
        //1. 生成验证码
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Integer.parseInt(authCodeLength); i++) {
            sb.append(ThreadLocalRandom.current().nextInt(10));
        }
        //2. 验证码绑定手机号并存储到redis
        redisUtils.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisUtils.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }

    @Override
    public int register(UmsMemberRegisterDTO dto) {
        //1. 校验验证码
        if (!verifyAuthCode(dto.getPhone(), dto.getAuthCode())) {
            Asserts.fail("验证码错误！");
        }
        //2. 校验该会员是否已存在
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(dto.getUsername());
        example.or(example.createCriteria().andPhoneEqualTo(dto.getPhone()));
        example.or(example.createCriteria().andEmailEqualTo(dto.getEmail()));
        example.or(example.createCriteria().andIdCardEqualTo(dto.getIdCard()));
        List<UmsMember> umsMembers = umsMemberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            Asserts.fail("用户已存在！");
        }
        //3. 拷贝数据，执行添加操作
        UmsMember umsMember = new UmsMember();
        BeanCopierUtils.copyProperties(dto, umsMember);
        String encodePassword = myPasswordEncode.encode(dto.getPassword());
        umsMember.setPassword(encodePassword);
        umsMember.setStatus(1);
        umsMember.setMemberLevelId(DEFAULT_MEMBER_LEVEL);
        umsMember.setCreateTime(DateUtils.currentDate());
        return umsMemberMapper.insert(umsMember);
    }

    /**
     * 校验验证码
     * @author jiaorun
     * @date 2021/09/15 11:16
     * @param telephone 手机号
     * @param authCode 验证码
     * @return boolean
     */
    private boolean verifyAuthCode(String telephone, String authCode) {
        String realAuthCode = (String) redisUtils.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }
}
