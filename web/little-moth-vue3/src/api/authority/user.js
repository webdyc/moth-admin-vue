import axios from '../http'
import md5 from 'blueimp-md5'

/**
 * @description 用户列表
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function user_list(options = {}) {
  options = Object.assign(
    {
      // 用户名
      username: '',
      // 手机号
      phone: '',
      // 真实姓名
      truename: '',
      // 禁启用（true：启用，false：禁用）
      status: '',
      // 每页条数
      pageSize: 1,
      // 当前页码
      pageNumber: 10
    },
    options
  )
  return axios.request({
    url: process.env.VUE_APP_USER_APIURL + '/user/lists',
    method: 'post',
    data: options
  })
}

/**
 * @description 用户新增
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function user_create(options = {}) {
  options.password = md5(options.password)
  options = Object.assign(
    {
      // 身份证
      care_id: '',
      // 用户名
      username: '',
      // 真实姓名
      truename: '',
      // 手机号
      phone: '',
      // 密码（加密）
      password: '',
      // 角色类型
      role: '',
      // 禁启用（true：启用，false：禁用）
      status: ''
    },
    options
  )
  return axios.request({
    url: process.env.VUE_APP_USER_APIURL + '/user/create',
    method: 'post',
    data: options
  })
}

/**
 * @description 用户详情
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function user_info(options = {}) {
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
    url: process.env.VUE_APP_USER_APIURL + '/user/info',
    method: 'post',
    data: options
  })
}

/**
 * @description 用户编辑
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function user_update(options = {}) {
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
    url: process.env.VUE_APP_USER_APIURL + '/user/update',
    method: 'post',
    data: options
  })
}

/**
 * @description 用户删除
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function user_remove(options = {}) {
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
    url: process.env.VUE_APP_USER_APIURL + '/user/remove',
    method: 'post',
    data: options
  })
}

/**
 * @description 用户状态更新
 * @param dataForm
 * @returns {*}
 * @author webdyc
 */

export function user_status(options = {}) {
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
    url: process.env.VUE_APP_USER_APIURL + '/user/status',
    method: 'post',
    data: options
  })
}
