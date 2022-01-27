<template>
  <div class="app-box">
    <el-row class="account" :gutter="20">
      <el-col :span="7">
        <el-card class="account-center">
          <!-- 用户信息 - 头部 -->
          <div class="account-center-avatarHolder">
            <el-image :src="user_info.photo" fit="fill" class="avatar" />
            <div class="username">{{ user_info.userName }}</div>
            <div class="bio">求知若愚，求贤若饥</div>
          </div>
          <!-- 用户信息 - 详情 -->
          <div class="account-center-detail">
            <p><svg-icon class="mr-1" icon-class="tree" />web前端</p>
            <p><svg-icon class="mr-1" icon-class="tree" />技术部</p>
            <p><svg-icon class="mr-1" icon-class="tree" />123456</p>
          </div>
          <!-- 分割线 -->
          <el-divider />
          <!-- 标签 -->
          <div class="account-center-tags">
            <div class="tagsTitle">标签</div>
            <div class="mt-1">
              <CustomTags
                :dataList="navTags"
                @callBack="tagsChange"
              ></CustomTags>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="17">
        <!-- 用户信息卡片 -->
        <el-card class="account-center">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="用户管理" name="first">用户管理</el-tab-pane>
            <el-tab-pane label="配置管理" name="second">配置管理</el-tab-pane>
            <el-tab-pane label="角色管理" name="third">角色管理</el-tab-pane>
            <el-tab-pane label="定时任务补偿" name="fourth">
              定时任务补偿
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import CustomTags from "@/components/CustomTags";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      activeName: "first",
      navTags: ["标签一", "标签二", "标签三"],
    };
  },
  watch: {},
  computed: {
    ...mapGetters(["user_info"]),
  },
  components: { CustomTags },
  methods: {
    handleClick() {},
    // 自定义标签返回函数
    tagsChange(data) {
      console.log(data);
      this.navTags = data;
    },
  },
};
</script>
<style lang="scss" scoped>
.account {
  background: #f0f2f5;
  .account-center {
    .account-center-avatarHolder {
      margin-bottom: 24px;
      text-align: center;
      .avatar {
        width: 104px;
        height: 104px;
        margin: 0 auto;
        margin-bottom: 20px;
        overflow: hidden;
        border-radius: 50%;
        img {
          width: 100%;
          height: 100%;
        }
      }
      .username {
        margin-bottom: 4px;
        color: rgba(0, 0, 0, 0.85);
        font-weight: 500;
        font-size: 20px;
        line-height: 28px;
      }
    }
    .account-center-detail {
      p {
        position: relative;
        margin-bottom: 8px;
        padding-left: 26px;
        .anticon {
          position: absolute;
          top: 4px;
          left: 0;
          width: 14px;
          height: 14px;
        }
      }
    }
    .account-center-tags {
    }
  }
  .tabs-center {
    .ant-card-body {
      padding: 0 !important;
    }
  }
}
</style>
