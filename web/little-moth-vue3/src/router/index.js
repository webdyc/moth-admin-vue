import { createRouter, createWebHashHistory } from 'vue-router'
/* Layout */
// import Layout from '@/layout/Index.vue'

// 本地路由
export const constantRoutes = [
  // 登录
  {
    path: '/login',
    name: 'Login',
    meta: {
      title: '登录'
    },
    hidden: true,
    component: () =>
      import(/* webpackChunkName: "acount" */ '@/views/acount/Login.vue')
  },
  // 注册
  {
    path: '/ragister',
    name: 'Ragister',
    meta: {
      title: '注册'
    },
    hidden: true,
    component: () =>
      import(/* webpackChunkName: "acount" */ '@/views/acount/Ragister.vue')
  },
  // 忘记密码
  {
    path: '/forget',
    name: 'Forget',
    meta: {
      title: '忘记密码'
    },
    hidden: true,
    component: () =>
      import(/* webpackChunkName: "acount" */ '@/views/acount/Forget.vue')
  },
  // 404
  {
    path: '/:pathMatch(.*)*',
    component: () =>
      import(/* webpackChunkName: "home" */ '@/views/404/Index.vue'),
    hidden: true
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes: constantRoutes
})

// 删除路由 see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
