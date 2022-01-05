// import { createStore } from "vuex";

// export default createStore({
//   state: {},
//   mutations: {},
//   actions: {},
//   modules: {},
// });

import { createStore } from 'vuex'
import getters from './getters'

const modulesFiles = require.context('./modules', true, /\.js$/)

// 不需要“从导入应用程序”/模块/应用程序'`
// 它将自动要求模块文件中的所有vuex模块
const modules = modulesFiles.keys().reduce((modules, modulePath) => {
  // set './app.js' => 'app'
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
  const value = modulesFiles(modulePath)
  modules[moduleName] = value.default
  return modules
}, {})

export default createStore({
  getters,
  modules
})
