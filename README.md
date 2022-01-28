<p align="center">
  <img width="320" src="https://webdyc.oss-cn-beijing.aliyuncs.com/moth-admin/logo.png">
</p>

English | [简体中文](./README-zh.md)

## 简介

基于 Vue + Element UI 的前后端分离权限解决方案，系统完全基于实际业务场景开发，只需要修改极少的代码既可实现后台管理系统的搭建。  
[线上地址](http://moth-admin-vue.webdyc.com/)

## Build Setup

```bash
# clone the project
git clone https://github.com/webdyc/moth-admin-vue

# enter the project directory
cd moth-admin-vue

# install dependency
npm install

# develop
npm run dev
```

This will automatically open http://localhost:9528

## Build

```bash
# build for test environment
npm run build:stage

# build for production environment
npm run build:prod
```

## Advanced

```bash
# preview the release environment effect
npm run preview

# preview the release environment effect + static resource analysis
npm run preview -- --report

# code format check
npm run lint

# code format check and auto fix
npm run lint -- --fix
```

## 飞蛾的由来

Bug 一词在英文中本来是虫子，臭虫之意，为什么现在我们会用它来指程序中存在的缺陷呢？这里就来说说它的由来。
其实早在 1878 年，大发明家爱迪生就在给朋友的信中用 bug 一词代指过电子电路中的故障（可能导致故障的原因），但是真正在计算机行业载入史册还要到 1947 年 9 月 9 日。
这时第一批写程序的程序员们正在哈佛大学研发 markII 计算机，其中 Grace Murray Hopper 在查找一个问题时，在中继器触点旁发现了一个飞蛾，用日志本拍死了它，并标记了它是“第一个找到 bug 真实案例”。由此 bug 一词便被用来代指计算机中的缺陷或故障了。相应地 debug 则被用来指代去除故障的调测行为。
