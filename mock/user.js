const tokens = {
  admin: {
    token: "admin-token",
  },
  editor: {
    token: "editor-token",
  },
};

const users = {
  "admin-token": {
    roles: ["admin"],
    introduction: "I am a super administrator",
    avatar:
      "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
    username: "Super Admin",
  },
  "editor-token": {
    roles: ["editor"],
    introduction: "I am an editor",
    avatar:
      "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
    username: "Normal Editor",
  },
};
// 路由表
const menuListM = [
  {
    path: "/",
    component: "Layout",
    redirect: "welcome",
    children: [
      {
        path: "welcome",
        name: "Welcome",
        component: "/welcome/index",
        meta: {
          title: "欢迎首页",
          role: ["admin", "addbtn1"],
          icon: "example",
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
    path: "/charts",
    component: "Layout",
    redirect: "/charts/index",
    meta: {
      title: "图表管理",
      icon: "table",
    },
    children: [
      {
        path: "/charts/index",
        name: "chartsline",
        component: "/charts/line/index",
        meta: {
          title: "折线图",
        },
      },
      {
        path: "/charts/mixChart",
        name: "chartsmixChart",
        component: "/charts/mixChart/index",
        meta: {
          title: "混合图表",
        },
      },
    ],
  },
  {
    path: "/mapManage",
    component: "Layout",
    redirect: "/aMap/index",
    meta: {
      title: "地图管理",
      icon: "table",
    },
    children: [
      {
        path: "/aMap/index",
        name: "aMap",
        component: "/mapManage/aMap/index",
        meta: {
          title: "高德地图",
        },
      }
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
    path: "/menu",
    component: "Layout",
    redirect: "/menu/index",
    children: [
      {
        path: "/menu/index",
        name: "menu",
        component: "/menu/index",
        meta: {
          title: "菜单管理",
          icon: "table",
        },
      },
    ],
  },
];

module.exports = [
  // 登录
  {
    url: process.env.VUE_APP_BASE_API + "/user/login",
    type: "post",
    response: (config) => {
      const { username } = config.body;
      const token = tokens[username].token;
      const info = users[token];
      // mock error
      if (!token) {
        return {
          code: 60204,
          message: "用户名密码错误.",
        };
      }
      return {
        code: 20000,
        data: { info, token },
      };
    },
  },

  // 获取用户动态路由
  {
    url: process.env.VUE_APP_BASE_API + "/user/role",
    type: "get",
    response: (config) => {
      const info = menuListM;

      // mock error
      if (!info) {
        return {
          code: 50008,
          message: "获取菜单列表失败.",
        };
      }

      return {
        code: 20000,
        data: info,
      };
    },
  },

  // user logout
  {
    url: process.env.VUE_APP_BASE_API + "/user/logout",
    type: "post",
    response: (_) => {
      return {
        code: 20000,
        data: "success",
      };
    },
  },
];
