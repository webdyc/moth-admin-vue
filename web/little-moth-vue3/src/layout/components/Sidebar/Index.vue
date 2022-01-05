<template>
  <!-- 菜单栏 -->
  <!-- 菜单栏Logo -->
  <Logo />
  <!-- 菜单栏列表 -->
  <a-menu
    v-model:openKeys="openKeys"
    v-model:selectedKeys="selectedKeys"
    class="aside-menu"
    mode="inline"
    theme="dark"
  >
    <template v-for="item in permissionRoutes">
      <template v-if="!item.hidden">
        <!--这里是一级-->
        <a-menu-item v-if="hasOnlyChildren(item)" :key="item.path">
          <router-link :to="item.path">
            <span class="anticon">
              <svg-icon
                :icon-name="item.meta && item.meta.icon"
                class-name="aside-svg"
              />
            </span>
            <span>
              {{ item.meta && $t(`aside_menu.${item.meta.langTitle}`) }}</span
            >
          </router-link>
        </a-menu-item>
        <!--这里是子级-->
        <template v-else>
          <SidebarItem :key="item.path" :menu="item" />
        </template>
      </template>
    </template>
  </a-menu>
</template>
<script>
import { computed, reactive, toRefs } from 'vue'
import SidebarItem from './SidebarItem.vue'
import Logo from './Logo'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'Sidebar',
  components: { SidebarItem, Logo },
  setup() {
    const { currentRoute } = useRouter()
    const store = useStore()
    // 数据
    const state = reactive({
      // selectedKeys: [],
      // openKeys: [],
      logo: '',
      logo_min: ''
    })
    // vuex储存的路由列表
    const permissionRoutes = computed(() => store.getters.permission_routes)

    // 选择菜单
    const selectedKeys = computed(() =>
      currentRoute.value.matched.map((item) => item.path)
    )
    // 展开/关闭菜单
    const openKeys = computed(() =>
      currentRoute.value.matched.map((item) => item.path)
    )
    // 检测是否只有一个子路由
    const hasOnlyChildren = (data) => {
      // 不存在子级的情况
      if (!data.children) {
        return false
      }
      // 过滤隐藏的子级路由
      const routers = data.children.filter((item) => !item.hidden)
      // 判断最终结果
      if (routers.length === 1) {
        return true
      }
      return false
    }

    return {
      ...toRefs(state),
      permissionRoutes,
      selectedKeys,
      openKeys,
      hasOnlyChildren
    }
  }
}
</script>
<style lang="scss" scoped></style>
