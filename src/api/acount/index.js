import request from "@/api/request";

import { encrypt } from "@/utils/index";
/**
 * @description 账户登录
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export async function login (options = {}) {
  let data = JSON.stringify(options);
  let res = await encrypt(data)
  // return
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/login",
    method: "post",
    data: { data: res },
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
    url: process.env.VUE_APP_BASE_API + "/5g-auth/captchaImage",
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
export function getRole () {
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/getRouters",
    method: "get",
  });
}

/**
 * @description 获取用户信息
 * @param da，taForm
 * @returns {*}
 * @author webdyc
 */
export function userMessage () {
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/user/myMessage",
    method: "get",
  });
}

/**
 * @description 登出
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function logout (tokenId) {
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/monitor/online/" + tokenId,
    method: "get",
  });
}

export function switchSubScript (options) {
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/setChatBotId",
    method: "get",
    params: options,
  });
}

//获取手机验证码
export async function getPhoneCode (options) {
  let data = JSON.stringify(options);
  let res = await encrypt(data)
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/auth/user/getMessageCode",
    method: "post",
    data: { data: res },
  });
}
//修改密码
export async function usersUpdatePwd (options) {
  let data = JSON.stringify(options);
  let res = await encrypt(data)
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/auth/user/usersUpdatePwd",
    method: "post",
    data: { data: res },
  });
}
// SIM 卡登录
export async function simLogin (options) {
  let data = JSON.parse(JSON.stringify(options));
  data.phone = await encrypt(data.phone)
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/simLogin/",
    method: "get",
    params: data,
  });
}
// SIM 卡 登录回调
export function simLogin_queryResult (options) {
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/simLogin/queryResult/",
    method: "get",
    params: options,
    loading: false
  });
}
// 登录方法的加密信息Key
export function loginKey (options) {
  return request({
    url: process.env.VUE_APP_BASE_API + "/5g-auth/loginKey",
    method: "get",
    params: options,
  });
}
