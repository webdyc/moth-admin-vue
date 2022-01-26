import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

/* Layout */
import Layout from "@/layout";
// 本地路由
export const constantRoutes = [
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true,
  },

  {
    path: "external-link",
    component: Layout,
    children: [
      {
        path: "https://github.com/webdyc/little-moth",
        meta: { title: "外链地址", icon: "link" },
      },
    ],
  },
  // 404 必须放在所有路由后面 !!!
  {
    path: "*",
    component: () => import("@/views/404"),
    hidden: true,
  },
];

const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes,
  });

const router = createRouter();

// 重置路由
export function resetRouter () {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}

export default router;
