<template>
  <div class="editor">
    <Editor
      api-key="no-api-key"
      :init="tinymceInit"
      v-model="myValue"
      :disabled="readonly"
    />
  </div>
</template>
<script>
import Editor from '@tinymce/tinymce-vue'
import plugins from './plugins'
import toolbar from './toolbar'
export default {
  components: {
    Editor
  },
  name: 'Tinymce',
  props: {
    id: {
      type: String,
      default: function () {
        return (
          'vue-tinymce-' + +newDate() + ((Math.random() * 1000).toFixed(0) + '')
        )
      }
    },
    value: {
      type: String,
      default: ''
    },
    toolbar: {
      type: Array,
      required: false,
      default() {
        return []
      }
    },
    menubar: {
      type: String,
      default: 'file edit insert view format table'
    },
    height: {
      type: [Number, String],
      required: false,
      default: 360
    },
    width: {
      type: [Number, String],
      required: false,
      default: 'auto'
    },
    /**
     * 只读模式
     * html ,text
     **/
    readonly: {
      type: Boolean,
      default: false
    },
    /**
     * 输出格式
     * html ,text
     **/
    outputFormat: {
      type: String,
      default: 'html ,text'
    }
  },
  data() {
    return {
      myValue: this.value,
      hasChange: false,
      hasInit: false,
      tinymceId: this.id,
      fullscreen: false,
      languageTypeList: {
        en: 'en',
        zh: 'zh_CN',
        es: 'es_MX',
        ja: 'ja'
      },
      // 初始化设置
      tinymceInit: {
        // 辑器替换的 id
        selector: `#${this.tinymceId}`,
        //编辑器高度
        height: this.height,
        language: 'zh_CN',
        // 工具栏配置
        toolbar: this.toolbar.length > 0 ? this.toolbar : toolbar,
        // 菜单和菜单栏配置示例
        menubar: this.menubar,
        // 插件
        plugins: plugins,
        /*content_css: [ //可设置编辑区内容展示的css，谨慎使用
        '/static/reset.css',
        '/static/ax.css',
        '/static/css.css',
        ],*/
        fontsize_formats: '12px 14px 16px 18px 24px 36px 48px 56px 72px',
        font_formats:
          '微软雅黑=Microsoft YaHei,Helvetica Neue,PingFang SC,sans-serif;苹果苹方=PingFang SC,Microsoft YaHei,sans-serif;宋体=simsun,serif;仿宋体=FangSong,serif;黑体=SimHei,sans-serif;Arial=arial,helvetica,sans-serif;Arial Black=arial black,avant garde;Book Antiqua=book antiqua,palatino;',
        link_list: [
          { title: '预置链接1', value: 'http://www.tinymce.com' },
          { title: '预置链接2', value: 'http://tinymce.ax-z.cn' }
        ],
        image_list: [
          {
            title: '预置图片1',
            value: 'https://www.tiny.cloud/images/glyph-tinymce@2x.png'
          },
          {
            title: '预置图片2',
            value: 'https://www.baidu.com/img/bd_logo1.png'
          }
        ],
        image_class_list: [
          { title: 'None', value: '' },
          { title: 'Some class', value: 'class-name' }
        ],
        importcss_append: true,
        //自定义文件选择器的回调内容
        file_picker_callback: function (callback, value, meta) {
          if (meta.filetype === 'file') {
            callback('https://www.baidu.com/img/bd_logo1.png', {
              text: 'My text'
            })
          }
          if (meta.filetype === 'image') {
            callback('https://www.baidu.com/img/bd_logo1.png', {
              alt: 'My alt text'
            })
          }
          if (meta.filetype === 'media') {
            callback('movie.mp4', {
              source2: 'alt.ogg',
              poster: 'https://www.baidu.com/img/bd_logo1.png'
            })
          }
        },
        // 置顶工具栏
        toolbar_sticky: true,
        // 自动保存
        autosave_ask_before_unload: false
      }
    }
  },
  computed: {
    containerWidth() {
      constwidth = this.width
      if (/^[\d]+(\.[\d]+)?$/.test(width)) {
        // matches `100`, `'100'`
        return `${width}px`
      }
      returnwidth
    }
  },
  watch: {
    //监听内容变化
    value(newValue) {
      this.myValue = newValue
    },
    myValue(newValue) {
      console.log(newValue)
      this.$emit('update:value', newValue)
    }
  },
  mounted() {},
  methods: {}
}
</script>
<style lang="scss">
//没有申请key的话会出现一个弹窗要去申请key,在这里把那个弹窗隐藏，当然也可以自己申请key再使用
.tox-notifications-container {
  display: none !important;
}
</style>
