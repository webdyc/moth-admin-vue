<template>
  <div>
    <div class="news-tips cursor-pointer">
      <el-badge :value="0" :max="99">
        <i class="el-icon-bell" @click="handleTips" />
      </el-badge>
    </div>
    <el-drawer
      title="消息通知"
      :visible.sync="drawer"
      direction="rtl"
      append-to-body
    >
      <el-divider />
      <div class="tips-list">
        <el-timeline v-if="tableData && tableData.length > 0">
          <el-timeline-item
            v-for="item in tableData"
            :key="item.id"
            :timestamp="item.time"
            placement="top"
          >
            <el-card>
              <h4>{{ item.title }}</h4>
              <p>{{ item.content }}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
        <el-empty v-else description="暂无消息" />
      </div>
    </el-drawer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      drawer: false,
      tableData: [
        {
          id: 1,
          title: "更新 Github 模板",
          content: "王小虎 提交于 2018/4/12 20:46",
          time: "2018/4/12",
        },
        {
          id: 2,
          title: "更新 Github 模板",
          content: "王小虎 提交于 2018/4/12 20:46",
          time: "2018/4/12",
        },
        {
          id: 3,
          title: "更新 Github 模板",
          content: "王小虎 提交于 2018/4/12 20:46",
          time: "2018/4/12",
        },
      ],
    };
  },
  methods: {
    handleTips() {
      this.drawer = true;
    },
  },
};
</script>
<style lang="scss" scoped>
.news-tips {
  ::v-deep .el-badge__content.is-fixed {
    top: 10px !important;
  }
  i {
    font-size: 20px;
  }
}
.tips-list {
  padding: 0 40px;
}
</style>
