<template>
  <div class="app-content">
    <div :key="key" style="margin-top: 30px">
      <div>
        <span v-permission="['admin']" class="permission-alert">
          只有
          <el-tag class="permission-tag" size="small">admin</el-tag> 可见
        </span>
        <el-tag
          v-permission="['admin']"
          class="permission-sourceCode"
          type="info"
        >
          v-permission="['admin']"
        </el-tag>
      </div>

      <div>
        <span v-permission="['editor']" class="permission-alert">
          只有
          <el-tag class="permission-tag" size="small">editor</el-tag> 可见
        </span>
        <el-tag
          v-permission="['editor']"
          class="permission-sourceCode"
          type="info"
        >
          v-permission="['editor']"
        </el-tag>
      </div>

      <div>
        <span v-permission="['admin', 'editor']" class="permission-alert">
          拥有
          <el-tag class="permission-tag" size="small">admin</el-tag> 或
          <el-tag class="permission-tag" size="small">editor</el-tag> 可见
        </span>
        <el-tag
          v-permission="['admin', 'editor']"
          class="permission-sourceCode"
          type="info"
        >
          v-permission="['admin','editor']"
        </el-tag>
      </div>
    </div>
    <div :key="'checkPermission' + key" style="margin-top: 60px">
      <aside>
        在某些情况下，不适合使用 v-permission。例如：Element-UI 的 el-tab 或
        el-table-column 以及其它动态渲染 dom 的场景。你只能通过手动设置 v-if
        来实现。
      </aside>

      <el-tabs type="border-card" style="width: 550px">
        <el-tab-pane v-if="checkPermission(['admin'])" label="Admin">
          Admin 才能查看
          <el-tag class="permission-sourceCode" type="info">
            v-if="checkPermission(['admin'])"
          </el-tag>
        </el-tab-pane>

        <el-tab-pane v-if="checkPermission(['editor'])" label="Editor">
          Editor 才能查看
          <el-tag class="permission-sourceCode" type="info">
            v-if="checkPermission(['editor'])"
          </el-tag>
        </el-tab-pane>

        <el-tab-pane
          v-if="checkPermission(['admin', 'editor'])"
          label="Admin-OR-Editor"
        >
          拥有 admin 或者 editor 能查看
          <el-tag class="permission-sourceCode" type="info">
            v-if="checkPermission(['admin','editor'])"
          </el-tag>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import permission from "@/directive/permission/index.js"; // 权限判断指令
import checkPermission from "@/utils/permission"; // 权限判断函数

export default {
  name: "DirectivePermission",
  directives: { permission },
  data() {
    return {
      key: 1, // 为了能每次切换权限的时候重新初始化指令
    };
  },
  methods: {
    checkPermission,
    handleRolesChange() {
      this.key++;
    },
  },
};
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
  }
  ::v-deep .permission-sourceCode {
    margin-left: 15px;
  }
  ::v-deep .permission-tag {
    background-color: #ecf5ff;
  }
}
</style>
