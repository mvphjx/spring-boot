import request from '@/utils/request'
// 列表查询
export function getList(data) {
  return request({
    url: '/team/list',
    method: 'post',
    data
  })
}
// 详情展示
export function getTeam(uuid) {
  const data = { uuid: uuid }
  return request({
    url: '/team/get',
    method: 'get',
    params: data
  })
}
