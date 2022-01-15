import request from '@/api/request'

export function getList (params) {
  return request({
    url: '/table/list',
    method: 'get',
    params
  })
}
