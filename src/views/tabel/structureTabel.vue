<template>
  <div class="app-box">
    <div class="app-box-table">
      <!-- 左侧选择框 -->
      <el-card class="box-card" shadow="never">
        <div slot="header" class="clearfix">
          <span>添加一级节点</span>
          <el-button style="float: right; padding: 3px 0" type="text">
            操作按钮
          </el-button>
        </div>
        <div>
          <el-tree
            class="content_tree"
            :data="treeList"
            node-key="id"
            default-expand-all
            :draggable="isTree"
            :allow-drop="allowDrop"
            :allow-drag="allowDrag"
            @node-click="handleNodeClick"
            @node-drop="handleDrop"
          >
            <span slot-scope="{ node, data }" class="custom-tree-node">
              <span>{{ node.label }}</span>
              <span v-if="permissionId == data.id">
                <i
                  class="el-icon-circle-plus-outline icon"
                  @click.stop="openDialog('add', data.id)"
                />
                <i
                  class="el-icon-edit-outline icon"
                  style="color: #409eff"
                  @click.stop="openDialog('edit', data.id)"
                />
                <i
                  class="el-icon-delete icon"
                  style="color: #f56c6c"
                  @click.stop="todelete(data.id)"
                />
              </span>
            </span>
          </el-tree>
        </div>
      </el-card>
      <div class="app-box-table-box">
        <!-- 搜索项 -->
        <el-form :inline="true" :model="searchForm" class="demo-form-inline">
          <el-form-item label="客户姓名">
            <el-input v-model="searchForm.name" :size="styleSize" />
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
          </el-form-item>
        </el-form>
        <!-- 表格配置 -->
        <div class="table-config">
          <el-button type="primary" :size="styleSize" @click="handleEdit()">
            新增
          </el-button>
          <el-button type="danger" :size="styleSize" @click="handleDelete()">
            批量删除
          </el-button>
        </div>
        <!-- 表格内容 -->
        <el-table
          v-loading="tableLoading"
          :data="tableData"
          border
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="40" />
          <el-table-column prop="name" label="姓名" align="center" />
          <el-table-column prop="typeName" label="客户类型" align="center" />
          <el-table-column prop="phone" label="客户号码" align="center" />
          <el-table-column prop="secondPhone" label="备用号码" align="center" />
          <el-table-column
            prop="provinceCityRegion"
            label="地区"
            align="center"
          />
          <el-table-column prop="sourceName" label="客户来源" align="center" />
          <el-table-column prop="createTime" label="添加日期" align="center" />
          <el-table-column prop="status" label="状态" align="center">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.status"
                active-color="#13ce66"
                inactive-color="#ff4949"
              />
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
                查看
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
          :title="isUpdate ? '编辑' : '添加'"
          @close="updateFormVisibleClose"
        >
          <el-form :model="updateForm" :label-width="formLabelWidth">
            <el-form-item label="客户姓名：">
              <el-input
                v-model="updateForm.name"
                autocomplete="off"
                :size="styleSize"
              />
            </el-form-item>
            <el-form-item label="客户号码：">
              <el-input
                v-model="updateForm.phone"
                autocomplete="off"
                :size="styleSize"
              />
            </el-form-item>
            <el-form-item label="备用号码：">
              <el-input
                v-model="updateForm.secondPhone"
                autocomplete="off"
                :size="styleSize"
              />
            </el-form-item>
            <el-form-item label="客户来源：">
              <el-select v-model="updateForm.source" :size="styleSize">
                <el-option
                  v-for="item in sourceArr"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
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
    </div>

    <!-- 新增/编辑弹出框 -->
    <el-dialog
      v-el-drag-dialog
      :visible.sync="updateFormVisible"
      :title="isUpdate ? '编辑' : '添加'"
      @close="updateFormVisibleClose"
    >
      <el-form :model="updateForm" :label-width="formLabelWidth">
        <el-form-item label="客户姓名：">
          <el-input
            v-model="updateForm.name"
            autocomplete="off"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="客户号码：">
          <el-input
            v-model="updateForm.phone"
            autocomplete="off"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="备用号码：">
          <el-input
            v-model="updateForm.secondPhone"
            autocomplete="off"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="客户来源：">
          <el-select v-model="updateForm.source" :size="styleSize">
            <el-option
              v-for="item in sourceArr"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
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
// import { clientManage_list } from "@/api/client/clientManage";
/**
* name:'welcome',             路由名称
* path: '/welcome',           路由地址
* component:'Layout',         vue文件路径
* redirect: noredirect,       路由重定向
* meta : {
  role: ['admin','editor'],    页面权限
  title: 'title'               页面标题
  icon: 'svg-name'             页面图标
  keepAlive: false,            页面是否缓存
}
* hidden: true,               是否隐藏
**/
// PersonForm的类
class PersonForm {
  // 值
  constructor() {
    // 客户姓名
    this.name = "";
    // 客户号码
    this.phone = "";
    // 备用号码
    this.secondPhone = "";
    // 客户来源
    this.source = "";
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
  name: "Tabel",
  directives: { elDragDialog },
  data() {
    return {
      styleSize: defaultSettings.styleSize,
      // 左侧树数据
      treeList: [
        {
          id: 1,
          label: "一级 1",
          children: [
            {
              id: 4,
              label: "二级 1-1",
              children: [
                {
                  id: 9,
                  label: "三级 1-1-1",
                },
                {
                  id: 10,
                  label: "三级 1-1-2",
                },
              ],
            },
          ],
        },
        {
          id: 2,
          label: "一级 2",
          children: [
            {
              id: 5,
              label: "二级 2-1",
            },
            {
              id: 6,
              label: "二级 2-2",
            },
          ],
        },
        {
          id: 3,
          label: "一级 3",
          children: [
            {
              id: 7,
              label: "二级 3-1",
            },
            {
              id: 8,
              label: "二级 3-2",
              children: [
                {
                  id: 11,
                  label: "三级 3-2-1",
                },
                {
                  id: 12,
                  label: "三级 3-2-2",
                },
                {
                  id: 13,
                  label: "三级 3-2-3",
                },
              ],
            },
          ],
        },
      ],
      // 是否可拖拽
      isTree: true,
      permissionId: "",
      // 拖拽临时储存
      treeListOld: [],
      defaultProps: {
        children: "children",
        label: "label",
      },
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
      tableData: [],
      // 多选数组
      multipleSelection: [],
      // 总条数
      total: 0,
      // 表单弹出框
      isUpdate: false,
      updateFormVisible: false,
      updateFormLoading: false,
      updateForm: new PersonForm(),
      updateFormRules: PersonForm.getRule(),
    };
  },
  created() {},
  mounted() {
    this.handleSearch();
  },
  methods: {
    // 当前选中
    handleNodeClick(data) {
      this.permissionId = data.id;
      console.log(data.id);
    },
    // 共两个参数，依次为：被拖拽节点对应的 Node、event
    handleDragStart(node, ev) {
      // 拖拽前备份数组，请求数据如果出错需要还原更改前的数据
      this.treeListOld = JSON.parse(JSON.stringify(this.treeList));
    },
    // // 共三个参数，依次为：被拖拽节点对应的 Node、所进入节点对应的 Node、event
    // handleDragEnter(draggingNode, dropNode, ev) {
    // 	console.log('tree drag enter: ', dropNode.label);
    // },
    // // 共三个参数，依次为：被拖拽节点对应的 Node、所离开节点对应的 Node、event
    // handleDragLeave(draggingNode, dropNode, ev) {
    // 	console.log('tree drag leave: ', dropNode.label);
    // },
    // // 共三个参数，依次为：被拖拽节点对应的 Node、当前进入节点对应的 Node、event
    // handleDragOver(draggingNode, dropNode, ev) {
    // 	console.log('tree drag over: ', dropNode.label);
    // },
    // // 共四个参数，依次为：被拖拽节点对应的 Node、结束拖拽时最后进入的节点（可能为空）、被拖拽节点的放置位置（before、after、inner）、event
    // handleDragEnd(draggingNode, dropNode, dropType, ev) {
    // 	console.log('tree drag end: ', dropNode && dropNode.label, dropType);
    // },
    // 共四个参数，依次为：被拖拽节点对应的 Node、结束拖拽时最后进入的节点、被拖拽节点的放置位置（before、after、inner）、event
    handleDrop(draggingNode, dropNode, dropType, ev) {
      // 这里需要注意案例是直接draggingNode.id，如果需要用到自己的参数就要用draggingNode.data
      console.log("被拖拽id", draggingNode.data.id);
      console.log("结束拖拽时最后进入的节点id", dropNode.data.id);
      console.log("被拖拽节点的放置位置", dropType);
      // 请求接口的话 记得拖拽更改为不可拖拽
      this.isTree = true;
    },
    // 拖拽时判定目标节点能否被放置。type 参数有三种情况：'prev'、'inner' 和 'next'，分别表示放置在目标节点前、插入至目标节点和放置在目标节点后
    allowDrop(draggingNode, dropNode, type) {
      if (dropNode.data.label === "二级 3-1") {
        return type !== "inner";
      } else {
        return true;
      }
    },
    // 判断节点能否被拖拽
    allowDrag(draggingNode) {
      return draggingNode.data.label.indexOf("三级 3-2-2") === -1;
    },
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
  },
};
</script>

<style lang="scss" scoped></style>
