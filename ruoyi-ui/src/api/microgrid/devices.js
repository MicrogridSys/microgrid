import request from '@/utils/request'

// 查询设备管理列表
export function listDevices(query) {
  return request({
    url: '/microgrid/devices/list',
    method: 'get',
    params: query
  })
}

export function listDevicesByType(query) {
  return request({
    url: '/microgrid/devices/listByType',
    method: 'get',
    params: query
  })
}


// 查询设备管理详细
export function getDevices(id) {
  return request({
    url: '/microgrid/devices/' + id,
    method: 'get'
  })
}

// 新增设备管理
export function addDevices(data) {
  return request({
    url: '/microgrid/devices',
    method: 'post',
    data: data
  })
}

// 修改设备管理
export function updateDevices(data) {
  return request({
    url: '/microgrid/devices',
    method: 'put',
    data: data
  })
}

// 删除设备管理
export function delDevices(id) {
  return request({
    url: '/microgrid/devices/' + id,
    method: 'delete'
  })
}
