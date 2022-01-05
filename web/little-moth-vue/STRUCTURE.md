├── build // 构建相关   
├── config // 配置相关
├── mock // 项目 mock 模拟数据
├── pubilc // mock 数据相关
│ ├── favicon.ico // favicon 图标
│ ├── index.html // html 模板
├── src // 源代码
│   ├── api // 所有请求
│   ├── assets // 主题 字体等静态资源
│   ├── components // 全局公用组件
│   │   ├── Breadcrumb // 面包屑
│   │   ├── Hamburger // 关闭/展开菜单
│   │   ├── Kanban // 可拖拽看板
│   │   ├── MDinput // input 组件
│   │   ├── RightPanel // 全局设置插件
│   │   ├── Screenfull // 浏览器全屏
│   │   ├── SvgIcon // 图标
│   │   ├── TextHoverEffect // 文字 hover 特效组件
│   │   ├── ThemePicker // 选色器
│   │   ├── Tinymce // 富文本
│   ├── directive // 全局指令
│   │   ├── clipboard // 一键复制指令
│   │   ├── el-drag-dialog // 弹出框拖拽指令
│   │   ├── permission // 用户权限指令
│   │   ├── waves // 水波纹指令
│   ├── filtres // 全局 filter
│   ├── icons // 项目所有 svg icons
│   ├── router // 路由
│   │   ├── index // 路由设置
│   │   ├── menuList_Model // 本地路由
│   ├── store // 全局 store 管理
│   │   ├── app // 项目信息
│   │   ├── permission // 权限信息
│   │   ├── settings // 设置信息
│   │   ├── tagsView // 面包屑路由信息
│   │   ├── user // 用户信息
│   ├── styles // 全局样式
│   ├── utils // 全局公用方法
│   │   ├── asyncRouter // 处理动态路由格式
│   │   ├── permission // 权限判断函数
│   │   ├── request // 处理 axios
│   ├── vendor // 公用 vendor
│   ├── views // view
│   ├── App.vue // 入口页面
│   ├── main.js // 入口 加载组件 初始化等
│ └── permission.js // 权限管理
├── static // 第三方不打包资源
│   ├── common.css // 全局样式
│   ├── free.css // 样式初始化
│   ├── normalize.css // 样式初始化
├── .babelrc // babel-loader 配置
├── eslintrc.js // eslint 配置项
├── .gitignore // git 忽略项
└── package.json // package.json
