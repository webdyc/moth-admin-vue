<template>
  <div class="login-container">
    <div class="center">
      <div class="title-container">
        <h3 class="title">{{ title }}</h3>
      </div>
      <el-form
        ref="dataFrom"
        :model="dataFrom"
        :rules="dataFromRules"
        class="login-form"
        label-position="left"
      >
        <!-- 用户名 -->
        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input v-model.trim="dataFrom.username" type="text" trim />
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="dataFrom.password"
            :type="passwordType"
            placeholder=""
            name="password"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon
              :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"
            />
          </span>
        </el-form-item>
        <!-- 验证码 -->
        <el-form-item>
          <el-row :gutter="15">
            <el-col :span="14">
              <el-input
                v-model="dataFrom.code"
                type="text"
                @keyup.enter.native="handleLogin"
              />
            </el-col>
            <el-col :span="10">
              <div class="veriCodeImg" @click="getCode">
                <img :src="codeUrl" />
              </div>
            </el-col>
          </el-row>
        </el-form-item>
        <el-button
          class="login-btn"
          :loading="loading"
          type="primary"
          @click.native.prevent="handleLogin"
        >
          登 录
        </el-button>
        <div class="tips">
          <span class="mr-2">用户名: admin</span>
          <span> 密码: 123456</span>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import defaultSettings from "@/settings";
import { sendCode } from "@/api/acount";
// PersonForm的类
class PersonForm {
  // 值
  constructor() {
    // 客户姓名
    this.username = "admin";
    // 客户号码
    this.password = "123456";
    // 验证码
    this.code = "gstd";
    // 客户来源
    this.uuid = "";
  }
  // 验证方法
  static getRule() {
    return {
      username: [
        { required: true, message: "请输入用户名称", trigger: "blur" },
      ],
      password: [{ required: true, message: "请输入密码", trigger: "blur" }],
    };
  }
}
export default {
  name: "Login",
  data() {
    return {
      // 系统名称
      title: defaultSettings.title,
      // 登录表单
      dataFrom: new PersonForm(),
      // 表单验证
      dataFromRules: PersonForm.getRule(),
      // 验证码
      codeUrl: "",
      // 登录按钮状态
      loading: false,
      // 密码框状态
      passwordType: "password",
      // 登录页之前路由
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  mounted() {
    this.getCode();
  },
  methods: {
    // 显示、隐藏密码
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    // 获取验证码
    async getCode() {
      let { code, data } = await sendCode();
      if (code === 200) {
        this.codeUrl = "data:image/png;base64," + data.img;
        this.dataFrom.uuid = data.uuid;
      }
    },
    // 登录
    async handleLogin() {
      this.$refs.dataFrom.validate(async (valid) => {
        if (valid) {
          this.loading = true;
          console.log(2);
          try {
            let token = await this.$store.dispatch("user/Login", this.dataFrom);
            if (token) {
              this.$router.push({ path: this.redirect || "/" });
              this.loading = false;
            } else {
              this.getCode();
              this.loading = false;
            }
          } catch (error) {
            this.getCode();
            this.loading = false;
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
$width: 200vmax;
$height: 200vmax;
$excursion_top: 50%;
$excursion_left: 50%;
/* 背景样式 */
.login-container {
  background-image: linear-gradient(
    to right,
    rgb(160, 172, 188),
    rgb(117, 151, 209)
  );
  position: absolute;
  width: 100%;
  height: 100%;
  overflow: hidden;

  .center {
    transition-delay: 0s;

    // transition-delay: 0.2s;
  }
}
</style>

<style lang="scss">
// /* 修复input 背景不协调 和光标变色 */
$light_gray: rgba(0, 0, 0.85);
$cursor: rgba(0, 0, 0.85);
@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
// /* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    width: 85%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      caret-color: $cursor;
      // &:-webkit-autofill {
      //   box-shadow: 0 0 0px 1000px $bg inset !important;
      //   -webkit-text-fill-color: $cursor !important;
      // }
    }
  }
  .el-form-item {
    border: 1px solid #dcdfe6;
    border-radius: 5px;
    color: $light_gray;
    .el-form-item__content {
      line-height: 36px;
    }
  }
}
</style>

<style lang="scss" scoped>
$dark_gray: rgba(0, 0, 0, 0.85);
$excursion_top: 50%;
$excursion_left: 50%;
.center {
  width: 500px;
  height: 400px;
  display: flex;
  flex-direction: column;
  // justify-content: center;
  align-items: center;
  background: rgb(250, 246, 246);
  position: absolute;
  transform: translate(-50%, -50%);
  top: $excursion_top;
  left: $excursion_left;
  border-radius: 10px;
  backdrop-filter: blur(10px);
  border: 1px solid #ccc;
  padding: 0 35px;
  z-index: 10;
  .login-form {
    position: relative;
    width: 100%;
    overflow: hidden;
  }
  .tips {
    font-size: 14px;
    margin-bottom: 10px;
  }
  .svg-container {
    padding: 0 5px 0 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .title-container {
    position: relative;
    .title {
      font-size: 20px;
      color: $dark_gray;
      margin: 20px auto 10px auto;
      text-align: center;
      font-weight: bold;
    }
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
  .veriCodeImg {
    width: 100%;
    height: 40px;
    cursor: pointer;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .login-btn {
    width: 100%;
    margin-bottom: 15px;
  }
}
::v-deep .el-tabs__content {
  min-height: 195px;
}
.lrMainWrap {
  width: 100%;
}
.getCode {
  // background: #ccc;
  // padding-bottom: 0;
  // margin-bottom: 0;
  // height: 40px;
  // text-align: center;
  // cursor: pointer;
  ::v-deep el-button {
    width: 100%;
    padding: 0;
    margin: 0;
  }
}
.h20 {
  height: 20px;
}
.pointOut {
  font-size: 14px;
  line-height: 26px;
}
.warning {
  color: red;
  font-size: 14px;
  padding-top: 20px;
  i {
    margin-right: 6px;
    font-size: 16px;
  }
}
</style>
