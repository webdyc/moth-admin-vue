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
            v-for="item in statusArr"
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
        <el-button type="success" :size="styleSize" @click="handleExport">
          导出
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
      <FileExcel ref="fileExcel" @import="importFile" />
    </div>
    <!-- 表格内容 -->
    <el-table
      v-loading="tableLoading"
      :data="tableData"
      border
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="name" label="姓名" align="center" />
      <el-table-column prop="typeName" label="客户类型" align="center" />
      <el-table-column prop="phone" label="客户号码" align="center" />
      <el-table-column prop="secondPhone" label="备用号码" align="center" />
      <el-table-column prop="provinceCityRegion" label="地区" align="center" />
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
              v-for="item in statusArr"
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
import { exportDownload } from "@/utils/export";
import FileExcel from "@/components/upload/file";
// PersonForm的类
class PersonForm {
  // 值
  constructor() {
    // 身份证
    this.care_id = "";
    // 用户名
    this.username = "";
    // 真实姓名
    this.truename = "";
    // 手机号
    this.phone = "";
    // 密码（加密）
    this.password = "";
    // 角色类型
    this.role = "";
    // 禁启用（true：启用，false：禁用）
    this.status = false;
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
  directives: { elDragDialog },
  components: {
    FileExcel,
  },
  data() {
    return {
      styleSize: defaultSettings.styleSize,
      // 搜索项
      searchForm: {
        user: "",
        region: "",
        page: 1,
        pageSize: 10,
      },
      //   时间段日期
      timeValue: [],
      // 状态数组
      statusArr: [
        {
          label: "启用",
          value: true,
        },
        {
          label: "禁用",
          value: false,
        },
      ],
      // 表格数据
      formLabelWidth: "140px",
      tableLoading: false,
      tableData: [
        {
          key: "1",
          name: "John Brown",
          age: 32,
          address: "New York No. 1 Lake Park",
          status: true,
          tags: ["nice", "developer"],
        },
        {
          key: "2",
          name: "Jim Green",
          age: 42,
          address: "London No. 1 Lake Park",
          status: false,
          tags: ["loser"],
        },
        {
          key: "3",
          name: "Joe Black",
          age: 32,
          address: "Sidney No. 1 Lake Park",
          status: true,
          tags: ["cool", "teacher"],
        },
      ],
      // 多选数组
      multipleSelection: [],
      // 总条数
      total: 400,
      // 表单弹出框
      isUpdate: false,
      updateFormVisible: false,
      updateFormLoading: false,
      updateForm: new PersonForm(),
      updateFormRules: PersonForm.getRule(),
    };
  },
  methods: {
    /** 筛选项提交 */
    handleSearch() {
      const params = {
        starTime: this.timeValue[0],
        endTime: this.timeValue[1],
      };
      Object.assign(this.searchForm, params);
      this.tableLoading = true;
      // api(this.searchForm).then((res) => {
      //   this.tableData = res.data.list;
      //   this.total = res.data.total;
      //   this.tableLoading = false;
      // });
      this.tableLoading = false;
      console.log("submit!");
    },
    /** 筛选项重置 */
    handleReset() {
      // 搜索项
      this.searchForm = {
        user: "",
        region: "",
        page: 1,
        pageSize: 10,
      };
      //   时间段日期
      this.imeValue = [];
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
    /** 弹窗表单提交 */
    handleSubmit() {
      console.log(this.updateForm);
      // this.updateFormVisibleClose()
    },
    /** 导出 */
    async handleExport() {
      const params = {
        starTime: 1,
        endTime: 2,
      };
      exportDownload(params, "/manrobot/commission/estimate/export", "文件名");
    },
    /** 导入 */
    importFile(data) {
      console.log(data);
      if (data.length) {
        this.$refs.fileExcel.closeUploadModel();
      } else {
        console.log("文件不能为空");
      }
    },
    /** 多选 */
    handleSelectionChange(val) {
      this.multipleSelection = val.map((item) => item.id);
    },
    /** 删除 */
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
      this.searchForm.pageNum = 1;
      this.searchForm.pageSize = val;
      this.handleSearch();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.searchForm.pageNum = val;
      this.handleSearch();
    },
  },
};
</script>
