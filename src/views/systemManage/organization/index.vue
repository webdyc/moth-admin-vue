<template>
  <div class="app-content">
    <!-- 搜索项 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="组织名称：">
        <el-input v-model="searchForm.name" :size="styleSize" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" :size="styleSize" @click="handleSearch(true)">
          查询
        </el-button>
        <el-button :size="styleSize" @click="handleReset"> 重置 </el-button>
      </el-form-item>
    </el-form>
    <!-- 表格配置 -->
    <div class="table-config">
      <el-button type="primary" :size="styleSize" @click="handleEdit()">
        新增
      </el-button>
    </div>
    <!-- 表格内容 -->
    <el-table
      v-loading="tableLoading"
      :data="tableData"
      border
      style="width: 100%"
    >
      <el-table-column prop="name" label="组织名称" align="center" />
      <el-table-column prop="officeCode" label="负责人" align="center" />
      <el-table-column prop="createTime" label="创建日期" align="center" />
      <el-table-column prop="status" label="状态" align="center" width="120">
        <template slot-scope="scope">
          <el-switch
            :value="scope.row.useable == '0' ? true : false"
            active-color="#13ce66"
            inactive-color="#ff4949"
            @change="handelSwitchChange(scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center" width="320">
        <template slot-scope="scope">
          <el-link
            class="mr-1"
            type="success"
            :underline="false"
            @click="handleEdit(false, scope.row)"
          >
            新增
          </el-link>
          <el-link
            class="mr-1"
            type="primary"
            :underline="false"
            @click="handleEdit(true, scope.row)"
          >
            编辑
          </el-link>
          <el-link
            class="mr-1"
            type="danger"
            :underline="false"
            @click="handleRemove(scope.row)"
          >
            删除
          </el-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      class="table-pagination"
      :current-page="searchForm.pageNum"
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
      :size="styleSize"
      :visible.sync="updateFormVisible"
      :title="isUpdate ? '编辑' : '新增'"
      @close="updateFormVisibleClose"
    >
      <div class="dialog_display">
        <div class="tree">
          <h3>组织架构</h3>
          <el-tree
            class="content_tree"
            :data="treeList"
            node-key="id"
            default-expand-all
          />
        </div>
        <div class="form-">
          <h3>{{ isUpdate ? "编辑组织" : "新增组织" }}</h3>
          <el-form
            ref="dialogForm"
            :model="updateForm"
            :label-width="formLabelWidth"
            :size="styleSize"
            :rules="updateFormRules"
          >
            <el-form-item label="名称：" prop="name" label-width="100px">
              <el-input
                v-model="updateForm.name"
                placeholder="请输入名称"
                :size="styleSize"
              />
            </el-form-item>
            <el-form-item label="描述：" prop="detail" label-width="100px">
              <el-input
                v-model="updateForm.detail"
                type="textarea"
                placeholder="请输入描述"
                :size="styleSize"
              />
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button :size="styleSize" @click="updateFormVisibleClose">
          取 消
        </el-button>
        <el-button
          type="primary"
          :size="styleSize"
          @click="handleSubmit('dialogForm')"
        >
          确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import defaultSettings from "@/settings";
import elDragDialog from "@/directive/el-drag-dialog";

import {
  organization_tabel,
  organization_treeselect,
  organization_stateChange,
  organization_add,
  organization_remove,
  organization_edit,
  organization_detail,
  addInfo,
  officePeculiar,
  // semantic,
} from "@/api/systemManage/organization.js";
import debounce from "lodash.debounce";
class PersonForm {
  constructor() {
    // 组织id
    this.deptId = "";
    // 上级部门
    this.parentId = "";
    // 部门名称
    this.deptName = "";
    // 显示排序
    this.orderNum = "";
    // 负责人
    this.leader = "";
    // 联系电话
    this.phone = "";
    // 邮箱
    this.email = "";
    // 部门状态
    this.status = "0";
  }
  // 验证方法
  static getRule() {
    return {
      parentId: [{ required: true, message: "上级部门", trigger: "change" }],
      deptName: [{ required: true, message: "部门名称", trigger: "change" }],
      orderNum: [{ required: true, message: "显示排序", trigger: "change" }],
    };
  }
}
export default {
  name: "Organization",
  directives: { elDragDialog },
  data() {
    return {
      styleSize: defaultSettings.styleSize,
      // 左侧树数据
      treeList: [],
      // 搜索项
      searchForm: {
        // 部门名称
        deptName: "",
        // 部门状态
        status: "0",
        // 每页条数
        pageSize: 10,
        // 当前页码
        pageNum: 1,
      },
      // 上级菜单
      options: [],
      // 表格数据
      formLabelWidth: "180px",
      // 表格loading
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

  mounted() {
    this.handleSearch();
  },
  methods: {
    // 筛选项提交
    handleSearch(isShow) {
      isShow ? (this.searchForm.pageNum = 1) : null;
      isShow ? (this.searchForm.parentIds = "") : null;
      this.tableLoading = true;
      organization_tabel(this.searchForm).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
          this.tableLoading = false;
        }
      });
      organization_treeselect().then((result) => {
        if (result.code === 200) {
          this.treeList = result.data;
          this.options = result.data;
        }
      });
    },
    /** 筛选项重置 */
    handleReset() {
      // 搜索项
      this.searchForm = {
        // 部门名称
        deptName: "",
        // 部门状态
        status: "0",
        // 每页条数
        pageSize: 10,
        // 当前页码
        pageNum: 1,
      };
      this.handleSearch();
    },

    /** 打开弹框 */
    handleEdit(row) {
      if (row) {
        // 编辑
        this.isUpdate = true;
        this.updateForm = JSON.parse(JSON.stringify(row));
      }
      this.updateFormVisible = true;
    },
    /** 关闭弹窗 */
    updateFormVisibleClose() {
      this.updateForm = new PersonForm();
      this.updateFormRules = PersonForm.getRule();
      this.updateFormLoading = false;
      this.updateFormVisible = false;
      this.isUpdate = false;
    },
    // 弹窗表单提交
    handleSubmit: debounce(function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (!this.isUpdate) {
            organization_add({
              id: this.updateForm.id,
              name: this.updateForm.name,
              remarks: this.updateForm.detail,
            }).then((res) => {
              if (res.code === 200) {
                this.$message.success("添加成功");
                this.handleSearch();
                this.updateFormInit();
              } else {
                this.$message.error("添加失败");
              }
              this.updateFormVisibleClose();
            });
          } else {
            organization_edit({
              id: this.updateForm.id,
              name: this.updateForm.name,
              remarks: this.updateForm.detail,
            }).then((res) => {
              if (res.code === 200) {
                this.$message.success("编辑成功");
                this.handleSearch();
              } else {
                this.$message.error("编辑失败");
              }
              this.updateFormVisibleClose();
            });
          }
        } else {
          this.$message.error("请完善信息");
        }
      });
    }, 1000),

    // 初始化弹窗
    updateFormInit() {
      this.updateForm = {
        name: "",
        detail: "",
        id: null,
      };
    },
    // 初始化搜索项
    searchInit() {
      this.searchForm = {
        parentIds: "",
        // 客户姓名
        name: "",
        // 每页条数
        pageSize: 10,
        // 当前页码
        pageNum: 1,
      };
    },

    // 状态改变
    handelSwitchChange(row) {
      if (row.useable == "0") {
        this.$confirm(
          "状态禁用后，当前组织下所有操作将被禁止，请谨慎操作！",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          }
        )
          .then(() => {
            organization_stateChange({
              id: row.id,
              useable: row.useable === "1" ? "0" : "1",
            }).then((res) => {
              if (res.code === 200) {
                row.useable = row.useable === "1" ? "0" : "1";
              } else {
                this.$message.error("操作失败");
              }
            });
          })
          .catch(() => {
            this.$message.info("已取消操作");
          });
      } else {
        organization_stateChange({
          id: row.id,
          useable: row.useable === "1" ? "0" : "1",
        }).then((res) => {
          if (res.code === 200) {
            row.useable = row.useable === "1" ? "0" : "1";
            this.$message({
              type: "success", // success error warning
              message: "启用成功",
              duration: 2000,
            });
          } else {
            this.$message.error("操作失败");
          }
        });
      }
      console.log(row);
    },
    // 删除组织
    handleRemove(row) {
      this.$confirm("此操作将永久删除该组织, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        organization_remove({ id: row.id }).then((res) => {
          if (res.code === 200) {
            this.$message.success("删除成功");
            this.handleSearch();
          } else {
            this.$message.error("删除失败");
          }
        });
      });
    },
    // 分页
    handleSizeChange(val) {
      this.searchForm.pageSize = val;
      this.handleSearch();
    },
    handleCurrentChange(val) {
      this.searchForm.pageNum = val;
      this.handleSearch();
    },
  },
};
</script>

<style lang="scss" scoped>
.custom-tree-node {
  width: 100%;
  display: block;
}
.custom-tree-node-rigth {
  float: right;
  i {
    padding: 0 5px;
  }
}
.dialog_display {
  display: flex;
  align-content: space-around;
  justify-content: space-evenly;
  .tree {
    width: 30%;
  }

  .form- {
    width: 40%;
  }
}
.tree-label {
  font-size: 14px;
}
// ::v-deep .el-dialog__body {
//   height: 100vh;
// }
</style>
