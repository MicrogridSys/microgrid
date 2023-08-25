import request from '@/utils/request'

// 查询终端表列表
export function listTerminals(query) {
  return request({
    url: '/microgrid/terminals/list',
    method: 'get',
    params: query
  })
}

export function listTerminalsByType(query) {
  return request({
    url: '/microgrid/terminals/listByType',
    method: 'get',
    params: query
  })
}

// 查询终端表详细
export function getTerminals(id) {
  return request({
    url: '/microgrid/terminals/' + id,
    method: 'get'
  })
}

// 新增终端表
export function addTerminals(data) {
  return request({
    url: '/microgrid/terminals',
    method: 'post',
    data: data
  })
}

// 修改终端表
export function updateTerminals(data) {
  return request({
    url: '/microgrid/terminals',
    method: 'put',
    data: data
  })
}

// 删除终端表
export function delTerminals(id) {
  return request({
    url: '/microgrid/terminals/' + id,
    method: 'delete'
  })
}
