import request from '@/api/http'

export function getList (params) {
  return request({
    url: '/table/list',
    method: 'get',
    params
  })
}
