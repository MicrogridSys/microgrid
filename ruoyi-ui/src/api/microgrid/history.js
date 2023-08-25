import request from '@/utils/request'

// 查询发电机数据表列表
export function listHistory(query) {
  return request({
    url: '/microgrid/data/listDataByType',
    method: 'get',
    params: query
  })
}

// 查询发电机数据表详细
export function getHistory(id) {
  return request({
    url: '/microgrid/history/' + id,
    method: 'get'
  })
}

// 新增发电机数据表
export function addHistory(data) {
  return request({
    url: '/microgrid/history',
    method: 'post',
    data: data
  })
}

// 修改发电机数据表
export function updateHistory(data) {
  return request({
    url: '/microgrid/history',
    method: 'put',
    data: data
  })
}

// 删除发电机数据表
export function delHistory(id) {
  return request({
    url: '/microgrid/history/' + id,
    method: 'delete'
  })
}
