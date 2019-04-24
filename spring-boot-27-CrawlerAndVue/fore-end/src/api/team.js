import request from '@/utils/request'

export function getList(data) {
  return request({
    url: '/team/list',
    method: 'post',
    data
  })
}

export function getTeam(uuid) {
  const data={uuid:uuid}
  return request({
    url: '/team/get',
    method: 'get',
    params:data
  })
}
