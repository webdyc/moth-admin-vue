<template>
  <div class="app-info">
    <!-- 顶部返回按钮 -->
    <div class="app-info-top">
      <el-page-header content="详情页面" @back="goBack" />
    </div>
    <!-- 详情内容区 -->

    <div class="app-info-content">
      <div class="app-info-content-item">
        <div class="app-info-content-item-title title-line">基础用法</div>
        <el-descriptions>
          <el-descriptions-item label="用户名"
            >kooriookami</el-descriptions-item
          >
          <el-descriptions-item label="手机号"
            >18100000000</el-descriptions-item
          >
          <el-descriptions-item label="居住地">苏州市</el-descriptions-item>
          <el-descriptions-item label="备注">
            <el-tag size="small">学校</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="联系地址"
            >江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item
          >
        </el-descriptions>
      </div>
      <div class="app-info-content-item">
        <div class="app-info-content-item-title title-line">垂直带边框列表</div>
        <el-descriptions direction="vertical" :column="4" border>
          <el-descriptions-item label="用户名"
            >kooriookami</el-descriptions-item
          >
          <el-descriptions-item label="手机号"
            >18100000000</el-descriptions-item
          >
          <el-descriptions-item label="居住地" :span="2"
            >苏州市</el-descriptions-item
          >
          <el-descriptions-item label="备注">
            <el-tag size="small">学校</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="联系地址"
            >江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item
          >
        </el-descriptions>
      </div>
      <div class="app-info-content-item">
        <div class="app-info-content-item-title title-line">暂无数据</div>
        <el-empty description="暂无数据" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goBack() {
      console.log("go back");
    },
  },
};
</script>

<style lang="scss" scoped>
.h {
  color: $--color-primary;
}
</style>
