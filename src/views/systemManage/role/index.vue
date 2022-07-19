<template>
  <div class="app-content">
    <!-- 搜索项 -->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="角色名：">
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
    <el-table
      v-loading="tableLoading"
      :data="tableData"
      border
      style="width: 100%"
    >
      <el-table-column prop="name" label="角色名" align="center" />
      <el-table-column prop="officeName" label="所属组织" align="center" />
      <el-table-column prop="userName" label="创建人" align="center" />
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
      <el-table-column fixed="right" label="操作" align="center" width="300">
        <template slot-scope="scope"
          ><el-link
            class="mr-2"
            type="primary"
            :underline="false"
            @click="handleEdit(true, scope.row)"
          >
            编辑
          </el-link>
          <el-link
            class="mr-2"
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
      <div class="threeBar">
        <div class="center">
          <h3>{{ isUpdate ? "编辑角色" : "新增角色" }}</h3>
          <el-form
            ref="centerFormRlue"
            :model="updateForm"
            label-width="80px"
            :size="styleSize"
            :rules="updateFormRules"
          >
            <el-form-item label="名称：" prop="name">
              <el-input
                v-model="updateForm.name"
                placeholder="请输入名称"
                :size="styleSize"
              />
            </el-form-item>
            <el-form-item label="描述：" prop="remarks">
              <el-input
                v-model="updateForm.remarks"
                type="textarea"
                placeholder="请输入描述"
                :size="styleSize"
              />
            </el-form-item>
          </el-form>
        </div>
        <div class="rigth">
          <h3>菜单配置</h3>
          <el-tree
            ref="myTree"
            :data="data"
            show-checkbox
            node-key="id"
            :props="defaultPropsL"
            :default-checked-keys="updateForm.menuIdList"
            @check-change="handleTreeChange"
          />
        </div>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button :size="styleSize" @click="updateFormVisibleClose">
          取 消
        </el-button>
        <el-button
          type="primary"
          :size="styleSize"
          @click="handleSubmit('centerFormRlue')"
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
import { organization_treeselect } from "@/api/systemManage/organization.js";
import { user_roleMap } from "@/api/systemManage/user.js";
import {
  role_list,
  role_stateChange,
  role_delete,
  role_detail,
  role_add,
  role_edit,
} from "@/api/systemManage/role.js";
import { menu_treeselect } from "@/api/systemManage/menu";
import { deepClone } from "@/utils";
import debounce from "lodash.debounce";

// 搜索项 类
class SearchForm {
  constructor() {
    this.officeValue = "";
    this.name = "";
    this.officeName = "";
    this.pageSize = 10;
    this.pageNum = 1;
  }
}
// 弹出层 类
class DiagioForm {
  constructor() {
    this.officeId = "";
    this.id = "";
    this.name = "";
    this.remarks = "";
    this.menuIdList = [];
  }
  static getRule() {
    return {
      name: [
        { required: true, message: "请输入名称", trigger: "blur" },
        { min: 4, max: 8, message: "长度在 4 到 8 个字符", trigger: "blur" },
      ],
      remarks: [
        {
          required: true,
          message: "请输入描述",
          trigger: "blur",
        },
        {
          max: 100,
          message: "最大字数为100！",
          trigger: "blur",
        },
      ],
    };
  }
}

export default {
  name: "Tabel",
  directives: { elDragDialog },
  data() {
    return {
      styleSize: defaultSettings.styleSize,
      // 搜索项
      searchForm: new SearchForm(),
      // 表格数据
      formLabelWidth: "140px",
      // 表哥loading
      tableLoading: false,
      tableData: [],

      // 总条数
      total: 0,
      // 表单弹出框
      isUpdate: false,
      updateFormVisible: false,
      updateFormLoading: true,

      detailForm: {},
      // 详情弹层
      XQdialogFormVisible: false,
      // 弹出层下拉列表数据
      officeIds: [],
      updateForm: new DiagioForm(),
      // 弹窗的组织名称
      updateOrganizationName: "",
      updateFormRules: DiagioForm.getRule(),
      // 用户转移弹窗
      transferDialogFormVisible: false,
      // 下拉的组织列表
      transferOrganization: [],
      // 下拉的角色列表
      transferRole: [],

      defaultProps: {
        value: "id",
        checkStrictly: true,
      },
      data: [
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
            },
          ],
        },
      ],
      defaultPropsL: {
        children: "children",
        label: "label",
      },
      // 弹窗默认选中的节点
    };
  },

  mounted() {
    this.handleSearch();
  },
  methods: {
    // 当前选中
    handleNodeClick(data) {
      // console.log(data);
      this.searchForm.officeValue = data.value;
      this.searchForm.id = data.id;
      this.handleSearch();
    },

    // 筛选项提交
    handleSearch(isShow) {
      isShow ? (this.searchForm.pageNum = 1) : null;
      isShow ? (this.searchForm.officeValue = "") : null;
      this.tableLoading = true;

      role_list(this.searchForm).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
          this.tableLoading = false;
        }
      });
    },
    // 筛选项重置
    handleReset() {
      this.searchForm = new SearchForm();
      this.handleSearch();
    },
    // 打开弹框
    async handleEdit(state, row) {
      // 打开弹窗请求树列表
      this.handleTreeList();
      this.isUpdate = state;
      if (this.isUpdate) {
        console.log(row);
        this.updateForm = deepClone(row);
      } else {
        // 根据组织id请求角色列表
        this.updateForm = new DiagioForm();
        console.log(row.id);
        this.updateForm.officeId = row.id;
      }

      this.updateFormVisible = true;
    },
    // 关闭弹窗
    updateFormVisibleClose() {
      this.$refs.centerFormRlue.resetFields();
      this.updateFormLoading = false;
      this.updateFormVisible = false;
      this.isUpdate = false;
    },
    // 弹窗表单提交
    handleSubmit: debounce(function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (!this.isUpdate) {
            role_add(this.updateForm).then((res) => {
              if (res.code === 200) {
                this.$message.success("添加成功");
                this.handleSearch();
              } else {
                this.$message.success("添加失败");
              }
              this.updateFormVisibleClose();
            });
          } else {
            role_edit(this.updateForm).then((res) => {
              if (res.code === 200) {
                this.$message.success("编辑成功");
                this.handleSearch();
              } else {
                this.$message.success("编辑失败");
              }
              this.updateFormVisibleClose();
            });
          }
        } else {
          this.$message.error("请完善信息");
        }
      });
    }, 1000),

    // 状态改变
    handelSwitchChange(row) {
      this.$confirm(
        `${
          row.useable === "1"
            ? "是否启用该角色"
            : "状态禁用后，当前角色下所有用户操作将被禁止，请谨慎操作!"
        }`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).then(() => {
        role_stateChange({
          id: row.id,
          useable: row.useable === "1" ? "0" : "1",
        }).then((res) => {
          if (res.code === 200) {
            row.useable = row.useable === "1" ? "0" : "1";
          } else {
            this.$message.error("操作失败");
          }
        });
      });
    },
    // 删除用户
    handleRemove(row) {
      this.$confirm("是否删除此用户？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        role_delete({ id: row.id }).then((res) => {
          if (res.code === 200 && res.data === 1) {
            this.$message.success("删除成功");
            this.handleSearch();
          } else {
            this.$message.error("当前角色下有关联用户存在，无法被删除！");
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
    handleTreeList() {
      menu_treeselect().then((res) => {
        if (res.code === 200) {
          // res.data.filter((item) => {
          //   if(item.)
          // });
          console.log(res.data, "***");
          this.data = res.data;
        }
      });
    },
    handleTreeChange() {
      this.updateForm.menuIdList = this.$refs.myTree.getCheckedKeys();
    },
  },
};
</script>

<style lang="scss" scoped>
.custom-tree-node {
  width: 100%;
  display: block;

  .rigth {
    display: block;
    float: right;
  }
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
.threeBar {
  display: flex;

  justify-content: space-around;

  .left {
    width: 25%;
  }
  .rigth {
    width: 25%;
  }
}
</style>
