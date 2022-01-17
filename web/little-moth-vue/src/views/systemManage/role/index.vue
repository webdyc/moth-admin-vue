<template>
  <div class="app-content">
    <!-- 搜索项 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="客户姓名">
        <el-input v-model="searchForm.name" :size="styleSize" />
      </el-form-item>
      <el-form-item label="客户号码">
        <el-input v-model="searchForm.phone" :size="styleSize" />
      </el-form-item>
      <el-form-item label="客户来源">
        <el-select v-model="searchForm.source" :size="styleSize">
          <el-option
            v-for="item in sourceArr"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="日期">
        <el-date-picker
          v-model="timeValue"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-DD"
          :size="styleSize"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" :size="styleSize" @click="handleSearch">
          查询
        </el-button>
        <el-button :size="styleSize" @click="handleReset">重置</el-button>
        <el-button type="success" :size="styleSize">导出</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格配置 -->
    <div class="table-config">
      <el-button type="primary" :size="styleSize" @click="handleEdit()">
        新增一级菜单
      </el-button>
      <el-button type="danger" :size="styleSize" @click="handleDelete()">
        批量删除
      </el-button>
    </div>
    <!-- 表格 -->
    <el-table
      v-loading="tableLoading"
      style="width: 100%"
      :data="tableData"
      row-key="id"
      border
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <el-table-column prop="path" label="路由地址" width="180" />
      <el-table-column label="标题">
        <template v-if="scope.row.meta" slot-scope="scope">
          {{ scope.row.meta.title }}
        </template>
      </el-table-column>

      <el-table-column prop="路由名称" label="name" />
      <el-table-column prop="component" label="文件路径" />
      <el-table-column prop="redirect" label="路由重定向" />
      <el-table-column label="图标" width="80" align="center">
        <template
          v-if="scope.row.meta && scope.row.meta.icon"
          slot-scope="scope"
        >
          <svg-icon :icon-class="scope.row.meta.icon" />
        </template>
      </el-table-column>
      <el-table-column label="是否缓存" align="center" width="100">
        <template v-if="scope.row.meta" slot-scope="scope">
          {{ scope.row.meta.keepAlive ? "是" : "否" }}
        </template>
      </el-table-column>
      <el-table-column label="隐藏路由" align="center">
        <template slot-scope="scope">
          {{ scope.row.hidden ? "是" : "否" }}
        </template>
      </el-table-column>
      <el-table-column label="页面权限" align="center">
        <template v-if="scope.row.meta" slot-scope="scope">
          {{ scope.row.meta.role }}
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center">
        <template slot-scope="scope">
          <el-link
            class="mr-1"
            type="info"
            :underline="false"
            @click="handleEdit(scope.row)"
          >
            新增
          </el-link>
          <el-link
            class="mr-1"
            type="primary"
            :underline="false"
            @click="handleEdit(scope.row)"
          >
            编辑
          </el-link>
          <el-link
            type="danger"
            :underline="false"
            @click="handleDelete(scope.row.id)"
          >
            删除
          </el-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      class="table-pagination"
      :current-page="searchForm.page"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="searchForm.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <!-- 新增/编辑弹出框 -->
    <el-dialog
      v-el-drag-dialog
      :visible.sync="updateFormVisible"
      :title="isUpdate ? '编辑菜单' : '添加菜单'"
      @close="updateFormVisibleClose"
    >
      <el-form :model="updateForm" :label-width="formLabelWidth" :inline="true">
        <el-row>
          <el-form-item label="上级菜单：">
            <el-select
              v-model="updateForm.parentId"
              :size="styleSize"
              style="width: 500px"
            >
              <el-option
                v-for="item in sourceArr"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="菜单类型：">
            <el-radio-group v-model="updateForm.menuType">
              <el-radio label="M">目录</el-radio>
              <el-radio label="C">菜单</el-radio>
              <el-radio label="F">按钮</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-row>
        <el-form-item label="菜单名称：">
          <el-input
            v-model="updateForm.title"
            autocomplete="off"
            placeholder="title"
            :size="styleSize"
          />
        </el-form-item>
        <template
          v-if="updateForm.menuType === 'M' || updateForm.menuType === 'C'"
        >
          <el-form-item label="路由名称：">
            <el-input
              v-model="updateForm.name"
              autocomplete="off"
              placeholder="name"
              :size="styleSize"
            />
          </el-form-item>
          <el-form-item label="路由地址：">
            <el-input
              v-model="updateForm.path"
              autocomplete="off"
              placeholder="path"
              :size="styleSize"
            />
          </el-form-item>
          <el-form-item label="文件路径：">
            <el-input
              v-model="updateForm.component"
              autocomplete="off"
              placeholder="component"
              :size="styleSize"
            />
          </el-form-item>
          <el-form-item label="路由重定向：">
            <el-input
              v-model="updateForm.redirect"
              placeholder="redirect"
              autocomplete="off"
              :size="styleSize"
            />
          </el-form-item>
          <el-form-item label="图标：">
            <el-input
              v-model="updateForm.icon"
              placeholder="icon"
              autocomplete="off"
              :size="styleSize"
            />
          </el-form-item>
          <el-form-item label="面包屑显示：" v-if="updateForm.menuType === 'C'">
            <el-switch
              v-model="updateForm.breadcrumb"
              active-color="#13ce66"
              inactive-color="#ff4949"
            />
          </el-form-item>
          <el-form-item label="是否缓存：" v-if="updateForm.menuType === 'C'">
            <el-switch
              v-model="updateForm.keepAlive"
              active-color="#13ce66"
              inactive-color="#ff4949"
            />
          </el-form-item>
          <el-form-item label="隐藏路由：">
            <el-switch
              v-model="updateForm.hidden"
              active-color="#13ce66"
              inactive-color="#ff4949"
            />
          </el-form-item>
        </template>
        <el-form-item label="按钮权限：" v-if="updateForm.menuType === 'F'">
          <el-input
            v-model="updateForm.role"
            autocomplete="off"
            placeholder="role"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="排序：">
          <el-input-number
            v-model="updateForm.sort"
            :min="1"
            :size="styleSize"
          ></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="styleSize" @click="updateFormVisibleClose">
          取 消
        </el-button>
        <el-button type="primary" :size="styleSize" @click="handleSubmit">
          确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import defaultSettings from "@/settings";
import elDragDialog from "@/directive/el-drag-dialog";
// /menu/treeselect
// 路由表
const menuListM = [
  {
    id: 1,
    path: "/",
    component: "Layout",
    redirect: "welcome",
    children: [
      {
        id: 11,
        path: "welcome",
        name: "Welcome",
        component: "/welcome/index",
        meta: {
          title: "欢迎首页",
          role: ["admin", "addbtn1"],
          icon: "example",
        },
      },
    ],
  },
  {
    id: 2,
    path: "/user",
    component: "Layout",
    redirect: "/user/index",
    children: [
      {
        id: 21,
        path: "/user/index",
        name: "user",
        component: "/user/index",
        meta: {
          title: "用户管理",
          icon: "table",
        },
      },
    ],
  },
  {
    id: 3,
    path: "/charts",
    component: "Layout",
    redirect: "/charts/index",
    meta: {
      title: "图表管理",
      icon: "table",
    },
    children: [
      {
        id: 31,
        path: "/charts/index",
        name: "chartsline",
        component: "/charts/line/index",
        meta: {
          title: "折线图",
        },
      },
      {
        id: 32,
        path: "/charts/mixChart",
        name: "chartsmixChart",
        component: "/charts/mixChart/index",
        meta: {
          title: "混合图表",
        },
      },
    ],
  },
  {
    id: 4,
    path: "/components",
    component: "Layout",
    redirect: "/tinymce/index",
    meta: {
      title: "组件",
      icon: "table",
    },
    children: [
      {
        id: 41,
        path: "/recorder/index",
        name: "recorder",
        component: "/components/recorder/index",
        meta: {
          title: "录音",
        },
      },
      {
        id: 42,
        path: "/tinymce/index",
        name: "tinymce",
        component: "/components/tinymce/index",
        meta: {
          title: "富文本编译器",
        },
      },
      {
        id: 43,
        path: "/componentsDemo/index",
        name: "componentsDemo",
        component: "/components/componentsDemo/index",
        meta: {
          title: "小组件",
        },
      },
      {
        id: 44,
        path: "/dragDalog/index",
        name: "dragDalog",
        component: "/components/dragDalog/index",
        meta: {
          title: "可拖拽弹窗",
        },
      },
      {
        id: 45,
        path: "/dragKanban/index",
        name: "dragKanban",
        component: "/components/dragKanban/index",
        meta: {
          title: "可拖拽看板",
        },
      },
    ],
  },
  {
    id: 5,
    path: "/permission",
    component: "Layout",
    redirect: "/permission/index",
    children: [
      {
        id: 51,
        path: "/permission/index",
        name: "permission",
        component: "/permission/index",
        meta: {
          title: "指令权限",
          icon: "table",
        },
      },
    ],
  },
  {
    id: 6,
    path: "/menu",
    component: "Layout",
    redirect: "/menu/index",
    children: [
      {
        id: 61,
        path: "/menu/index",
        name: "menu",
        component: "/menu/index",
        meta: {
          title: "菜单管理",
          icon: "table",
        },
      },
    ],
  },
];
/**
* parentId: null              上级菜单
* menuType: M,                菜单类型
* sort: 1,                    排序
* hidden: true,               是否隐藏路由
* path: '/welcome',           路由地址
* component:'Layout',         vue文件路径
* redirect: noredirect,       路由重定向
* name:'welcome',             路由名称父级可不填
* 单层级路由父级可不填
  role: 'admin',               页面权限
  title: 'title'               页面标题
  icon: 'svg-name'             页面图标
  breadcrumb: false,           如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)
  keepAlive: false,            页面是否缓存
**/
// PersonForm的类
class PersonForm {
  // 值
  constructor() {
    // 上级菜单
    this.parentId = null;
    // 菜单类型
    this.menuType = "M";
    // 排序
    this.sort = 1;
    // 是否隐藏路由
    this.hidden = false;
    // 路由地址
    this.path = "";
    // vue文件路径
    this.component = "";
    // 路由重定向
    this.redirect = "";
    // 路由名称父级可不填
    this.name = "";
    // 单层级路由父级可不填
    // 页面权限
    this.role = "";
    // 页面标题
    this.title = "";
    //  页面图标
    this.icon = "";
    //  如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)
    this.breadcrumb = true;
    //  页面是否缓存
    this.keepAlive = false;
  }
  // 验证方法
  static getRule() {
    return {
      title: [{ required: true, message: "请填写标题名称", trigger: "blur" }],
      title_e: [{ required: true, message: "请填写权限名称", trigger: "blur" }],
    };
  }
}
export default {
  name: "Menu",
  directives: { elDragDialog },
  data() {
    return {
      styleSize: defaultSettings.styleSize,
      // 搜索项
      searchForm: {
        // 客户姓名
        name: "",
        // 客户号码
        phone: "",
        // 备用号码
        secondPhone: "",
        // 客户来源
        source: "",
        // 导入开始时间
        createTimeBegin: "",
        createTimeEnd: "",
        // 每页条数
        pageSize: 1,
        // 当前页码
        pageNumber: 10,
      },
      //   时间段日期
      timeValue: [],
      // 客户来源数组
      sourceArr: [
        {
          value: "1",
          label: "全部",
        },
        {
          value: "2",
          label: "手动添加",
        },
        {
          value: "3",
          label: "自动导入",
        },
      ],
      // 表格数据
      formLabelWidth: "140px",
      tableLoading: false,
      tableData: menuListM,
      // 多选数组
      multipleSelection: [],
      // 总条数
      total: 0,
      // 表单弹出框
      isUpdate: false,
      updateFormVisible: false,
      updateFormLoading: false,
      // 是否显示新增标签输入框
      inputVisible: false,
      inputValue: "",
      updateForm: new PersonForm(),
      updateFormRules: PersonForm.getRule(),
    };
  },
  created() {},
  mounted() {},
  methods: {
    // 筛选项提交
    handleSearch() {
      const params = {
        starTime: this.timeValue[0],
        endTime: this.timeValue[1],
      };
      Object.assign(this.searchForm, params);
      this.tableLoading = true;

      // clientManage_list(this.searchForm).then((res) => {
      //   this.tableData = res.data.list;
      //   this.total = res.data.total;
      //   this.tableLoading = false;
      // });
    },
    // 筛选项重置
    handleReset() {},
    // 打开弹框
    handleEdit(row) {
      if (row) {
        // 编辑
        this.isUpdate = true;
        this.updateForm = JSON.parse(JSON.stringify(row));
      }
      this.updateFormVisible = true;
    },
    // 关闭弹窗
    updateFormVisibleClose() {
      this.updateForm = new PersonForm();
      this.updateFormRules = PersonForm.getRule();
      this.updateFormLoading = false;
      this.updateFormVisible = false;
      this.isUpdate = false;
    },
    // 弹窗表单提交
    handleSubmit() {
      console.log(this.updateForm);
      // this.updateFormVisibleClose()
    },
    // 多选
    handleSelectionChange(val) {
      this.multipleSelection = val.map((item) => item.id);
    },
    // 删除
    handleDelete(id) {
      const ids = id || this.multipleSelection.join(",");
      if (!ids) {
        this.$message.warning("删除信息不能为空！");
        return;
      }
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          console.log(ids);
          this.$message.success("删除成功");
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },
    // 分页
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    handleClose(tag) {
      this.updateForm.meta.role.splice(
        this.updateForm.meta.role.indexOf(tag),
        1
      );
    },
    showInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    handleInputConfirm() {
      const inputValue = this.inputValue;
      if (inputValue) {
        this.updateForm.meta.role.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = "";
    },
  },
};
</script>

<style lang="scss" scoped>
.box-card {
  width: 200px;
  margin-right: 15px;
}
.input-new-tag {
  width: 100px;
}
</style>
