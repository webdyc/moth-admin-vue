<template>
  <div class="app-box">
    <div class="app-box-table">
      <!-- 左侧选择框 -->
      <el-card class="box-card" shadow="never">
        <div slot="header" class="clearfix">
          <span>组织结构</span>
          <!-- <el-button
            style="float: right; padding: 3px 0"
            type="text"
            @click="handleAddfist"
          >
            新增一级组织
          </el-button> -->
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
              :title="data.label"
              class="tree-label"
            >
              {{ data.label }}
            </span>
          </el-tree>
        </div>
      </el-card>
      <div class="app-box-table-box">
        <!-- 搜索项 -->
        <el-form :inline="true" :model="searchForm" class="demo-form-inline">
          <el-form-item label="组织名称：">
            <el-input v-model.trim="searchForm.name" :size="styleSize" />
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
            新增组织
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
          <el-table-column prop="parentName" label="上级组织" align="center" />
          <el-table-column prop="userName" label="创建人" align="center" />
          <el-table-column prop="createTime" label="创建日期" align="center" />
          <el-table-column prop="remarks" label="备注" align="center" />
          <!-- <el-table-column
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
          </el-table-column> -->
          <el-table-column label="操作" align="center" width="200">
            <template slot-scope="scope">
              <!-- <el-link
                class="mr-1"
                type="success"
                :underline="false"
                @click="handleEdit(false, scope.row)"
              >
                新增
              </el-link> -->
              <el-link
                class="mr-1"
                type="primary"
                :underline="false"
                @click="handleEdit(true, scope.row)"
              >
                编辑
              </el-link>
              <!-- <el-link
                class="mr-1"
                type="warning"
                :underline="false"
                @click="handleAddInfo(scope.row)"
              >
                附加信息
              </el-link> -->
              <!-- <el-link
                class="mr-1"
                type="info"
                :underline="false"
                @click="handleDetail(scope.row)"
              >
                查看
              </el-link> -->
              <!-- <el-link
                class="mr-1"
                type="success"
                :underline="false"
                @click="handleEdit('detail', scope.row)"
              >
                短信配置
              </el-link> -->
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
            <el-form-item label="描述：" label-width="100px">
              <el-input
                v-model="updateForm.detail"
                type="textarea"
                placeholder="请输入备注"
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
    <!-- 点击查看弹出 -->
    <el-dialog
      v-el-drag-dialog
      title="组织详情"
      :visible.sync="XQdialogFormVisible"
      :size="styleSize"
    >
      <el-form :model="detailForm">
        <el-form-item label="组织名称：" :label-width="formLabelWidth">
          <el-link :underline="false">{{ detailForm.name }}</el-link>
        </el-form-item>
        <el-form-item label="接口账号：" :label-width="formLabelWidth">
          <el-link :underline="false">{{
            detailForm.accountNo
          }}</el-link></el-form-item
        >
        <el-form-item label="上级组织：" :label-width="formLabelWidth">
          <el-link :underline="false">{{ detailForm.parentName }}</el-link>
        </el-form-item>
        <el-form-item label="下级组织：" :label-width="formLabelWidth">
          <el-link
            v-for="item in detailForm.offeceSon"
            :key="item"
            :underline="false"
            >{{ item + "," }}</el-link
          >
        </el-form-item>
        <el-form-item label="组织说明：" :label-width="formLabelWidth">
          <el-link :underline="false">
            {{ detailForm.remarks }}
          </el-link>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="XQdialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="XQdialogFormVisible = false"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <el-dialog
      v-el-drag-dialog
      title="附加信息"
      :visible.sync="addInfodialogFormVisible"
      :size="styleSize"
    >
      <el-form :model="detailForm" :size="styleSize" inline>
        <el-form-item label="ESOP e55计费号：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.esopE55Billing" :size="styleSize" />
        </el-form-item>
        <el-form-item label="计费用户归属地区：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.attribution" :size="styleSize" />
        </el-form-item>
        <el-form-item label="能力开放平台应用：" :label-width="formLabelWidth">
          <el-input
            v-model="addInfoForm.abilityPlatformAppId"
            :size="styleSize"
          />
        </el-form-item>
        <!-- productInventoryId -->
        <el-form-item label="所属产品：" :label-width="formLabelWidth">
          <el-select
            v-model="addInfoForm.productInventoryId"
            placeholder="请选择"
            :size="styleSize"
          >
            <el-option
              v-for="item in productData"
              :key="item.id"
              :label="item.esopProName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产品属性：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.productAttr" :size="styleSize" />
        </el-form-item>

        <el-form-item label="通话时长限制：" :label-width="formLabelWidth">
          <div style="display: flex">
            <el-input v-model="addInfoForm.callDurationLimit" :size="styleSize">
              <template slot="append"> 分钟 </template>
            </el-input>
          </div>
        </el-form-item>
        <div>
          <el-form-item
            label="套餐时间范围："
            prop="resource"
            :label-width="formLabelWidth"
          >
            <el-radio-group v-model="addInfoForm.enableUnlimited">
              <el-radio label="enable">无限制</el-radio>
              <el-radio label="disable">选择时间范围</el-radio>
            </el-radio-group>
            <el-date-picker
              v-if="addInfoForm.enableUnlimited == 'disable'"
              v-model="timeValue"
              class="ml-1"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd HH:mm:ss"
              format="yyyy-MM-dd HH:mm:ss"
              :size="styleSize"
            />
          </el-form-item>
        </div>

        <el-form-item label="短信apiKey：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.apiKey" :size="styleSize" />
        </el-form-item>
        <el-form-item label="短信secretKey：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.secretKey" :size="styleSize" />
        </el-form-item>
        <el-form-item label="闪信apiKey：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.flashApiKey" :size="styleSize" />
        </el-form-item>
        <el-form-item label="闪信secretKey：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.flashSecretKey" :size="styleSize" />
        </el-form-item>
        <el-form-item label="视频短信apiKey：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.videomsgApiKey" :size="styleSize" />
        </el-form-item>
        <el-form-item label="视频短信secretKey：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.videomsgSecretKey" :size="styleSize" />
        </el-form-item>
        <el-form-item label="5G消息apiKey：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.msg5gApiKey" :size="styleSize" />
        </el-form-item>
        <el-form-item label="5G消息secretKey：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.msg5gSecretKey" :size="styleSize" />
        </el-form-item>
        <el-form-item label="短信企业名称：" :label-width="formLabelWidth">
          <el-input v-model="addInfoForm.smsCompanyName" :size="styleSize" />
        </el-form-item>
        <el-form-item
          label="是否回传呼叫结果："
          prop="resource"
          :label-width="formLabelWidth"
        >
          <el-radio-group v-model="addInfoForm.isCallback">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          v-if="addInfoForm.isCallback == '1'"
          label="回传地址："
          :label-width="formLabelWidth"
        >
          <el-input v-model="addInfoForm.callbackUrl" :size="styleSize" />
        </el-form-item>
        <el-form-item
          v-if="addInfoForm.isCallback == '1'"
          label="回传参数："
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="addInfoForm.callbackParams"
            placeholder="回传参数，逗号分隔"
            :size="styleSize"
            type="textarea"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addInfodialogFormVisibleClose">取 消</el-button>
        <el-button
          type="primary"
          :loading="updateFormLoading"
          @click="addInfosubmint"
          >确 定</el-button
        >
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

// import { deepClone, debounce } from "@/utils/index";
import debounce from "lodash.debounce";
class AddInfo {
  constructor() {
    // 编辑时：必
    // this.id = "";
    // 组织id
    this.officeId = "";
    // ESOP e55计费号
    this.esopE55Billing = "";
    // 计费用户归属地区
    this.attribution = "";
    // 能力开放平台应用ID
    this.abilityPlatformAppId = "";
    // 产品清单ID
    this.productInventoryId = "";
    // 产品属性
    this.productAttr = "";
    // 套餐时间范围无限制停用启用 enable/disable
    this.enableUnlimited = "enable";
    // 套餐生效时间
    this.productStartTime = "";
    // 套餐失效时间
    this.productEndTime = "";
    // 通话可使用时长限制 分钟
    this.callDurationLimit = "";
    // 短信apikey
    this.apiKey = "";
    // 短信secretKey
    this.secretKey = "";
    // 闪信apikey
    this.flashApiKey = "";
    // 闪信secretKey
    this.flashSecretKey = "";
    // 短信企业名称
    this.smsCompanyName = "";
    // 视频短信apikey
    this.videomsgApiKey = "";
    // 视频短信secretKey
    this.videomsgSecretKey = "";
    // 5G消息apikey
    this.msg5gApiKey = "";
    // 5G消息secretKey
    this.msg5gSecretKey = "";
    // 是否回传呼叫结果
    this.isCallback = 0;
    // 回传地址
    this.callbackUrl = "";
    // 回传参数，逗号分隔
    this.callbackParams = "";
  }
  // static addInfoFormRule() {
  //   return {
  //     esopE55Billing: [
  //       { required: true, message: "请输入ESOP e55计费号", trigger: "blur" },
  //     ],
  //     attribution: [
  //       { required: true, message: "请输入计费用户归属地区", trigger: "blur" },
  //     ],
  //     abilityPlatformAppId: [
  //       { required: true, message: "请输入能力开放平台应用ID", trigger: "blur" },
  //     ],
  //   };
  // }
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
      searchForm: {
        parentIds: "",
        // 客户姓名
        name: "",
        // 每页条数
        pageSize: 10,
        // 当前页码
        pageNum: 1,
      },
      // 上级菜单
      options: [],
      // 表格数据
      formLabelWidth: "180px",
      // 表哥loading
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
      updateForm: {
        name: "",
        detail: "",
        id: null,
      },
      updateFormRules: {
        name: [
          { required: true, message: "请输入组织名称", trigger: "blur" },
          {
            min: 4,
            max: 16,
            message: "长度在 4 到 16 个字符",
            trigger: "blur",
          },
        ],
        detail: [
          { required: true, message: "请输入描述", trigger: "blur" },
          {
            max: 50,
            message: "最大输入50字",
            trigger: "blur",
          },
        ],
      },
      detailForm: {},
      // 详情弹层
      XQdialogFormVisible: false,
      // 附加信息弹窗
      addInfodialogFormVisible: false,
      // 附加信息form
      addInfoForm: new AddInfo(),
      timeValue: [],
      productData: [],
      addRow: {},
    };
  },

  mounted() {
    this.handleSearch();
  },
  methods: {
    // 当前选中
    handleNodeClick(data) {
      this.searchForm.parentIds = data.value;
      this.searchForm.id = data.id;
      this.addRow = data;
      // console.log(data);
      this.handleSearch();
    },
    openDialog() {
      this.updateFormVisible = true;
    },
    // async semanticF(data) {
    //   await semantic(data);
    // },
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
    // 点击查看
    handleDetail({ id }) {
      this.XQdialogFormVisible = true;
      organization_detail(id).then((res) => {
        if (res.code === 200) {
          this.detailForm = res.data;
        }
      });
    },
    // 筛选项重置
    handleReset() {
      this.searchInit();
      this.handleSearch();
    },
    // // 添加一级组织触发
    // handleAddfist() {
    //   this.handleEdit("parent");
    // },
    // 打开弹框
    handleEdit(state, row) {
      // 当添加一级组织时触发
      // if (state === "detail") {
      //   this.$router.push({
      //     path: "/accountManage/smsConfigurationDetail",
      //     query: { id: row.id },
      //   });
      // }
      if (state === "parent") {
        this.updateFormInit();
      } else {
        this.isUpdate = state;
        if (this.isUpdate) {
          this.updateForm.detail = row.remarks;
          this.updateForm.id = row.id;
          this.updateForm.name = row.name;
        } else {
          if (this.addRow.id) {
            this.updateFormInit();
            this.updateForm.id = this.addRow.id;
          } else {
            this.$message.warning("请先选择左侧组织");
            return false;
          }
        }
      }
      this.updateFormVisible = true;
    },
    //
    // 关闭弹窗
    updateFormVisibleClose() {
      this.$refs.dialogForm.resetFields();
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
                // if (res.data.flag === 1) {
                //   this.semanticF({
                //     applicationName: "组织默认应用",
                //     companyId: res.data.officeId,
                //   });
                // }
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
    // 关闭附加信息弹窗
    addInfodialogFormVisibleClose() {
      this.addInfoForm = new AddInfo();
      this.addInfodialogFormVisible = false;
      this.updateFormLoading = false;

      this.timeValue = [];
    },
    // 附加信息
    handleAddInfo(row) {
      console.log(this.addInfoForm.enableUnlimited);
      this.addInfodialogFormVisible = true;
      officePeculiar({ officeId: row.id }).then((res) => {
        if (res.code === 200) {
          this.addInfoForm = res.data;
          if (res.data.productStartTime && res.data.productEndTime) {
            this.timeValue[0] = res.data.productStartTime;
            this.timeValue[1] = res.data.productEndTime;
          } else {
            this.timeValue = [];
          }
          if (!res.data.enableUnlimited) {
            this.addInfoForm.enableUnlimited = "enable";
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    // 附加信息确定
    addInfosubmint() {
      this.updateFormLoading = true;
      if (this.addInfoForm.enableUnlimited == "disable") {
        this.addInfoForm.productStartTime = this.timeValue[0]
          ? this.timeValue[0]
          : "";
        this.addInfoForm.productEndTime = this.timeValue[1]
          ? this.timeValue[1]
          : "";
      }
      addInfo(this.addInfoForm).then((res) => {
        if (res.code === 200) {
          this.handleSearch();
          this.addInfodialogFormVisibleClose();
        } else {
          this.$message.error(res.msg);
        }
      });
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
