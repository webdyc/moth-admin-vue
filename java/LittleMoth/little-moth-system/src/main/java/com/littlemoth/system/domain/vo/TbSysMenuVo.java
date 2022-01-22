package com.littlemoth.system.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单对象 tb_sys_menu
 *
 * @author xjl
 * @date 2022-01-12
 */
public class TbSysMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级编号
     */
    private String parentId;


    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 面包屑
     */
    private Integer breadcrumb;

    /**
     * 页面是否缓存
     */
    private Integer keepAlive;

    /**
     * 页面标题
     */
    private String title;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    /**
     * 链接
     */
    private String href;

    /**
     * 目标
     */
    private String target;


    /**
     * 图标
     */
    private String icon;

    /**
     * 是否在菜单中显示 是否在菜单中显示0是不展示1是展示
     */
    private String hidden;

    /**
     * 权限标识
     */
    private String permis;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 更新者
     */
    private Long updateUser;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记 默认0  是否删除  0否  1是
     */
    private Integer isDel;

    /**
     * 子菜单
     */
    private List<TbSysMenuVo> children = new ArrayList<TbSysMenuVo>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setBreadcrumb(Integer breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public Integer getBreadcrumb() {
        return breadcrumb;
    }

    public void setKeepAlive(Integer keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Integer getKeepAlive() {
        return keepAlive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComponent() {
        return component;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public String getPermis() {
        return permis;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getCreator() {
        return creator;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getIsDel() {
        return isDel;
    }


    public List<TbSysMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<TbSysMenuVo> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("parentId", getParentId())
                .append("path", getPath())
                .append("component", getComponent())
                .append("sort", getSort())
                .append("menuType", getMenuType())
                .append("href", getHref())
                .append("target", getTarget())
                .append("icon", getIcon())
                .append("hidden", getHidden())
                .append("permis", getPermis())
                .append("creator", getCreator())
                .append("updateUser", getUpdateUser())
                .append("remarks", getRemarks())
                .append("isDel", getIsDel())
                .toString();
    }
}
