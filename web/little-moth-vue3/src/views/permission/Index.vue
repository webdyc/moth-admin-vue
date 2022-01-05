<template>
  <div class="app-content">
    <div :key="key" style="margin-top: 30px">
      <div v-permission="['admin']">
        <span class="permission-alert">
          只有
          <a-tag color="processing" size="small">admin</a-tag>可见
        </span>
        <a-tag color="default" size="small">v-permission="['admin']"</a-tag>
      </div>

      <div v-permission="['editor']">
        <span class="permission-alert">
          只有
          <a-tag color="processing" size="small">editor</a-tag>可见
        </span>
        <a-tag color="default" size="small">v-permission="['editor']"</a-tag>
      </div>

      <div>
        <span v-permission="['admin', 'editor']" class="permission-alert">
          拥有
          <a-tag color="processing" size="small">admin</a-tag> 或
          <a-tag color="processing" size="small">editor</a-tag> 可见
        </span>
        <a-tag v-permission="['admin', 'editor']" color="default" size="small"
          >v-permission="['admin','editor']"</a-tag
        >
      </div>
    </div>
    <div :key="'checkPermission' + key" style="margin-top: 60px">
      <aside>
        在某些情况下，不适合使用 v-permission。例如：Element-UI 的 el-tab 或
        el-table-column 以及其它动态渲染 dom 的场景。你只能通过手动设置 v-if
        来实现。
      </aside>
      <a-tabs v-model:activeKey="activeKey" style="width: 550px" type="card">
        <a-tab-pane key="1" tab="Admin" v-if="checkPermission(['admin'])">
          Admin 才能查看
          <a-tag color="default" size="small"
            >v-if="checkPermission(['admin'])"</a-tag
          >
        </a-tab-pane>
        <a-tab-pane key="2" tab="Editor" v-if="checkPermission(['editor'])">
          Editor 才能查看
          <a-tag color="default" size="small"
            >v-if="checkPermission(['editor'])"</a-tag
          >
        </a-tab-pane>
        <a-tab-pane
          key="3"
          tab="Admin-OR-Editor"
          v-if="checkPermission(['admin', 'editor'])"
        >
          拥有 admin 或者 editor 能查看
          <a-tag color="default" size="small"
            >v-if="checkPermission(['admin','editor'])"</a-tag
          >
        </a-tab-pane>
      </a-tabs>
    </div>
  </div>
</template>

<script>
import checkPermission from '@/utils/permission' // 权限判断函数
import { reactive, toRefs } from '@vue/reactivity'

export default {
  name: 'DragDialogDemo',
  setup() {
    const state = reactive({
      key: 1, // 为了能每次切换权限的时候重新初始化指令: false,
      activeKey: '1'
    })

    const handleRolesChange = () => {
      state.key++
    }
    return {
      ...toRefs(state),
      checkPermission,
      handleRolesChange
    }
  }
}
</script>
<style lang="scss" scoped>
.app-content {
  ::v-deep .permission-alert {
    width: 320px;
    margin-top: 15px;
    background-color: #f0f9eb;
    color: #67c23a;
    padding: 8px 16px;
    border-radius: 4px;
    display: inline-block;
    margin-right: 15px;
  }
  ::v-deep .permission-sourceCode {
    margin-left: 15px;
  }
  ::v-deep .permission-tag {
    background-color: #ecf5ff;
  }
}
</style>
