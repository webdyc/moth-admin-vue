package com.littlemoth.web.controller.req;

import com.littlemoth.system.domain.TbSysRoleMenu;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 角色-菜单对象 tb_sys_role_menu
 *
 * @author xjl
 * @date 2022-01-20
 */
public class TbSysRoleMenuReq implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 角色编号
     */
    private Long roleId;

    /**
     * 菜单编号
     */
    private Long menuId;

    public TbSysRoleMenu reqToBean() {
        TbSysRoleMenu menu = new TbSysRoleMenu();
        menu.setId(id);
        menu.setRoleId(roleId);
        menu.setMenuId(menuId);
        return menu;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuId() {
        return menuId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("roleId", getRoleId())
                .append("menuId", getMenuId())
                .toString();
    }
}
