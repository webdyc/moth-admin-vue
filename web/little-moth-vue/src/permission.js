import router from "./router";
import store from "./store";
import { Message } from "element-ui";
import NProgress from "nprogress"; // progress bar
import "nprogress/nprogress.css"; // progress bar style
import { getToken } from "@/utils/auth"; // get token from cookie
import getPageTitle from "@/utils/get-page-title";
import { getAsyncRoutes } from "@/utils/asyncRouter.js";
import { getRole } from "@/api/user";
// import menuListM from '@/router/menuList_Model' // 本地路由

// 是否动态添加过路由
let asyncRouterFlag = 0;
NProgress.configure({ showSpinner: false }); // 进度条配置
const whiteList = ["/login"]; // 不需要登录的路由表

router.beforeEach(async (to, from, next) => {
  // 开始进度条
  NProgress.start();
  // 更换页面标题
  document.title = getPageTitle(to.meta.title);
  // 获取token
  const hasToken = getToken();

  /* 路由跳转判断*/
  if (whiteList.indexOf(to.path) !== -1) {
    // 需要跳转的路由是否是whiteList中的路由，若是，则直接跳转
    next();
  } else {
    // 是否存在token
    if (hasToken) {
      // 添加flag防止多次获取动态路由和栈溢出
      if (!asyncRouterFlag) {
        asyncRouterFlag++;
        try {
          // 通过用户角色，获取到角色路由表
          const { code, data } = await getRole();
          console.log("路由表", code, data);
          // 本地数据
          // const dataList = menuListM
          const dataList = data;
          //   格式化路由结构
          const accessRoutes = getAsyncRoutes(await dataList);
          //   存到vuex中，菜单栏需要
          store.dispatch("permission/generateRoutes", accessRoutes);
          //   动态添加格式化过的路由
          router.addRoutes(accessRoutes);
          next({ ...to, replace: true });
        } catch (error) {
          // 清除用户登录信息后，回跳到登录页去
          await store.dispatch("user/resetToken");
          Message.error(error || "Has Error");
          next(`/login?redirect=${to.path}`);
          NProgress.done();
        }
      } else {
        next();
      }
    } else {
      /* 用户未登录*/
      // 需要跳转的路由不是whiteList中的路由，直接跳转到登录页
      next(`/login?redirect=${to.path}`);
      // 结束精度条
      NProgress.done();
    }
  }
});
router.afterEach(() => {
  // 结束精度条
  NProgress.done();
});
