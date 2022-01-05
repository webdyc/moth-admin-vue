import request from "@/utils/request";

/**
 * @description 账户登录
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */
export function login(options = {}) {
  options = Object.assign(
    {
      // 用户名
      username: "",
      // 密码。sha1加密码
      password: "",
    },
    options
  );
  return request({
    url: process.env.VUE_APP_BASE_API + "/user/login",
    method: "post",
    data: options,
  });
}

// 获取路由列表
export function getRole() {
  return request({
    url: process.env.VUE_APP_BASE_API + "/user/role",
    method: "get",
  });
}

export function logout() {
  return request({
    url: process.env.VUE_APP_BASE_API + "/user/logout",
    method: "post",
  });
}
