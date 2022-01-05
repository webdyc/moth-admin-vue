<template>
  <a-sub-menu :key="menu.path">
    <template #title>
      <span class="anticon">
        <svg-icon
          :icon-name="menu.meta && menu.meta.icon"
          class-name="aside-svg"
        />
      </span>
      <span>{{ menu.meta && $t(`aside_menu.${menu.meta.langTitle}`) }}</span>
    </template>
    <template v-if="menu.children && menu.children.length">
      <template v-for="item in menu.children">
        <template v-if="!item.hidden">
          <!-- 不存在子级的栏目 -->
          <a-menu-item v-if="!item.children" :key="item.path">
            <router-link :to="item.path">{{
              item.meta && $t(`aside_menu.${item.meta.langTitle}`)
            }}</router-link>
          </a-menu-item>
          <!-- 存在子级栏目 -->
          <template v-else>
            <SidebarItem :key="item.path" :menu="item" />
          </template>
        </template>
      </template>
    </template>
  </a-sub-menu>
</template>
<script>
export default {
  name: 'SidebarItem',
  components: {},
  props: {
    menu: {
      type: Object, // array
      default: () => ({})
    }
  },
  setup() {}
}
</script>
<style lang="scss" scoped>
.aside-menu {
  i.icon {
    margin-right: 5px;
    opacity: 0.6;
  }
  .ant-menu-submenu-open {
    > div {
      i.icon {
        opacity: 1;
      }
    }
  }
}
</style>
