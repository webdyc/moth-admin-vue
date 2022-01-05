import router from './router'
import store from './store'
// 进度条插件
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth'
import { getAsyncRoutes } from '@/utils/asyncRouter.js'
import getPageTitle from '@/utils/get-page-title'
import menuListM from '@/router/menuList_Model' // 本地路由

// 是否动态添加过路由
let asyncRouterFlag = 0
NProgress.configure({ showSpinner: false }) // 进度条配置
const whiteList = ['/login', '/ragister', '/forget'] // 不需要登录的路由表

router.beforeEach(async (to, from, next) => {
  // 开始进度条
  NProgress.start()
  // 更换页面标题
  document.title = getPageTitle(to.meta.title)
  // 获取token
  const hasToken = getToken()

  /* 路由跳转判断*/
  if (whiteList.indexOf(to.path) !== -1) {
    // 需要跳转的路由是否是whiteList中的路由，若是，则直接跳转
    next()
  } else {
    // 不在白名单中并且已经登陆的时候
    if (hasToken) {
      // 添加flag防止多次获取动态路由和栈溢出
      if (!asyncRouterFlag) {
        asyncRouterFlag++
        // 通过用户角色，获取到角色路由表
        //   const { code, data } = await getRole()
        //   console.log('路由表', code, data)
        // 本地数据
        const dataList = menuListM
        //   const dataList = data
        //   格式化路由结构
        const accessRoutes = getAsyncRoutes(dataList)
        //   存到vuex中，菜单栏需要
        await store.dispatch('permission/generateRoutes', accessRoutes)
        // 动态添加格式化过的路由
        // 需要注意：addRoutes 已废弃：使用 router.addRoute() 代替。
        await accessRoutes.forEach((item) => router.addRoute(item))
        next({ ...to, replace: true })
      } else {
        next()
      }
    } else {
      // 不在白名单中并且未登陆的时候，直接跳转到登录页
      next(`/login?redirect=${to.path}`)
      // 结束精度条
      NProgress.done()
    }
  }
})
router.afterEach(() => {
  // 结束精度条
  NProgress.done()
})
