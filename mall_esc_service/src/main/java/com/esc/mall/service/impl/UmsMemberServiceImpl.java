package com.esc.mall.service.impl;

import com.esc.mall.DateUtils;
import com.esc.mall.RedisUtils;
import com.esc.mall.api.common.Constant;
import com.esc.mall.api.result.MallResult;
import com.esc.mall.dto.umsMember.UmsMemberRegisterDTO;
import com.esc.mall.exception.Asserts;
import com.esc.mall.mapper.UmsMemberMapper;
import com.esc.mall.model.UmsMember;
import com.esc.mall.model.UmsMemberExample;
import com.esc.mall.service.IUmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Random;

/**
 * 会员登录注册 业务实现层
 * @author jiaorun
 * @date 2021/09/14 20:17
 **/
@Service
@Slf4j
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

    @Autowired
    public UmsMemberServiceImpl(RedisUtils redisUtils, UmsMemberMapper umsMemberMapper) {
        this.redisUtils = redisUtils;
        this.umsMemberMapper = umsMemberMapper;
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

    @Override
    public void register(UmsMemberRegisterDTO dto) {
        //1. 校验验证码
        if(!verifyAuthCode(dto.getTelephone(), dto.getAuthCode())) {
            Asserts.fail("验证码错误");
        }
        //2. 校验改用户是否已存在
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(dto.getUsername());
        example.or(example.createCriteria().andPhoneEqualTo(dto.getTelephone()));
        example.or(example.createCriteria().andEmailEqualTo(dto.getEmail()));
        example.or(example.createCriteria().andIdCardEqualTo(dto.getIdCard()));
        List<UmsMember> umsMembers = umsMemberMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(umsMembers)) {
            Asserts.fail("用户已存在");
        }
        //3. 执行用户添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(dto.getUsername());
        umsMember.setNickname(dto.getNickName());
        umsMember.setPassword(Base64.getEncoder().encodeToString(dto.getPassword().getBytes(StandardCharsets.UTF_8)));
        umsMember.setPhone(dto.getTelephone());
        umsMember.setEmail(dto.getEmail());
        umsMember.setIdCard(dto.getIdCard());
        umsMember.setStatus(1);
        umsMember.setMemberLevelId(DEFAULT_MEMBER_LEVEL);
        umsMember.setCreateTime(DateUtils.currentDate());
        umsMemberMapper.insert(umsMember);
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
        if(StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = (String) redisUtils.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }
}
