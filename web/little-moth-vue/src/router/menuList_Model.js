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
    redirect: "workplace",
    meta: {
      title: "工作台",
      icon: "table",
    },
    children: [
      {
        path: "workplace",
        name: "workplace",
        component: "/workplace/index",
        meta: {
          title: "工作台",
        },
      }
    ],
  },
  {
    path: "/dashboard",
    component: "Layout",
    redirect: "dashboard/welcome",
    meta: {
      title: "仪表盘",
      icon: "table",
    },
    children: [
      {
        path: "welcome",
        name: "Welcome",
        component: "/dashboard/welcome/index",
        meta: {
          title: "欢迎首页",
        },
      },
      {
        path: "analysis",
        name: "analysis",
        component: "/dashboard/analysis/index",
        meta: {
          title: "分析页",
        },
      },
    ],
  },
  {
    path: "/tabel",
    component: "Layout",
    redirect: "/tabel/index",
    meta: {
      title: "案例模板",
      icon: "table",
    },
    children: [
      {
        path: "/tabel/index",
        name: "tabel",
        component: "/tabel/index",
        meta: {
          title: "表格模板",
        },
      },
      {
        path: "/structureTabel/index",
        name: "structureTabel",
        component: "/tabel/structureTabel",
        meta: {
          title: "左右表格模板",
        },
      },
      {
        path: "/tabelInfo/index",
        name: "tabelInfo",
        component: "/tabel/tabelInfo",
        meta: {
          title: "详情模板",
        },
      },
    ],
  },
  {
    path: "/components",
    component: "Layout",
    redirect: "/tinymce/index",
    meta: {
      title: "组件",
      icon: "table",
    },
    children: [
      {
        path: "/recorder/index",
        name: "recorder",
        component: "/components/recorder/index",
        meta: {
          title: "录音",
        },
      },
      {
        path: "/tinymce/index",
        name: "tinymce",
        component: "/components/tinymce/index",
        meta: {
          title: "富文本编译器",
        },
      },
      {
        path: "/componentsDemo/index",
        name: "componentsDemo",
        component: "/components/componentsDemo/index",
        meta: {
          title: "小组件",
        },
      },
      {
        path: "/dragDalog/index",
        name: "dragDalog",
        component: "/components/dragDalog/index",
        meta: {
          title: "可拖拽弹窗",
        },
      },
      {
        path: "/dragKanban/index",
        name: "dragKanban",
        component: "/components/dragKanban/index",
        meta: {
          title: "可拖拽看板",
        },
      },
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
  {
    path: "/mapManage",
    component: "Layout",
    redirect: "mapManage/aMap",
    children: [
      {
        path: "mapManage/aMap",
        name: "mapManage/aMap",
        component: "/mapManage/aMap/index",
        meta: {
          title: "地图管理",
          icon: "example",
        },
      },
    ],
  },
  {
    path: "/flowDiagram",
    component: "Layout",
    redirect: "/flowDiagram/x6Flow",
    meta: {
      title: "流程图",
      icon: "table",
    },
    children: [
      {
        path: "/flowDiagram/x6Flow",
        component: "/flowDiagram/x6Flow/index",
        meta: {
          title: "x6流程图",
        },
      },
      {
        path: "/flowDiagram/logicFlow",
        component: "/flowDiagram/logicFlow/index",
        meta: {
          title: "logicFlow流程图",
        },
      },
    ],
  },
  {
    path: "/personalManage",
    component: "Layout",
    redirect: "personal",
    meta: {
      title: "个人信息",
      icon: "example",
    },
    children: [
      {
        path: "personal",
        name: "personal",
        component: "/personalManage/personal/index",
        meta: {
          title: "个人中心",
        },
      },
      {
        path: "personalSetting",
        name: "personalSetting",
        component: "/personalManage/personalSetting/index",
        redirect: "personalSetting/base",
        meta: {
          title: "个人设置",
        },
        children: [
          {
            hidden: true,
            path: "base",
            name: "personalSettingBase",
            component: "/personalManage/personalSetting/base/index",
            meta: {
              title: "基础设置",
              breadcrumb: false,
              keepAlive: true,
            },
          },
          {
            hidden: true,
            path: "security",
            name: "personalSettingSecurity",
            component: "/personalManage/personalSetting/security/index",
            meta: {
              title: "安全设置",
              breadcrumb: false,
              keepAlive: true,
            },
          },
        ],
      },
    ],
  },
  {
    path: "/systemManage",
    component: "Layout",
    redirect: "role",
    meta: {
      title: "系统管理",
      icon: "table",
    },
    children: [
      {
        path: "role",
        name: "role",
        component: "/systemManage/role/index",
        meta: {
          title: "角色管理",
        },
      },
      {
        path: "menu",
        name: "menu",
        component: "/systemManage/menu/index",
        meta: {
          title: "菜单管理",
        },
      },
    ],
  },
];
