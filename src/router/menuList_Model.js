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
      icon: "table",
    },
    children: [
      {
        path: "/workplace",
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
    redirect: "/dashboard/welcome",
    meta: {
      title: "仪表盘",
      icon: "dashboard",
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
    redirect: "/tabel/tabelList",
    meta: {
      title: "案例模板",
      icon: "table",
    },
    children: [
      {
        path: "tabelList",
        name: "tabelList",
        component: "/tabel/tabelList",
        meta: {
          title: "表格模板",
        },
      },
      {
        path: "structureTabel",
        name: "structureTabel",
        component: "/tabel/structureTabel",
        meta: {
          title: "左右表格模板",
        },
      },
      {
        path: "tabelInfo",
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
    redirect: "/components/recorder",
    meta: {
      title: "组件",
      icon: "table",
    },
    children: [
      {
        path: "recorder",
        name: "recorder",
        component: "/components/recorder/index",
        meta: {
          title: "录音",
        },
      },
      {
        path: "tinymce",
        name: "tinymce",
        component: "/components/tinymce/index",
        meta: {
          title: "富文本编译器",
        },
      },
      {
        path: "dragDalog",
        name: "dragDalog",
        component: "/components/dragDalog/index",
        meta: {
          title: "可拖拽弹窗",
        },
      },
      {
        path: "dragKanban",
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
        path: "aMap",
        name: "aMap",
        component: "/mapManage/aMap/index",
        meta: {
          title: "地图管理",
          icon: "example",
        },
      },
    ],
  },
  {
    path: "/questionnaire",
    component: "Layout",
    redirect: "/questionnaire",
    children: [
      {
        path: "/questionnaire",
        name: "questionnaire",
        component: "/questionnaire/index",
        meta: {
          title: "问卷管理",
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
        path: "x6Flow",
        name: "x6Flow",
        component: "/flowDiagram/x6Flow/index",
        meta: {
          title: "x6流程图",
        },
      },
      {
        path: "logicFlow",
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
    redirect: "/personalManage/personal",
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
        meta: {
          title: "个人设置",
        }
      },
    ],
  },
  {
    path: "/systemManage",
    component: "Layout",
    redirect: "/systemManage/role",
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
