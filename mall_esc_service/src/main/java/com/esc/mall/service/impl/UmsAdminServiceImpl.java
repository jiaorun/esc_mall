package com.esc.mall.service.impl;

import com.esc.mall.BeanCopierUtils;
import com.esc.mall.JWTTokenUtils;
import com.esc.mall.dao.IUmsAdminRoleRelationDao;
import com.esc.mall.dto.ums.admin.UmsAdminRegisterDTO;
import com.esc.mall.exception.Asserts;
import com.esc.mall.mapper.UmsAdminMapper;
import com.esc.mall.model.UmsAdmin;
import com.esc.mall.model.UmsAdminExample;
import com.esc.mall.model.UmsPermission;
import com.esc.mall.security.MyPasswordEncode;
import com.esc.mall.service.IUmsAdminService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 后台用户登录注册 业务实现层
 *
 * @author jiaorun
 * @date 2021/11/4 10:55
 **/
@Service
@Transactional
public class UmsAdminServiceImpl implements IUmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    private final UmsAdminMapper umsAdminMapper;

    private final IUmsAdminRoleRelationDao umsAdminRoleRelationDao;

    private final MyPasswordEncode myPasswordEncode;

    private final JWTTokenUtils jwtTokenUtils;

    private UserDetailsService userDetailsService;

    @Autowired
    public UmsAdminServiceImpl(UmsAdminMapper umsAdminMapper,
                               IUmsAdminRoleRelationDao umsAdminRoleRelationDao,
                               MyPasswordEncode myPasswordEncode,
                               JWTTokenUtils jwtTokenUtils) {
        this.umsAdminMapper = umsAdminMapper;
        this.umsAdminRoleRelationDao = umsAdminRoleRelationDao;
        this.myPasswordEncode = myPasswordEncode;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if (adminList != null && !adminList.isEmpty()) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsAdminRoleRelationDao.selectPermissionList(adminId);
    }

    @Override
    public int register(UmsAdminRegisterDTO dto) {
        // 查询是否存在相同用户名
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(dto.getUsername());
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            Asserts.fail("存在相同用户名！");
        }
        UmsAdmin umsAdmin = new UmsAdmin();
        // 使用BeanCopier进行拷贝
        BeanCopierUtils.copyProperties(dto, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //将密码进行加密
        String encodePassword = myPasswordEncode.encode(dto.getPassword());
        umsAdmin.setPassword(encodePassword);
        return umsAdminMapper.insertSelective(umsAdmin);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!myPasswordEncode.matches(password, userDetails.getPassword())) {
                LOGGER.info("password failed:{}", password);
                Asserts.fail("密码不正确！");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtils.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
}
