<template>
  <div class="login">
    <div class="form-wrap color-white">
      <div class="title mb-2">{{ title }}</div>
      <a-form
        ref="formRef"
        name="custom-validation"
        :model="dataFrom"
        :rules="dataFromRules"
        @finish="handleFinish"
      >
        <a-form-item ref="username" name="userName">
          <label class="color-white">用户名</label>
          <a-input
            v-model:value="dataFrom.userName"
            type="text"
            autocomplete="off"
            @change="
              () => {
                $refs.username.onFieldChange()
              }
            "
          />
        </a-form-item>
        <a-form-item ref="password" name="passWord">
          <label class="color-white">密码</label>
          <a-input-password
            v-model:value="dataFrom.passWord"
            autocomplete="off"
            @change="
              () => {
                $refs.password.onFieldChange()
              }
            "
          />
        </a-form-item>
        <!-- 滑动模块 -->
        <!-- <a-form-item>
          <Captcha />
        </a-form-item> -->
        <a-form-item>
          <a-button type="primary" html-type="submit" block>立即登录</a-button>
        </a-form-item>
      </a-form>
      <!-- 忘记密码、注册 -->
      <div class="text-center fs-12">
        <router-link to="/forget" class="color-white">忘记密码</router-link> |
        <router-link to="/ragister" class="color-white">注册</router-link>
      </div>
    </div>
  </div>
</template>

<script>
// 滑动组件导入
// import Captcha from '@/components/Captcha/index.vue'
import { message } from 'ant-design-vue'
import { reactive, toRefs } from '@vue/reactivity'
import { isPhone, isPassWord } from '@/utils/validate.js'
import defaultSettings from '@/settings.js'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'

export default {
  name: 'Login',
  // components: { Captcha },
  setup() {
    //   验证用户名
    const checkUserName = async (_rule, value) => {
      if (!value) {
        return Promise.reject('请输入用户名')
      } else if (!isPhone(value)) {
        return Promise.reject('请输入正确的手机号')
      } else {
        return Promise.resolve()
      }
    }
    //   验证密码
    const cheakPassWord = async (_rule, value) => {
      if (!value) {
        return Promise.reject('请输入密码')
      } else if (!isPassWord(value)) {
        return Promise.reject('密码格式为6-20的数字+字母')
      } else {
        return Promise.resolve()
      }
    }

    const store = useStore()
    const router = useRouter()
    const state = reactive({
      title: defaultSettings.title,
      dataFrom: {
        userName: '18510164253',
        passWord: 'dyc1lyf'
      },
      // 表单验证
      dataFromRules: {
        userName: [
          {
            required: true,
            validator: checkUserName,
            trigger: 'change'
          }
        ],
        passWord: [
          {
            required: true,
            validator: cheakPassWord,
            trigger: 'change'
          }
        ]
      }
    })
    // 表单提交
    const handleFinish = (values) => {
      store.dispatch('user/login', values).then(() => {
        // 注册成功跳转登录页面，不需要返回上一个记录
        router.replace('/home')
      })
    }
    return {
      ...toRefs(state),
      handleFinish
    }
  }
}
</script>
<style lang="scss">
.login {
  width: 100vw;
  height: 100vh;
  background: #282828;
  display: flex;
  justify-content: center;
  align-items: center;
  .title {
    font-size: 20px;
    text-align: center;
  }
  .form-wrap {
    width: 285px;
    // margin: auto;
    color: #ffffff;
    label {
      display: block;
      font-size: 14px;
    }
  }
}
</style>
