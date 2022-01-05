<template>
  <!-- 菜单栏Logo -->
  <div class="sidebar-logo-container" :class="{ collapsed: collapsed }">
    <transition name="sidebarLogoFade">
      <router-link
        v-if="collapsed"
        key="collapse"
        class="sidebar-logo-link"
        to="/"
      >
        <img v-if="logo" :src="logo" class="sidebar-logo" />
        <h1 v-else class="sidebar-title">{{ title }}</h1>
      </router-link>
      <router-link v-else key="expand" class="sidebar-logo-link" to="/">
        <img v-if="logo" :src="logo" class="sidebar-logo" />
        <h1 class="sidebar-title">{{ title }}</h1>
      </router-link>
    </transition>
  </div>
</template>
<script>
import { reactive, toRefs, computed } from 'vue'
import defaultSettings from '@/settings'
import { useStore } from 'vuex'
export default {
  name: 'SidebarLogo',
  setup() {
    const store = useStore()
    // 数据
    const state = reactive({
      title: defaultSettings.title,
      logo: defaultSettings.logo
    })

    // // 菜单折叠按钮状态
    const collapsed = computed(() => {
      return store.getters.sidebar.opened
    })

    return {
      ...toRefs(state),
      collapsed
    }
  }
}
</script>
<style lang="scss" scoped>
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 50px;
  line-height: 50px;
  background: #2b2f3a;
  text-align: center;
  overflow: hidden;

  & .sidebar-logo-link {
    height: 100%;
    width: 100%;

    & .sidebar-logo {
      width: 32px;
      height: 32px;
      vertical-align: middle;
      margin-right: 12px;
    }

    & .sidebar-title {
      display: inline-block;
      margin: 0;
      color: #fff;
      font-weight: 600;
      line-height: 50px;
      font-size: 14px;
      font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
      vertical-align: middle;
    }
  }

  &.collapse {
    .sidebar-logo {
      margin-right: 0px;
    }
  }
}
</style>
