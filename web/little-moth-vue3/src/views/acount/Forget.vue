<template>
  <div class="login">
    <div class="form-wrap color-white">
      <a-form
        ref="formRef"
        name="custom-validation"
        :model="formData"
        :rules="fromRules"
        @finish="handleFinish"
      >
        <a-form-item name="userName">
          <label class="color-white">用户名</label>
          <a-input v-model:value="formData.userName" autocomplete="off" />
        </a-form-item>
        <a-form-item name="passWord">
          <label class="color-white">密码</label>
          <a-input
            v-model:value="formData.passWord"
            type="password"
            autocomplete="off"
          />
        </a-form-item>
        <a-form-item name="passWords">
          <label class="color-white">确认密码</label>
          <a-input
            v-model:value="formData.passWords"
            type="password"
            autocomplete="off"
          />
        </a-form-item>
        <a-form-item name="code">
          <label class="color-white">验证码</label>
          <a-row :gutter="15">
            <a-col :span="14">
              <a-input
                v-model:value="formData.code"
                autocomplete="off"
                :maxlength="6"
              />
            </a-col>
            <a-col :span="10">
              <a-button
                type="primary"
                :loading="buttonState.loading"
                :disabled="buttonState.disabled"
                block
                @click="getCode"
              >
                {{ buttonState.textValue }}
              </a-button>
            </a-col>
          </a-row>
        </a-form-item>
        <!-- 滑动模块 -->
        <!-- <a-form-item>
          <Captcha />
        </a-form-item> -->
        <a-form-item>
          <a-button type="primary" html-type="submit" block>修改密码</a-button>
        </a-form-item>
      </a-form>
      <!-- 登录、注册 -->
      <div class="text-center fs-12">
        <router-link to="/login" class="color-white">登录</router-link> |
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
import { isPhone, isPassWord, isCode } from '@/utils/validate.js'
export default {
  name: 'Forget',
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
      const passWords = data.formData.passWords
      console.log(passWords, value)
      if (!value) {
        return Promise.reject('请输入密码')
      } else if (!isPassWord(value)) {
        return Promise.reject('密码格式为6-20的数字+字母')
      } else if (passWords && value && passWords !== value) {
        return Promise.reject('两次密码不一致')
      } else {
        return Promise.resolve()
      }
    }
    //   验证确认密码
    const cheakPassWords = async (_rule, value) => {
      const passWord = data.formData.passWord
      if (!value) {
        return Promise.reject('请再次输入密码')
      } else if (!isPassWord(value)) {
        return Promise.reject('密码格式为6-20的数字+字母')
      } else if (passWord && value && passWord !== value) {
        return Promise.reject('两次密码不一致')
      } else {
        return Promise.resolve()
      }
    }
    //   验证验证码
    const checkCode = async (_rule, value) => {
      if (!value) {
        return Promise.reject('请输入验证码')
      } else if (!isCode(value)) {
        return Promise.reject('请输入正确验证码')
      } else {
        return Promise.resolve()
      }
    }
    const data = reactive({
      // 表单数据
      formData: {
        userName: '',
        passWord: '',
        passWords: '',
        code: ''
      },
      //   表单验证
      fromRules: {
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
        ],
        passWords: [
          {
            required: true,
            validator: cheakPassWords,
            trigger: 'change'
          }
        ],
        code: [
          {
            required: true,
            validator: checkCode,
            trigger: 'change'
          }
        ]
      },
      //   获取验证码
      buttonState: {
        textValue: '获取验证码',
        loading: false,
        disabled: false,
        sec: 60,
        timer: null
      }
    })
    // 倒计时
    const getCode = () => {
      // 用户名不存在
      if (!data.formData.userName) {
        message.warning('用户名不能为空')
        return false
      }
      // 判断定时器是否存在
      data.buttonState.timer && clearInterval(data.buttonState.timer)
      //   开启定时器
      data.buttonState.timer = setInterval(() => {
        const s = data.buttonState.sec--
        data.buttonState.textValue = `${s}秒`
        if (s <= 0) {
          clearInterval(data.buttonState.timer)
          data.buttonState.textValue = `重新获取`
        }
      }, 1000)
    }
    // 表单提交
    const handleFinish = (values) => {
      console.log(values, data.formData)
    }
    return {
      ...toRefs(data),
      getCode,
      handleFinish
    }
  }
}
</script>
<style lang="scss"></style>
