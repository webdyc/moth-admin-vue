'use strict'
const path = require('path')
const defaultSettings = require('./src/settings.js')

function resolve(dir) {
  return path.join(__dirname, dir)
}

// 系统名称
const name = defaultSettings.title || 'vue Admin Template'
// 端口配置
const port = process.env.port || process.env.npm_config_port || 9528 // dev port

module.exports = {
  // 基本路径
  publicPath: process.env.NODE_ENV === 'production' ? '' : '/',
  // 输出文件目录
  outputDir: process.env.NODE_ENV === 'production' ? 'dist' : 'devdist',
  assetsDir: 'static',
  // 开发环境配置
  devServer: {
    // 可以让外部访问
    host: '0.0.0.0',
    // 端口
    port: port,
    // 项目运行后是否自动打开
    open: true,
    overlay: {
      warnings: false,
      errors: true
    },
    // proxy代理
    proxy: {
      [process.env.VUE_APP_BASE_API]: {
        target: process.env.VUE_APP_BASE_APIURL,
        ws: true,
        changeOrigin: true,
        // 不重写请求地址
        pathRewrite: { [`^${process.env.VUE_APP_BASE_API}`]: '' }
      },
      [process.env.VUE_APP_USER_API]: {
        target: process.env.VUE_APP_USER_APIURL,
        ws: true,
        changeOrigin: true,
        // 不重写请求地址
        pathRewrite: { [`^${process.env.VUE_APP_USER_API}`]: '' }
      }
    }
  },
  // 关闭语法的自动检测
  lintOnSave: false,
  configureWebpack: {
    // provide the app's title in webpack's name field, so that
    // it can be accessed in index.html to inject the correct title.
    name: name,
    resolve: {
      alias: {
        '@': resolve('src')
      }
    }
  },
  // svg
  chainWebpack: (config) => {
    // 它可以提高第一屏的速度，建议开启预加载
    config.plugin('preload').tap(() => [
      {
        rel: 'preload',
        // to ignore runtime.js
        // https://github.com/vuejs/vue-cli/blob/dev/packages/@vue/cli-service/lib/config/app.js#L171
        fileBlacklist: [/\.map$/, /hot-update\.js$/, /runtime\..*\.js$/],
        include: 'initial'
      }
    ])
    // 当页面太多时，会导致太多无意义的请求
    config.plugins.delete('prefetch')
    // 设置 svg-sprite-loader
    config.module.rules.delete('svg') //重点:删除默认配置中处理svg,
    config.module.rule('svg').exclude.add(resolve('src/icons')).end()
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(resolve('src/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
      .end()
  },
  // css相关配置
  css: {
    // 是否使用css分离插件 ExtractTextPlugin
    extract: true,
    // 开启 CSS source maps?
    sourceMap: false,
    // css预设器配置项
    loaderOptions: {
      scss: {
        prependData: `@import "./src/styles/main.scss";`
      }
    }
  }
}
