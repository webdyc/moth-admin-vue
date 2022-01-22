package com.littlemoth.system.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 角色-机构对象 tb_sys_role_office
 *
 * @author xjl
 * @date 2022-01-20
 */
public class TbSysRoleOfficeVo implements Serializable {

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
     * 机构编号
     */
    private Long officeId;

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

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("roleId", getRoleId())
                .append("officeId", getOfficeId())
                .toString();
    }
}
