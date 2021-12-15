package com.esc.mall.service;

import com.esc.mall.dto.ums.admin.UmsAdminRegisterDTO;
import com.esc.mall.model.UmsAdmin;
import com.esc.mall.model.UmsPermission;

import java.util.List;

/**
 * 后台用户登录注册 业务接口层
 *
 * @author jiaorun
 * @date 2021/11/4 10:53
 **/
public interface IUmsAdminService {

    /**
     * 根据用户名查询后台用户
     *
     * @param username
     * @return com.esc.mall.model.UmsAdmin
     * @author jiaorun
     * @data 2021/11/4 10:54
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户所有权限(包括角色权限和+-权限)
     *
     * @param adminId
     * @return java.util.List<com.esc.mall.model.UmsPermission>
     * @author jiaorun
     * @data 2021/11/4 14:38
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 注册
     *
     * @param dto
     * @return int
     * @author jiaorun
     * @data 2021/11/4 20:13
     */
    int register(UmsAdminRegisterDTO dto);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return java.lang.String
     * @author jiaorun
     * @data 2021/11/5 9:26
     */
    String login(String username, String password);
}
