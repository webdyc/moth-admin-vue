<template>
  <div class="login">
    <div class="form-wrap color-white">
      <a-form
        ref="ruleForm"
        name="custom-validation"
        :model="dataFrom"
        :rules="dataFromRules"
        @finish="handleFinish"
      >
        <a-form-item ref="username" name="userName">
          <label class="color-white">用户名</label>
          <a-input
            v-model:value="dataFrom.userName"
            :disabled="input_diabled.username"
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
        <a-form-item ref="passwords" name="passWords">
          <label class="color-white">确认密码</label>
          <a-input-password
            v-model:value="dataFrom.passWords"
            type="password"
            autocomplete="off"
            @change="
              () => {
                $refs.passwords.onFieldChange()
              }
            "
          />
        </a-form-item>
        <a-form-item name="code">
          <label class="color-white">验证码</label>
          <a-row :gutter="15">
            <a-col :span="14">
              <a-input
                v-model:value="dataFrom.code"
                autocomplete="off"
                :maxlength="6"
              />
            </a-col>
            <a-col :span="10">
              <a-button
                type="primary"
                :loading="buttonState.loading"
                :disabled="buttonState.disabled_code_button"
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
          <a-button type="primary" html-type="submit" block>注册</a-button>
        </a-form-item>
      </a-form>
      <!-- 登录、注册 -->
      <div class="text-center fs-12">
        <router-link to="/login" class="color-white">登录</router-link> |
        <router-link to="/forget" class="color-white">忘记密码</router-link>
      </div>
    </div>
  </div>
</template>

<script>
// 滑动组件导入
// import Captcha from '@/components/Captcha/index.vue'
import { message } from 'ant-design-vue'
import { reactive, toRefs } from '@vue/reactivity'
import { useRouter } from 'vue-router'
import { isPhone, isPassWord, checkCode as isCode } from '@/utils/validate.js'
import { Send, ChekcUsername, Register } from '@/api/acount'
export default {
  name: 'Ragister',
  // components: { Captcha },
  setup() {
    //   验证用户名
    const checkUserName = async (_rule, value) => {
      if (!value) {
        state.buttonState.disabled_code_button = true
        return Promise.reject('请输入用户名')
      } else if (!isPhone(value)) {
        state.buttonState.disabled_code_button = true
        return Promise.reject('请输入正确的手机号')
      } else {
        // 解锁验证码按钮
        state.buttonState.disabled_code_button = false
        return Promise.resolve()
      }
    }
    //   验证密码
    const cheakPassWord = async (_rule, value) => {
      const passWords = state.dataFrom.passWords
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
      const passWord = state.dataFrom.passWord
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
    // 路由扩展
    const { replace } = useRouter()
    const state = reactive({
      // 表单数据
      dataFrom: {
        userName: '',
        passWord: '',
        passWords: '',
        code: ''
      },
      //   表单验证
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
      // 表单禁用
      input_diabled: {
        username: false
      },
      //   获取验证码
      buttonState: {
        textValue: '获取验证码',
        loading: false,
        disabled_code_button: true,
        sec: 60,
        timer: null
      }
    })
    // 获取验证码
    const getCode = async () => {
      // 检测用户名是否重复
      const usernameStatus = await checkUsernameFn() // 等待哪个接口，await就给谁用
      if (usernameStatus) {
        message.warning('用户名已存在')
        return false
      }
      // 按钮加载
      state.buttonState.loading = true
      // 按钮文本
      state.buttonState.textValue = '发送中'
      const { content } = await Send({
        username: state.dataFrom.userName,
        type: 'Register'
      })
      if (content) {
        // 关闭按钮加载
        state.buttonState.loading = false
        // 按钮禁用
        state.buttonState.disabled_code_button = true
        state.dataFrom.code = content
        // 倒计时
        countDown()
      } else {
        state.buttonState.textValue = `重新获取`
        // 按钮激活
        state.buttonState.disabled_code_button = false
      }
    }
    // 检测用户名是否重复
    const checkUsernameFn = () => {
      // 禁用用户名输入框状态
      state.input_diabled.username = true
      return ChekcUsername({ username: state.dataFrom.userName })
        .then((res) => {
          // 用户名状态
          const user_status = res.content.user
          // 发送验证码按钮状态
          state.buttonState.disabled_code_button = user_status
          // 用户名输入框状态
          state.input_diabled.username = false
          return user_status
        })
        .catch((error) => {
          console.log(error)
          state.input_diabled.username = false
        })
    }
    // 倒计时
    const countDown = () => {
      // 更新时间
      state.buttonState.sec = 60
      // 判断定时器是否存在
      state.buttonState.timer && clearInterval(state.buttonState.timer)
      //   开启定时器
      state.buttonState.timer = setInterval(() => {
        const s = state.buttonState.sec--
        state.buttonState.textValue = `${s}秒`
        if (s <= 0) {
          clearInterval(state.buttonState.timer)
          state.buttonState.textValue = `重新获取`
          // 按钮激活
          state.buttonState.disabled_code_button = false
        }
      }, 1000)
    }
    // 表单提交
    const handleFinish = (values) => {
      const requestData = {
        username: values.userName,
        password: values.passWord,
        code: values.code
      }
      // 注册接口
      Register(requestData).then((response) => {
        const code = response.content.code
        const msg = code ? '注册成功' : response.msg
        message.success(msg)
        // 注册成功跳转登录页面，不需要返回上一个记录
        replace({
          name: 'Login'
        })
      })
    }
    return {
      ...toRefs(state),
      getCode,
      handleFinish
    }
  }
}
</script>
<style lang="scss"></style>
