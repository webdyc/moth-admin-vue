<template>
  <!-- 导航栏 -->
  <div class="navbar">
    <div class="left-menu flex align-center">
      <!-- 菜单折叠按钮 -->
      <div class="menu-unfold-outlined">
        <menu-unfold-outlined
          v-if="collapsed"
          class="trigger"
          @click="toggleSideBar"
        />
        <menu-fold-outlined v-else class="trigger" @click="toggleSideBar" />
      </div>
      <!-- 面包屑 -->
      <Breadcrumb class="breadcrumb-container ml-2"></Breadcrumb>
    </div>
    <div class="right-menu flex">
      <!-- 用户信息 -->
      <a-dropdown class="right-menu-item">
        <div class="avatar-wrapper cursor-pointer">
          <img :src="avatar" alt="" class="user-avatar" />
          <span>{{ username }}</span>
        </div>
        <!-- 用户信息弹出框 -->
        <template #overlay>
          <a-menu>
            <a-menu-item key="0">
              <router-link to="/"> 回到主页 </router-link>
            </a-menu-item>
            <a-menu-item key="1">
              <a
                target="_blank"
                href="https://github.com/webdyc/vue3-antd-admin"
              >
                Github
              </a>
            </a-menu-item>
            <a-menu-item key="3">
              <a target="_blank" href="https://webdyc.com/"> 博客地址 </a>
            </a-menu-item>
            <a-menu-divider />
            <a-menu-item key="4" @click="logout">
              <div>退出登录</div>
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
      <!-- 国际化 -->
      <a-dropdown class="right-menu-item" :trigger="['click']">
        <div class="avatar-wrapper cursor-pointer">
          <svg-icon :icon-name="'international'" class-name="navbar-svg" />
        </div>
        <!-- 用户信息弹出框 -->
        <template #overlay>
          <a-menu class="right-menu-item-lang">
            <a-menu-item
              v-for="(item, index) in lang"
              :key="index"
              @click="toggleLang(item.value)"
            >
              <div class="align-center flex">
                <svg-icon :icon-name="item.name" class-name="language-svg" />
                <div :class="{ current: lang_current == item.value }">
                  {{ item.label }}
                </div>
              </div>
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </div>
  </div>
</template>
<script>
import Breadcrumb from '@/components/Breadcrumb'
import { computed, reactive, toRefs } from '@vue/reactivity'
import { useStore } from 'vuex'
import {
  DownOutlined,
  MenuFoldOutlined,
  MenuUnfoldOutlined
} from '@ant-design/icons-vue'
// 语言
import { useI18n } from 'vue-i18n'

export default {
  name: 'Navbar',
  components: {
    Breadcrumb,
    DownOutlined,
    MenuFoldOutlined,
    MenuUnfoldOutlined
  },
  setup() {
    // 扩展语言
    const { locale } = useI18n({ useScope: 'global' })
    const store = useStore()
    const state = reactive({
      ...store.state.user.info,
      lang: [
        { name: 'China', label: '简体中文', value: 'ch' },
        { name: 'English', label: 'English', value: 'en' }
      ],
      lang_current: 'ch'
    })
    // 获取菜单展开收起状态
    const collapsed = computed(() => {
      return store.getters.sidebar.opened
    })
    // 更改展开收起状态
    const toggleSideBar = async () => {
      store.dispatch('app/toggleSideBar')
    }
    // 设置语言
    const toggleLang = (lang) => {
      store.dispatch('settings/changeSetting', { key: 'language', value: lang })
      locale.value = lang
      state.lang_current = lang
    }
    // 登出
    const logout = async () => {
      await store.dispatch('user/logout')
      // 这个系统再切换用户时，路由不会动态更新。所以需要在登出函数中加上location.reload();刷新浏览器。
      location.reload()
    }
    return {
      ...toRefs(state),
      collapsed,
      toggleSideBar,
      toggleLang,
      logout
    }
  }
}
</script>
<style lang="scss" scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  padding: 0 16px;
  height: 100%;
  .trigger {
    font-size: 20px;
    cursor: pointer;
    transition: color 0.3s;
    margin-left: 16px;
  }
  .trigger:hover {
    color: #1890ff;
  }
  .right-menu {
    .right-menu-item {
      padding: 0 16px;
    }
    .avatar-wrapper {
      position: relative;
      display: flex;
      align-items: center;
      .user-avatar {
        cursor: pointer;
        width: 24px;
        height: 24px;
        border-radius: 50px;
        margin-right: 8px;
      }
      .user-name {
        display: inline-block;
        align-content: center;
        align-items: center;
        height: 40px;
        margin-left: 6px;
        line-height: 40px;
        cursor: pointer;
      }
      .el-icon-caret-bottom,
      .el-icon-caret-top {
        cursor: pointer;
        position: absolute;
        right: -20px;
        // top: 25px;
        font-size: 12px;
      }
    }
  }
}
.current {
  color: #1890ff;
}
</style>
