/**
* hidden: true,               是否隐藏路由
* path: '/welcome',           路由地址
* component:'Layout',         vue文件路径
* redirect: noredirect,       路由重定向
* name:'welcome',             路由名称父级可不填
* meta : {                    单层级路由父级可不填
   role: ['admin','editor'],    页面权限
   title: 'title'               页面标题
   icon: 'svg-name'             页面图标
   breadcrumb: false,           如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)
   keepAlive: false,            页面是否缓存
 }
**/
export default [
  {
    path: "/",
    component: "Layout",
    redirect: "/workplace",
    meta: {
      title: "工作台",
      icon: "workplace",
    },
    children: [
      {
        path: "/workplace",
        name: "workplace",
        component: "/workplace/index",
        meta: {
          title: "工作台",
          role: ['admin']
        },
      }
    ],
  },
  {
    path: "/permission",
    component: "Layout",
    redirect: "/permission/index",
    children: [
      {
        path: "/permission/index",
        name: "permission",
        component: "/permission/index",
        meta: {
          title: "指令权限",
          icon: "table",
        },
      },
    ],
  },

];
