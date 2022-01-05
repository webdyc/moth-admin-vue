<template>
  <!-- 框架主页 -->
  <a-layout id="layout-main">
    <!-- 侧边栏 -->
    <a-layout-sider
      v-model:collapsed="collapsed"
      :trigger="null"
      collapsible
      :width="220"
    >
      <Sidebar />
    </a-layout-sider>
    <!-- 右侧内容区 -->
    <a-layout>
      <!-- 头部导航栏 -->
      <a-layout-header>
        <Navbar />
      </a-layout-header>
      <!-- 视图区域 -->
      <a-layout-content>
        <AppMain />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
import { computed, reactive, ref, toRefs } from '@vue/reactivity'
import Navbar from './components/Navbar'
import AppMain from './components/AppMain'
import Sidebar from './components/Sidebar/Index'
import { useStore } from 'vuex'

export default {
  name: 'Layout',
  components: {
    AppMain,
    Navbar,
    Sidebar
  },
  setup() {
    const store = useStore()
    // 菜单折叠按钮状态
    const collapsed = computed(() => {
      return store.getters.sidebar.opened
    })
    return {
      collapsed
    }
  }
}
</script>
<style lang="scss" scoped>
#layout-main {
  min-height: 100vh;
}
// 导航栏头部
.ant-layout-header {
  background: #fff;
  padding: 0;
  height: 50px;
  line-height: 50px;
}

// 视图区内容
.ant-layout-content {
  margin: 24px 16px;
}
</style>
