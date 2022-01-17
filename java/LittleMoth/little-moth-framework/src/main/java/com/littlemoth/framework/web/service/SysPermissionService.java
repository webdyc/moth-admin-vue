package com.littlemoth.framework.web.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.littlemoth.common.core.domain.model.TbSysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.littlemoth.common.core.domain.entity.SysUser;
import com.littlemoth.system.service.ISysMenuService;
import com.littlemoth.system.service.ISysRoleService;

/**
 * 用户权限处理
 * 
 * @author xusan
 */
@Component
public class SysPermissionService
{
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取角色数据权限
     * 
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(TbSysUser user)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * 
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(TbSysUser user)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (!Objects.nonNull(user)){
            return perms;
        }
        else if (user.isAdmin())
        {
            perms.add("*:*:*");
        }
        else
        {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getId()));
        }
        return perms;
    }
}
