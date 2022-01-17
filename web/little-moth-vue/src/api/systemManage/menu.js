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
 * @description 获取验证码
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */
export function sendCode () {
    return request({
        url: process.env.VUE_APP_BASE_API + "/captchaImage",
        method: "get",
        // 如果后端直接返回图片流的话，放开下面的注释
        // responseType: "blob",
    });
}

/**
 * @description 获取路由权限
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */
export function getRole (options = {}) {
    return request({
        headerType: 1,
        url: process.env.VUE_APP_BASE_API + "/auth/permission",
        method: "post",
        data: options,
    });
}

/**
 * @description de
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function logout () {
    return request({
        url: "/logout",
        method: "post",
    });
}
