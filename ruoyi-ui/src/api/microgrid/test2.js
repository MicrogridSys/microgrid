import request from '@/utils/request'

// 查询json列表
export function listTest2(query) {
  return request({
    url: '/microgrid/test2/list',
    method: 'get',
    params: query
  })
}

// 查询json详细
export function getTest2(id) {
  return request({
    url: '/microgrid/test2/' + id,
    method: 'get'
  })
}

// 新增json
export function addTest2(data) {
  return request({
    url: '/microgrid/test2',
    method: 'post',
    data: data
  })
}

// 修改json
export function updateTest2(data) {
  return request({
    url: '/microgrid/test2',
    method: 'put',
    data: data
  })
}

// 删除json
export function delTest2(id) {
  return request({
    url: '/microgrid/test2/' + id,
    method: 'delete'
  })
}
