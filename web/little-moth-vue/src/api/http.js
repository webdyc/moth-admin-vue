import axios from "axios";
import store from "@/store";
import { MessageBox, Message } from "element-ui";
import qs from "qs";
import { isPlainObject } from '@/utils'
import { getToken } from "@/utils/auth";

// 请求设置
const service = axios.create({
  baseURL: '', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 99999 // 设置请求超时时间
})

// 请求拦截器
service.interceptors.request.use((config) => {
  // headerType 1 formdata 2 json
  if (config.headerType === 1) {
    config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
    config.data = qs.stringify(config.data)
  } else {
    config.headers['Content-Type'] = 'application/json;charset=UTF-8'
    if (isPlainObject(config.data)) qs.stringify(config.data)
  }
  // 针对于部分接口，我们不携带令牌和签名信息
  const apiList = [
    '/login',
    '/captchaImage',
  ]
  // 如果请求地址去掉/api是apiList所包含的接口
  if (
    !apiList.includes(config.url.replace(process.env.VUE_APP_BASE_API, '')) &&
    getToken()
  ) {
    const time = +new Date()
    //   sign = md5(`${token}@${time}@zhufeng`)
    // 在发送请求之前做些什么Authorization
    config.headers['Authorization'] = getToken()
    config.headers['time'] = time
    // config.headers['sign'] = sign
  }
  return config
},
  (error) => {
    // 处理请求错误
    console.log(error); // for debug
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  // 业务逻辑错误
  (response) => {
    const res = response.data;
    // 如果code返回不为200，即为错误
    if (res.code !== 200) {
      Message({
        message: res.msg || "Error",
        type: "error",
        duration: 3 * 1000,
      });

      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm(
          "登录状态已过期，您可以继续留在该页面，或者重新登录",
          "系统提示",
          {
            confirmButtonText: "重新登录",
            cancelButtonText: "取消",
            type: "warning",
          }
        ).then(() => {
          store.dispatch("user/resetToken").then(() => {
            location.reload();
          });
        });
      }
      return Promise.reject(new Error(res.msg || "Error"));
    } else {
      return res;
    }
  },
  (error) => {
    console.log("err" + error); // for debug
    Message({
      message: error.msg,
      type: "error",
      duration: 3 * 1000,
    });
    return Promise.reject(error);
  }
);

export default service;
