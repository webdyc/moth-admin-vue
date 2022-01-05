import axios from 'axios'
import qs from 'qs'
import { isPlainObject } from '@/utils'
import { message, Modal } from 'ant-design-vue'
import { getToken } from '@/utils/auth'
import store from '@/store'

// 请求设置
const service = axios.create({
  baseURL: '', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // 设置请求超时时间
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
    '/send',
    '/account/check',
    '/account/register',
    '/account/login'
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
})
// 响应拦截器
service.interceptors.response.use(
  (response) => {
    const res = response.data
    // 如果code为 0 代表成功
    if (res.error_code !== 0) {
      message.error(res.msg)
      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (
        res.error_code === 1010 ||
        res.error_code === 50008 ||
        res.error_code === 50012 ||
        res.error_code === 50014
      ) {
        Modal.warning({
          title: '登出通知',
          content: '您已经被登出, 请重新登录~',
          onOk() {
            store.dispatch('user/resetToken')
            // 这个系统再切换用户时，路由不会动态更新。所以需要在登出函数中加上location.reload();刷新浏览器。
            location.reload()
          }
        })
      }
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  },
  (error) => {
    const data = JSON.parse(error.request.response)
    switch (data.error_code) {
      case 1010:
        Modal.warning({
          title: '登出通知',
          content: data.msg,
          okText: '重新登录',
          onOk() {
            store.dispatch('user/resetToken').then(() => {
              location.reload()
            })
          }
        })
        break
      default:
        message.error(data.msg)
    }
    return Promise.reject(error)
  }
)
export default service
