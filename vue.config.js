"use strict";
const path = require("path");
const defaultSettings = require("./src/settings.js");

function resolve (dir) {
  return path.join(__dirname, dir);
}

const name = defaultSettings.title || "小飞蛾后台管理系统"; // 网页标题

const port = process.env.port || process.env.npm_config_port || 9528; // 端口配置

// vue.config.js 配置说明
//官方vue.config.js 参考文档 https://cli.vuejs.org/zh/config/#css-loaderoptions
module.exports = {
  // 基本路径
  publicPath: process.env.NODE_ENV === "production" ? "/" : "/",
  // 输出文件目录
  outputDir: "dist",
  // 用于放置生成的静态资源 (js、css、img、fonts) 的；（项目打包之后，静态资源会放在这个文件夹下）
  assetsDir: "static",
  // 是否开启eslint保存检测，有效值：ture | false | 'error'
  lintOnSave: process.env.NODE_ENV === 'development',
  // 如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建。
  productionSourceMap: false,
  // 开发环境配置
  devServer: {
    // 可以让外部访问
    host: "0.0.0.0",
    // 端口
    port: port,
    // 项目运行后是否自动打开
    open: true,
    overlay: {
      warnings: false,
      errors: true,
    },
    // proxy代理
    proxy: {
      // BASE_API 代理
      [process.env.VUE_APP_BASE_API]: {
        target: process.env.VUE_APP_BASE_APIURL,
        ws: true,
        changeOrigin: true,
        // 不重写请求地址
        pathRewrite: { [`^${process.env.VUE_APP_BASE_API}`]: "" },
      },
      // USER_API 代理
      [process.env.VUE_APP_USER_API]: {
        target: process.env.VUE_APP_USER_APIURL,
        ws: true,
        changeOrigin: true,
        // 不重写请求地址
        pathRewrite: { [`^${process.env.VUE_APP_USER_API}`]: "" },
      }
    },
    /**
     * 需要mack数据的话，自行打开
     * before: require("./mock/mock-server.js"),
     */
  },
  configureWebpack: {
    // 在webpack的name字段中提供应用的标题，这样
    // 它可以在index.html中访问以注入正确的标题
    name: name,
    resolve: {
      alias: {
        "@": resolve("src"),
        "@@": resolve("src/views/questionnaire/VForm"),
      },
    },
  },
  // svg
  chainWebpack (config) {
    // 它可以提高第一屏的速度，建议打开预加载
    config.plugin("preload").tap(() => [
      {
        rel: "preload",
        // 忽略 ignore runtime.js
        fileBlacklist: [/\.map$/, /hot-update\.js$/, /runtime\..*\.js$/],
        include: "initial",
      },
    ]);

    // 当页面过多时，会导致无意义的请求过多
    config.plugins.delete("prefetch");

    // set svg-sprite-loader
    config.module.rule("svg").exclude.add(resolve("src/icons")).end();
    config.module
      .rule("icons")
      .test(/\.svg$/)
      .include.add(resolve("src/icons"))
      .end()
      .use("svg-sprite-loader")
      .loader("svg-sprite-loader")
      .options({
        symbolId: "icon-[name]",
      })
      .end();

    config.when(process.env.NODE_ENV !== "development", (config) => {
      config
        .plugin("ScriptExtHtmlWebpackPlugin")
        .after("html")
        .use("script-ext-html-webpack-plugin", [
          {
            // `runtime` 必须与runtimeecchunk名称相同。默认是 is `runtime`
            inline: /runtime\..*\.js$/,
          },
        ])
        .end();
      config.optimization.splitChunks({
        chunks: "all",
        cacheGroups: {
          libs: {
            name: "chunk-libs",
            test: /[\\/]node_modules[\\/]/,
            priority: 10,
            chunks: "initial", // 只打包最初依赖的第三方
          },
          elementUI: {
            name: "chunk-elementUI", // 将elementUI拆分为单个包
            priority: 20, // 重量需要大于libs和app，否则它将被打包成libs或app
            test: /[\\/]node_modules[\\/]_?element-ui(.*)/, // 为了适应 cnpm
          },
          commons: {
            name: "chunk-commons",
            test: resolve("src/components"), // 可以自定义规则
            minChunks: 3, //  minimum common number
            priority: 5,
            reuseExistingChunk: true,
          },
        },
      });
      config.optimization.runtimeChunk("single");
    });
  },
  css: {
    loaderOptions: {
      scss: {
        /* 自动引入全局scss文件 */
        prependData: `
          @import "./src/views/questionnaire/VForm/styles/global.scss";
        `
      }
    }
  },
 
};
