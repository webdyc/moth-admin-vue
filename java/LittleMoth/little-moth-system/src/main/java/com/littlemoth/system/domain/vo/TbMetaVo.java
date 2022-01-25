package com.littlemoth.system.domain.vo;


import java.io.Serializable;

/**
 * 路由显示信息
 *
 * @author xiusan
 */
public class TbMetaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 路由权限
     */
    private String role[];

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;


    /**
     * 面包屑
     */
    private boolean breadcrumb;

    /**
     * 页面是否缓存
     */
    private boolean keepAlive;


    public TbMetaVo(String title, String icon, Boolean breadcrumb, Boolean keepAlive, String[] role) {
        this.title = title;
        this.icon = icon;
        this.role = role;
        this.breadcrumb = breadcrumb;
        this.keepAlive = keepAlive;

    }

    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public boolean isBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(boolean breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
