import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
// ant-design-vue
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
// axios
import Axios from 'axios'
// svg全局组件
import SvgIcon from '@/components/Svgicon'
// 语言
import VueI18n from './language'
// svg文件解析
import '@/icons'
// 路由守卫
import '@/permission'
// 拖拽弹窗指令
import drag from '@/directive/ant-drag-dialog'
// 判断权限指令
import permission from '@/directive/permission'

const app = createApp(App)
// 注册拖拽弹窗指令
app.directive('ant-drag-dialog', drag)
// 注册判断权限指令
app.directive('permission', permission)
// 注入Axios
app.config.globalProperties.$axios = Axios
app.use(store)
app.use(router)
app.use(Antd)
app.use(VueI18n)
app.component('SvgIcon', SvgIcon)
app.mount('#app')
