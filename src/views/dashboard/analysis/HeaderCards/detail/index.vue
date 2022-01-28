<template>
  <div class="card-box">
    <div class="card-header">
      <span class="text-secondary">{{ title }}</span>
      <i class="el-icon-warning-outline cursor-pointer text-secondary"></i>
    </div>
    <div class="card-content text-dark">{{ count }}</div>
    <div class="card-charts">
      <slot name="charts"></slot>
    </div>
    <div class="card-footer text-dark">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "vueName",
  props: {
    title: {
      type: String,
      default: "",
    },
    count: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      msg: "Welcome to your vueName",
    };
  },
};
</script>

<style scoped lang="scss">
.card-box {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 14px;
    line-height: 22px;
  }
  .card-content {
    margin-top: 4px;
    font-size: 30px;
    line-height: 38px;
  }
  .card-charts {
    height: 50px;
    line-height: 50px;
    span {
      margin-right: 15px;
    }
  }
  .card-footer {
    border-top: 1px solid #f0f0f0;
    margin-top: 20px;
    padding-top: 9px;
    font-size: 14px;
    span {
      margin-right: 15px;
    }
  }
}
</style>
