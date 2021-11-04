package com.esc.mall.service.impl;

import com.esc.mall.dao.UmsAdminRoleRelationDao;
import com.esc.mall.mapper.UmsAdminMapper;
import com.esc.mall.model.UmsAdmin;
import com.esc.mall.model.UmsAdminExample;
import com.esc.mall.model.UmsPermission;
import com.esc.mall.service.IUmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Permission;
import java.util.List;

/**
 * 后台用户登录注册 业务实现层
 * @author jiaorun
 * @date 2021/11/4 10:55
 **/
@Service
@Transactional
public class UmsAdminServiceImpl implements IUmsAdminService {

    private final UmsAdminMapper umsAdminMapper;

    private final UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Autowired
    public UmsAdminServiceImpl(UmsAdminMapper umsAdminMapper, UmsAdminRoleRelationDao umsAdminRoleRelationDao) {
        this.umsAdminMapper = umsAdminMapper;
        this.umsAdminRoleRelationDao = umsAdminRoleRelationDao;
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if(adminList != null && !adminList.isEmpty()) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsAdminRoleRelationDao.selectPermissionList(adminId);
    }
}
