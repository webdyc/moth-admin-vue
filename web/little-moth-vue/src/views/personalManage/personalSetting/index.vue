<template>
  <div class="app-box setting">
    <el-tabs
      class="setting-tabs"
      v-model="activeName"
      tab-position="left"
      :stretch="true"
    >
      <el-tab-pane label="基础设置" name="base"></el-tab-pane>
      <el-tab-pane label="安全设置" name="security"></el-tab-pane>
      <el-tab-pane label="账户绑定" name="binding"></el-tab-pane>
      <el-tab-pane label="新消息通知" name="notification"></el-tab-pane>
    </el-tabs>
    <div class="setting-content">
      <Base v-if="activeName === 'base'"></Base>
      <Security v-if="activeName === 'security'"></Security>
      <Binding v-if="activeName === 'binding'"></Binding>
      <Binding v-if="activeName === 'notification'"></Binding>
    </div>
  </div>
</template>

<script>
import Base from "./base";
import Security from "./security";
import Binding from "./binding";
export default {
  data() {
    return {
      activeName: "base",
    };
  },
  components: {
    Base,
    Security,
    Binding,
  },
  watch: {},

  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>
<style lang="scss" scoped>
.setting {
  padding: 16px 0px;
  background: #ffffff;
  display: flex;
  .setting-tabs {
    width: 223px;
    ::v-deep .el-tabs__header {
      width: 100%;
      margin-right: 0;
      .el-tabs__item {
        text-align: left;
        padding: 0 16px;
      }
    }
  }

  .setting-content {
    flex: 1;
    padding: 8px 40px 8px 30px;
  }
}
</style>
