<template>
  <div class="app-box">
    <div class="app-box-table">
      <!-- 左侧选择框 -->
      <el-card class="box-card" shadow="never">
        <div slot="header" class="clearfix">
          <span>组织结构</span>
          <!-- <span style="float: right; padding: 3px 0">新增用户</span> -->
        </div>
        <div>
          <el-tree
            class="content_tree"
            :data="treeList"
            node-key="id"
            default-expand-all
            @node-click="handleNodeClick"
          >
            <span
              slot-scope="{ node, data }"
              class="custom-tree-node el-tree-node__label"
            >
              <span class="tree-label" :title="node.label">{{
                node.label
              }}</span>
              <!-- <span class="rigth">
                <i
                  class="el-icon-circle-plus-outline icon"
                  style="color: #409eff"
                  @click.stop="handleEdit(false, data)"
                />
              </span> -->
            </span>
          </el-tree>
        </div>
      </el-card>
      <div class="app-box-table-box">
        <!-- 搜索项 -->
        <el-form :inline="true" :model="searchForm" class="demo-form-inline">
          <!-- <el-form-item label="组织名称：">
            <el-input v-model="searchForm.officeName" :size="styleSize" />
          </el-form-item> -->
          <el-form-item>
            <el-input
              v-model.trim="searchForm.margeSelect"
              :size="styleSize"
              placeholder="用户名/邮箱/手机号"
            />
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              :size="styleSize"
              @click="handleSearch(true)"
            >
              查询
            </el-button>
            <el-button :size="styleSize" @click="handleReset"> 重置 </el-button>
          </el-form-item>
        </el-form>
        <!-- 表格配置 -->
        <div class="table-config">
          <el-button
            type="success"
            :size="styleSize"
            @click="handleEdit(false)"
          >
            新增用户
          </el-button>
        </div>
        <!-- 表格内容 -->
        <el-table
          v-loading="tableLoading"
          :data="tableData"
          border
          style="width: 100%"
        >
          <el-table-column prop="userName" label="用户名" align="center" />
          <el-table-column prop="nickName" label="姓名" align="center" />
          <el-table-column prop="sex" label="性别" align="center">
            <template v-slot="scope">
              <div>
                {{ scope.row.sex == "1" ? "男" : "女" }}
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="email" label="邮箱" align="center" />
          <el-table-column prop="phone" label="手机号" align="center" />
          <el-table-column prop="officeName" label="组织机构" align="center" />
          <el-table-column prop="createTime" label="创建日期" align="center" />

          <el-table-column
            prop="status"
            label="状态"
            align="center"
            width="120"
          >
            <template slot-scope="scope">
              <el-switch
                :value="scope.row.useable == '0' ? true : false"
                active-color="#13ce66"
                inactive-color="#ff4949"
                @change="handelSwitchChange(scope.row)"
              />
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="250">
            <template slot-scope="scope"
              ><el-link
                class="mr-1"
                type="primary"
                :underline="false"
                @click="handleEdit(true, scope.row)"
              >
                编辑
              </el-link>
              <el-link
                class="mr-1"
                type="success"
                :underline="false"
                @click="handleTransferOpen(false, scope.row)"
              >
                转移
              </el-link>

              <el-link
                class="mr-1"
                type="info"
                :underline="false"
                @click="handleDetail(scope.row)"
              >
                查看权限
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
      </div>
    </div>

    <!-- 新增/编辑弹出框 -->
    <el-dialog
      v-el-drag-dialog
      :size="styleSize"
      :visible.sync="updateFormVisible"
      :title="isUpdate ? '编辑' : '新增'"
      @close="updateFormVisibleClose"
    >
      <div class="form-">
        <el-form
          ref="dialogForm"
          :model="updateForm"
          :label-width="formLabelWidth"
          :rules="updateFormRules"
          :size="styleSize"
        >
          <el-form-item label="组织名称：">
            <span>{{ updateOrganizationName }}</span>
          </el-form-item>
          <el-form-item label="用户名：" prop="userName">
            <el-input
              v-model="updateForm.userName"
              :disabled="flag"
              placeholder="请输入用户名"
              :size="styleSize"
            />
          </el-form-item>
          <el-form-item label="密码：" :prop="isPassword">
            <el-input
              type="password"
              v-model="updateForm.password"
              :disabled="flag"
              placeholder="请输入密码"
              :size="styleSize"
            />
          </el-form-item>
          <el-form-item label="姓名：" prop="nickName">
            <el-input
              v-model="updateForm.nickName"
              placeholder="请输入名称"
              :size="styleSize"
            />
          </el-form-item>
          <el-form-item label="性别：" prop="sex">
            <el-radio-group v-model="updateForm.sex">
              <el-radio label="0">女</el-radio>
              <el-radio label="1">男</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="手机号：" prop="phone">
            <el-input
              v-model="updateForm.phone"
              placeholder="请输入名称"
              :size="styleSize"
            />
          </el-form-item>
          <el-form-item label="邮箱：" prop="email">
            <el-input
              v-model="updateForm.email"
              placeholder="请输入名称"
              :size="styleSize"
            />
          </el-form-item>

          <el-form-item label="请选择角色：" prop="roleId">
            <el-select v-model="updateForm.roleId" placeholder="请选择角色">
              <el-option
                v-for="(item, key, index) in officeIds"
                :key="index"
                :label="item"
                :value="key * 1"
              />
            </el-select>
          </el-form-item>
        </el-form>
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
    <!-- 点击查看弹出 -->
    <el-dialog
      v-el-drag-dialog
      title="用户权限"
      :visible.sync="XQdialogFormVisible"
      :size="styleSize"
    >
      <el-form :model="detailForm">
        <!-- <el-form-item label="用户昵称：" :label-width="formLabelWidth">
          <el-link :underline="false">{{ detailForm.nickName }}</el-link>
        </el-form-item>
        <el-form-item label="所属组织：" :label-width="formLabelWidth">
          <el-link :underline="false">{{ detailForm.officeName }}</el-link>
        </el-form-item>
        <el-form-item label="用户账号：" :label-width="formLabelWidth">
          <el-link :underline="false">
            {{ detailForm.userName }}
          </el-link>
        </el-form-item>
        <el-form-item label="手机号：" :label-width="formLabelWidth">
          <el-link :underline="false">
            {{ detailForm.userName }}
          </el-link>
        </el-form-item>
        <el-form-item label="邮箱：" :label-width="formLabelWidth">
          <el-link :underline="false">
            {{ detailForm.userName }}
          </el-link>
        </el-form-item> -->
        <!-- <el-form-item label="状态：" :label-width="formLabelWidth">
          <el-link :underline="false">
            {{ detailForm.useable === "0" ? "有效" : "无效" }}
          </el-link>
        </el-form-item>
        <el-form-item label="号码脱敏：" :label-width="formLabelWidth">
          <el-link :underline="false">
            {{ detailForm.isDesensitize === "0" ? "是" : "否" }}
          </el-link>
        </el-form-item> -->

        <el-form-item label="菜单权限：" :label-width="formLabelWidth">
          <el-link
            v-for="item in detailForm.menuSet"
            :key="item"
            :underline="false"
            >{{ item + "," }}</el-link
          >
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="XQdialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="XQdialogFormVisible = false"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <!-- 转移弹窗 -->
    <el-dialog
      v-el-drag-dialog
      title="用户转移"
      :visible.sync="transferDialogFormVisible"
      :size="styleSize"
      width="30%"
    >
      <el-form
        ref="transferFormRole"
        :model="transferForm"
        :rules="transferFormRole"
      >
        <el-form-item
          label="组织名称："
          :label-width="formLabelWidth"
          prop="officeId"
        >
          <el-cascader
            v-model="transferForm.officeId"
            :options="transferOrganization"
            :props="defaultProps"
            clearable
            @change="cascaderChange"
          />
        </el-form-item>
        <el-form-item
          label="角色名："
          :label-width="formLabelWidth"
          prop="roleId"
        >
          <el-select
            v-model="transferForm.roleId"
            placeholder="请选择要转移到的角色名称"
          >
            <el-option
              v-for="(item, key, index) in transferRole"
              :key="index"
              :label="item"
              :value="key"
            />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="状态" prop="useable" :label-width="formLabelWidth">
          <el-radio-group v-model="transferForm.useable">
            <el-radio label="0">有效</el-radio>
            <el-radio label="1">无效</el-radio>
          </el-radio-group>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleTransferCancel">取 消</el-button>
        <el-button type="primary" @click="handleTransfer('transferFormRole')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import defaultSettings from "@/settings";
import elDragDialog from "@/directive/el-drag-dialog";
import { organization_treeselect } from "@/api/systemManage/organization.js";
import debounce from "lodash.debounce";
import {
  user_searchUserList,
  user_stateChange,
  user_detail,
  user_add,
  user_roleMap,
  user_delete,
  user_edit,
  user_transfer,
} from "@/api/systemManage/user.js";
import { mapGetters } from "vuex";
import { deepClone } from "@/utils";
// import { validPassword, checkPhone } from "@/utils/regular";

// 搜索项 类
class SearchForm {
  constructor() {
    this.officeValue = "";
    this.userName = "";
    this.officeName = "";
    this.pageSize = 10;
    this.pageNum = 1;
    this.nickName = "";
    this.margeSelect = "";
  }
}
// 弹出层 类
class DiagioForm {
  constructor() {
    this.nickName = "";
    this.userName = "";
    this.officeId = "";
    this.roleId = "";
    this.no = "";
    this.password = "";
    this.useable = "0";
    this.isDesensitize = "0";
    this.concurrencySum = "";
    this.sex = "";
    this.phone = "";
    this.email = "";
  }
  static getRule() {
    return {
      userName: [
        {
          required: true,
          message: "请输入用户名",
          trigger: "blur",
        },
        // { min: 11, max: 11, message: "请输入11位手机号码", trigger: "blur" },
        // {
        //   pattern:
        //     /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[0-9])\d{8}$/,
        //   message: "请输入正确的手机号码",
        // },
      ],
      roleId: [
        {
          required: true,
          message: "请选择角色",
          trigger: "change",
        },
      ],
      nickName: [
        {
          required: true,
          message: "请输入姓名",
          trigger: "blur",
        },
        {
          message: "请输入中文姓名！",
          trigger: "blur",
        },
      ],
      password: [
        {
          required: true,
          message: "请输入用户密码",
          trigger: "blur",
        },
        {
          pattern:
            /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_!@#$%^&*`~()-+=]+$)(?![0-9\\W_!@#$%^&*`~()-+=]+$)(?![a-zA-Z0-9]+$)(?![a-zA-Z\\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9\\W_!@#$%^&*`~()-+=]+$)(?![0-9A-Z\\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_!@#$%^&*`~()-+=]{8,30}$/,
          message: "请输入数字，字母混合,特殊字符密码并且不小于8位！",
          trigger: "blur",
        },
      ],
      sex: [
        {
          required: true,
          message: "请选择性别",
          trigger: "blur",
        },
      ],
      phone: [
        {
          required: true,
          message: "请输入手机号",
          trigger: "blur",
        },
        {
          required: true,
          pattern:
            /^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\d{8}$/,
          trigger: "blur",
          message: "请输入正确的手机号！",
        },
      ],
      email: [
        { required: true, message: "请输入邮箱", trigger: "blur" },
        {
          pattern:
            /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/,
          trigger: "blur",
          message: "请输入正确的邮箱!",
        },
      ],
    };
  }
}
// 转移 类
class TransferForm {
  constructor() {
    this.id = "";
    this.officeId = "";
    this.roleId = "";
    this.useable = "0";
  }
  static getRole() {
    return {
      officeId: [{ required: true, message: "请选择组织", trigger: "change" }],
      roleId: [{ required: true, message: "请选择角色", trigger: "change" }],
      useable: [{ required: true, message: "请选择状态", trigger: "change" }],
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
      treeList: [],
      // 搜索项
      searchForm: new SearchForm(),
      // 上级菜单
      options: [],
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
      // 转移 form
      transferForm: new TransferForm(),
      transferFormRole: TransferForm.getRole(),
      defaultProps: {
        value: "id",
        checkStrictly: true,
      },
      flag: false,
      addRow: {},
      isPassword: "password",
    };
  },

  mounted() {
    this.handleSearch();
  },
  methods: {
    // 当前选中
    handleNodeClick(data) {
      this.searchForm.officeValue = data.value;
      this.searchForm.id = data.id;
      this.addRow = data;
      this.handleSearch();
    },

    // 筛选项提交
    handleSearch(isShow) {
      isShow ? (this.searchForm.pageNum = 1) : null;
      isShow ? (this.searchForm.officeValue = "") : null;
      this.tableLoading = true;
      user_searchUserList(this.searchForm).then((res) => {
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
    // 点击查看
    handleDetail({ id }) {
      this.XQdialogFormVisible = true;
      user_detail({ id: id }).then((res) => {
        if (res.code === 200) {
          console.log(res);
          this.detailForm = res.data;
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
      // 当添加一级组织时触发

      this.isUpdate = state;
      if (this.isUpdate) {
        this.flag = true;
        this.updateOrganizationName = row.officeName;
        await this.handleIdGetList(false, row.officeId);
        this.updateForm = deepClone(row);
        this.isPassword = "noPassword";
      } else {
        // 根据组织id请求角色列表
        if (this.addRow.id) {
          this.updateForm = new DiagioForm();
          this.handleIdGetList(false, this.addRow.id);
          this.updateOrganizationName = this.addRow.label;
          this.updateForm.officeId = this.addRow.id;
        } else {
          this.$message.warning("请先选择左侧组织！");
          return false;
        }
      }

      this.updateFormVisible = true;
    },
    // 关闭弹窗
    updateFormVisibleClose() {
      this.$refs.dialogForm.resetFields();
      this.updateFormLoading = false;
      this.updateFormVisible = false;
      this.isUpdate = false;
      this.flag = false;
      this.isPassword = "password";
    },
    // 弹窗表单提交
    handleSubmit: debounce(function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (!this.isUpdate) {
            this.updateForm.roleId = this.updateForm.roleId * 1;
            user_add(this.updateForm).then((res) => {
              if (res.code === 200) {
                this.$message.success("添加成功");
                this.handleSearch();
                this.updateFormVisibleClose();
              } else {
                this.$message.success("添加失败");
              }
            });
          } else {
            user_edit(this.updateForm).then((res) => {
              if (res.code === 200) {
                this.$message.success("编辑成功");
                this.handleSearch();
                this.updateFormVisibleClose();
              } else {
                this.$message.success("编辑失败");
              }
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
        `是否${row.useable === "1" ? "启用" : "禁用"}此用户？`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).then(() => {
        user_stateChange({
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
    // 删除组织
    handleRemove(row) {
      this.$confirm("是否删除此用户？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        user_delete(row.id).then((res) => {
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
    // 根据id请求角色列表
    // 第一个参数决定是转移还是添加|编辑
    handleIdGetList(isSown, id) {
      user_roleMap({ id: id }).then((res) => {
        if (res.code === 200) {
          if (isSown === "transfer") {
            this.transferRole = res.data;
            // 转移后重新选择组织如果 组织下没有该角色就重新置空
            this.transferRoleIdReset();
          } else {
            this.officeIds = res.data;
          }
        }
      });
    },
    transferRoleIdReset() {
      const res = Object.keys(this.transferRole).filter((item) => {
        return item == this.transferForm.roleId;
      });

      if (!res.length) {
        this.transferForm.roleId = "";
      }
    },
    // 打开转移弹窗
    async handleTransferOpen(flag, row) {
      this.transferDialogFormVisible = true;
      this.transferForm.useable = row.useable;
      this.transferForm.roleId = row.roleId + "";
      this.transferForm.id = row.id;
      await organization_treeselect().then((result) => {
        if (result.code === 200) {
          this.transferOrganization = result.data;
          this.transferForm.officeId = row.officeId;
        }
      });
      this.handleIdGetList("transfer", this.transferForm.officeId);
    },
    // 转移
    handleTransfer(ref) {
      this.$refs[ref].validate((valid) => {
        if (valid) {
          console.log(this.transferForm.officeId);
          user_transfer({
            ...this.transferForm,
            officeId:
              this.transferForm.officeId[
                this.transferForm.officeId.length - 1
              ] || this.transferForm.officeId,
          }).then((res) => {
            if (res.code === 200) {
              this.$message.success("转移成功");
              this.transferDialogFormVisible = false;
              this.handleSearch();
            }
          });
        } else {
          this.$message.error("请完善信息");
          return false;
        }
      });
    },
    handleTransferCancel() {
      this.$refs.transferFormRole.resetFields();
      this.transferDialogFormVisible = false;
      this.transferForm = new TransferForm();
    },
    // 转移框里面东西改变触发
    cascaderChange(val) {
      this.handleIdGetList(
        "transfer",
        this.transferForm.officeId[val.length - 1]
      );
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
</style>
