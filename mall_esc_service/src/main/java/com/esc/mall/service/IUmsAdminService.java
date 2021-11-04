package com.esc.mall.service;

import com.esc.mall.model.UmsAdmin;
import com.esc.mall.model.UmsPermission;

import java.util.List;

/**
 * 后台用户登录注册 业务接口层
 * @author jiaorun
 * @date 2021/11/4 10:53
 **/
public interface IUmsAdminService {

    /**
     * 根据用户名查询后台用户
     * @author jiaorun
     * @data 2021/11/4 10:54
     * @param username
     * @return com.esc.mall.model.UmsAdmin
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户所有权限(包括角色权限和+-权限)
     * @author jiaorun
     * @data 2021/11/4 14:38
     * @param adminId
     * @return java.util.List<com.esc.mall.model.UmsPermission>
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
