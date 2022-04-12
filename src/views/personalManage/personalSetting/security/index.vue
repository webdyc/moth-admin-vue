<template>
  <div>
    <div class="title">安全设置</div>
    <div class="content">
      <div class="content-list">
        <div class="content-list-l">
          <div class="content-list-title">账户密码</div>
          <div class="content-list-sub-title">当前密码强度:强</div>
        </div>
        <el-button type="text">修改</el-button>
      </div>
      <div class="content-list">
        <div class="content-list-l">
          <div class="content-list-title">密保手机</div>
          <div class="content-list-sub-title">已绑定手机:185****4253</div>
        </div>
        <el-button type="text">修改</el-button>
      </div>
      <div class="content-list">
        <div class="content-list-l">
          <div class="content-list-title">密保问题</div>
          <div class="content-list-sub-title">
            未设置密保问题，密保问题可有效保护账户安全
          </div>
        </div>
        <el-button type="text">修改</el-button>
      </div>
      <div class="content-list">
        <div class="content-list-l">
          <div class="content-list-title">备用邮箱</div>
          <div class="content-list-sub-title">已绑定邮箱:dyc***gmail.com</div>
        </div>
        <el-button type="text">修改</el-button>
      </div>
      <div class="content-list">
        <div class="content-list-l">
          <div class="content-list-title">MFA 设备</div>
          <div class="content-list-sub-title">
            未绑定 MFA 设备，绑定后，可以进行二次确认
          </div>
        </div>
        <el-button type="text">修改</el-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>
<style lang="scss" scoped>
.title {
  margin-bottom: 12px;
  color: rgba(0, 0, 0, 0.85);
  font-weight: 500;
  font-size: 20px;
  line-height: 28px;
}
.content {
  padding-top: 12px;
  .content-list {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #f0f0f0;
    .content-list-l {
      flex: 1;
      padding: 12px 0;
      color: rgba(0, 0, 0, 0.85);
      .content-list-title {
        color: rgba(0, 0, 0, 0.85);
        transition: all 0.3s;
        font-weight: 500;
        margin-bottom: 4px;
      }
      .content-list-sub-title {
        color: rgba(0, 0, 0, 0.45);
        font-size: 14px;
        line-height: 1.5715;
      }
    }
  }
  .content-list:last-child {
    border-bottom: none;
  }
}
</style>
