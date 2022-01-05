// 语言
import { createI18n } from 'vue-i18n'
import store from '@/store'

const i18n = createI18n({
  locale: store.state.settings.language, // 默认语言
  messages: {
    ch: require('./ch.js'),
    en: require('./en.js')
  }
})
export default i18n
