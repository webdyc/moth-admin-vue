<template>
  <!-- 面包屑 -->
  <div>
    <a-breadcrumb>
      <a-breadcrumb-item v-for="(item, index) in routesList" :key="item.path">
        <span
          v-if="
            item.redirect === 'noRedirect' || index == routesList.length - 1
          "
          class="no-redirect"
          >{{ item.meta.title }}</span
        >
        <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>
      </a-breadcrumb-item>
    </a-breadcrumb>
  </div>
</template>
<script>
const { pathToRegexp, match, parse, compile } = require('path-to-regexp')
import { reactive, toRefs, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'

export default {
  setup() {
    const route = useRoute()
    const { currentRoute, push } = useRouter()
    const state = reactive({
      routesList: []
    })
    // 处理路由列表信息
    const getBreadcrumb = () => {
      // 仅显示带有meta的路由。标题
      const matched = currentRoute.value.matched.filter(
        (item) => item.meta && item.meta.title
      )
      state.routesList = matched.filter(
        (item) => item.meta && item.meta.title && item.meta.breadcrumb !== false
      )
    }
    // 监听路由跳转
    watch(
      () => route.path,
      () => {
        getBreadcrumb()
      },
      {
        immediate: true, // 是否初始化立即执行一次, 默认是false
        deep: true // 是否是深度监视, 默认是false
      }
    )
    // 处理路由地址
    const pathCompile = (path) => {
      const { params } = currentRoute.value.matched
      var toPath = compile(path)
      return toPath(params)
    }
    // 点击跳转路由
    const handleLink = (item) => {
      const { redirect, path } = item
      if (redirect) {
        push(redirect)
        return
      }
      push(pathCompile(path))
    }
    return {
      ...toRefs(state),
      handleLink
    }
  }
}
</script>
