<template>
  <div class="search-card">
    <!-- 标题 -->
    <div class="header">
      <span>搜索用户数 </span>
      <i class="el-icon-warning-outline"></i>
    </div>
    <!-- 数值显示 -->
    <div class="main">
      <span class="main-title">12321</span>
      <span class="main-content">12321</span>
      <i class="el-icon-caret-top"></i>
      <i class="el-icon-caret-bottom"></i>
    </div>
    <div class="footer">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchCard",
  data() {
    return {};
  },
  components: {},
  mounted() {},
  methods: {},
};
</script>

<style scoped lang="scss">
.search-card {
  .header {
    color: rgba(0, 0, 0, 0.45);
    font-size: 14px;
    line-height: 22px;
    white-space: nowrap;
    span {
      margin-right: 10px;
    }
  }
  .main {
    margin: 10px 0;
    display: flex;
    align-items: center;
    height: 32px;
    margin-right: 32px;
    color: rgba(0, 0, 0, 0.85);
    font-size: 24px;
    line-height: 32px;
    .main-title {
      margin-right: 30px;
    }
    .main-content {
      margin-right: 0;
      color: rgba(0, 0, 0, 0.45);
      font-size: 16px;
      vertical-align: top;
    }
    i {
      color: rgba(0, 0, 0, 0.45);
      font-size: 16px;
    }
  }
}
.charts {
  width: 100%;
  height: 99px;
}
</style>
