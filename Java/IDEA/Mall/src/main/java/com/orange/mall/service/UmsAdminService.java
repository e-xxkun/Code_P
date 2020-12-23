package com.orange.mall.service;

import com.orange.mall.mbg.model.UmsAdmin;
import com.orange.mall.mbg.model.UmsPermission;

import java.util.List;

/**
 * @author xxkun
 * @creed Awaken the Giant Within
 * @description:
 * @date 2020-12-23 16:54
 */
public interface UmsAdminService {
    UmsAdmin getAdminByUsername(String username);

    UmsAdmin register(UmsAdmin umsAdminParam);

    String login(String username, String password);

    List<UmsPermission> getPermissionList(Long id);
}
