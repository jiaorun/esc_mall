package com.esc.mall.dao;

import com.esc.mall.model.UmsPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 后台用户与角色管理自定义dao
 * @author jiaorun
 * @date 2021/11/4 11:27
 **/
@Component
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有权限(包括+-权限)
     * @author jiaorun
     * @data 2021/11/4 11:29
     * @param adminId
     * @return java.util.List<com.esc.mall.model.UmsPermission>
     */
    List<UmsPermission> selectPermissionList(@Param("adminId") Long adminId);
}
