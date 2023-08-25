import request from '@/utils/request'

// 获取gen数据
export function gensData() {
  return request({
    url: '/microgrid/data/powergens',
    method: 'get'
  })
}

// 获取pv数据
export function pvsData() {
  return request({
    url: '/microgrid/data/powerpvs',
    method: 'get'
  })
}

// 获取wind数据
export function windsData() {
  return request({
    url: '/microgrid/data/powerwinds',
    method: 'get'
  })
}

export function pvtotalData() {
  return request({
    url: '/microgrid/data/powerpvtotal',
    method: 'get'
  })
}

export function windtotalData() {
  return request({
    url: '/microgrid/data/powerwindtotal',
    method: 'get'
  })
}

export function listDataByType(query) {
  return request({
    url: '/microgrid/data/listDataByType',
    method: 'get',
    params: query
  })
}
