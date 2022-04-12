<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>
<style>
/* css初始化 */
@import "../static/normalize.css";
/* 公共样式 */
@import "../static/free.css";
/* 公共样式 */
@import "../static/common.css";
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
