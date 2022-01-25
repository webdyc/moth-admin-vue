import request from "@/api/request";

/**  
 * @description 获取菜单下拉树列表
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function menu_treeselect () {
    return request({
        url: process.env.VUE_APP_BASE_API + "/menu/treeselect",
        method: "get",
    });
}

/**
 * @description 菜单 —— 列表查询
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function menu_list (options = {}) {
    options = Object.assign(
        {
            // 每页条数
            pageSize: 10,
            // 当前页码
            pageNum: 1,
        },
        options
    );
    return request({
        url: process.env.VUE_APP_BASE_API + "/menu/listTree",
        method: "get",
        params: options,
    });
}

/**
 * @description 菜单新增
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function menu_create (options = {}) {
    options = Object.assign(
        {
            // 上级菜单
            parentId: null,
            // 菜单类型
            menuType: "M",
            // 排序
            sort: 1,
            // 是否隐藏路由
            hidden: false,
            // 路由地址
            path: "",
            // vue文件路径
            component: "",
            // 路由重定向
            redirect: "",
            // 路由名称父级可不填
            name: "",
            // 页面权限
            role: "",
            // 页面标题
            title: "",
            //  页面图标
            icon: "",
            //  如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)
            breadcrumb: true,
            //  页面是否缓存
            keepAlive: false,
        },
        options
    );
    return request({
        url: process.env.VUE_APP_BASE_API + "/menu/add",
        method: "post",
        data: options,
    });
}

/**
 * @description 菜单详情
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function menu_info (id) {
    return request({
        url: process.env.VUE_APP_BASE_API + "/menu/id/" + id,
        method: "get",
    });
}

/**
 * @description 菜单编辑
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function menu_update (options = {}) {
    options = Object.assign(
        {
            // 上级菜单
            parentId: null,
            // 菜单类型
            menuType: "M",
            // 排序
            sort: 1,
            // 是否隐藏路由
            hidden: false,
            // 路由地址
            path: "",
            // vue文件路径
            component: "",
            // 路由重定向
            redirect: "",
            // 路由名称父级可不填
            name: "",
            // 页面权限
            role: "",
            // 页面标题
            title: "",
            //  页面图标
            icon: "",
            //  如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)
            breadcrumb: true,
            //  页面是否缓存
            keepAlive: false,
        },
        options
    );
    return request({
        url: process.env.VUE_APP_BASE_API + "/menu/edit",
        method: "post",
        data: options,
    });
}

/**
 * @description 菜单删除
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function menu_remove (id) {
    return request({
        url: process.env.VUE_APP_BASE_API + "/menu/delete/" + id,
        method: "get"
    });
}

/**
 * @description 菜单状态更新
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function menu_status (options = {}) {
    options = Object.assign(
        {
            // 菜单名
            username: "",
            // 密码。sha1加密码
            password: "",
        },
        options
    );
    return request({
        url: process.env.VUE_APP_BASE_API + "/user/status",
        method: "post",
        data: options,
    });
}
