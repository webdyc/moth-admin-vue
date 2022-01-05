import { constantRoutes } from '@/router'
/**
 * 根据路由列表返回role
 * @param routes 路由列表
 */
export function filterAsyncRoles(routes, roles) {
  const res = roles || []
  routes.forEach(route => {
    const tmp = { ...route }
    if (tmp && tmp.meta && tmp.meta.role) {
      tmp.meta.role.forEach(role => {
        res.push(role)
      })
    }
    if (tmp.children) {
      return filterAsyncRoles(tmp.children, res)
    }
  })
  return res
}

const state = {
  // 本地路由
  routes: [],
  // 动态路由
  asyncRoutes: [],
  // 按钮权限
  roles: []
}

const mutations = {
  // 保存按钮权限列表
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  // 保存动态路由列表
  SET_ROUTES: (state, routes) => {
    state.asyncRoutes = routes
    state.routes = routes.concat(constantRoutes)
  }
}

const actions = {
  // 异步处理动态路由及按钮权限
  generateRoutes({ commit }, asyncRoutes) {
    return new Promise(resolve => {
      // 遍历路由中的role权限
      const roles = filterAsyncRoles(asyncRoutes)
      commit('SET_ROLES', roles)
      commit('SET_ROUTES', asyncRoutes)
      resolve(asyncRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
