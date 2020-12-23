package com.orange.mall.dao;

import com.orange.mall.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxkun
 * @creed Awaken the Giant Within
 * @description: 后台用户与角色管理自定义Dao
 * @date 2020-12-23 21:12
 */
public interface UmsAdminRoleRelationDao {

    /**
     * @describe: 获取用户所有权限(包括+-权限)
     * @param adminId
     * @date 2020/12/23 21:14
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}