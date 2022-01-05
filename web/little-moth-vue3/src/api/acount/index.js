import axios from '../http'
import md5 from 'blueimp-md5'

/**
 * @description 获取验证码
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function Send(options = {}) {
  options = Object.assign(
    {
      // 用户名
      username: '',
      // 模块类型
      type: ''
    },
    options
  )
  return axios.request({
    url: process.env.VUE_APP_BASE_API + '/send',
    method: 'post',
    data: options
  })
}

/**
 * @description 用户名检测
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function ChekcUsername(options = {}) {
  options = Object.assign(
    {
      // 用户名
      username: ''
    },
    options
  )
  return axios.request({
    url: process.env.VUE_APP_BASE_API + '/account/check',
    method: 'post',
    data: options
  })
}

/**
 * @description 账户注册
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function Register(options = {}) {
  options.password = md5(options.password)
  options = Object.assign(
    {
      // 用户名
      username: '',
      // 密码。sha1加密码
      password: '',
      // 手机验证码
      code: ''
    },
    options
  )
  return axios.request({
    url: process.env.VUE_APP_BASE_API + '/account/register',
    method: 'post',
    data: options
  })
}

/**
 * @description 账户登录
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function Login(options = {}) {
  options.password = md5(options.password)
  options = Object.assign(
    {
      // 用户名
      username: '',
      // 密码。sha1加密码
      password: ''
    },
    options
  )
  return axios.request({
    url: process.env.VUE_APP_BASE_API + '/account/login',
    method: 'post',
    data: options
  })
}
