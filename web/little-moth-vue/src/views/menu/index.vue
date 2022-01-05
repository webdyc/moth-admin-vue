<template>
  <div class="app-content flex">
    <!-- 左侧选择框 -->
    <el-card class="box-card" shadow="never">
      <div slot="header" class="clearfix">
        <span>按钮权限</span>
        <el-button style="float: right; padding: 3px 0" type="text">
          操作按钮
        </el-button>
      </div>
      <div>
        <el-tree
          :data="data"
          :props="defaultProps"
          @node-click="handleNodeClick"
        />
      </div>
    </el-card>
    <!-- 表格 -->
    <el-table
      :data="tableData"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <el-table-column prop="date" label="日期" sortable width="180" />
      <el-table-column prop="name" label="姓名" sortable width="180" />
      <el-table-column prop="address" label="地址" />
    </el-table>

    <!-- 编辑弹出框 -->
    <el-dialog
      :title="isAdd ? '添加菜单' : '编辑菜单'"
      :visible.sync="editVisible"
      width="50%"
      :close-on-click-modal="false"
      :before-close="handleClose"
    >
      <el-form
        ref="updateForm"
        :inline="true"
        :model="updateForm"
        label-width="100px"
        :rules="personFormRules"
      >
        <el-form-item label="名称">
          <el-input
            v-model="updateForm.name"
            autocomplete="off"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="地址" prop="path">
          <el-input
            v-model="updateForm.path"
            autocomplete="off"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="文件路径" prop="component">
          <el-input
            v-model="updateForm.component"
            autocomplete="off"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="重定向">
          <el-input
            v-model="updateForm.redirect"
            autocomplete="off"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input
            v-model="updateForm.title"
            autocomplete="off"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="图标">
          <el-input
            v-model="updateForm.icon"
            autocomplete="off"
            :size="styleSize"
          />
        </el-form-item>
        <el-form-item label="缓存">
          <el-switch v-model="updateForm.keepAlive" />
        </el-form-item>
        <el-form-item label="隐藏">
          <el-switch v-model="updateForm.hidden" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="formClose('updateForm')">取 消</el-button>
        <el-button
          type="primary"
          :loading="formLoading"
          @click="formEdit('updateForm')"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import defaultSettings from "@/settings";
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
    this.redirect = "";
    this.path = "";
    this.name = "";
    this.title = "";
    this.icon = "";
    this.hidden = false;
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
  name: "SecondHouse",
  data() {
    return {
      styleSize: defaultSettings.styleSize,
      listLoading: false,
      // 编辑弹出框
      editVisible: false,
      formLoading: false,
      isAdd: true,
      menuList: [],
      updateForm: new PersonForm(),
      personFormRules: PersonForm.getRule(),
      data: [
        {
          label: "一级 1",
          children: [
            {
              label: "二级 1-1",
              children: [
                {
                  label: "三级 1-1-1",
                },
              ],
            },
          ],
        },
        {
          label: "一级 2",
          children: [
            {
              label: "二级 2-1",
              children: [
                {
                  label: "三级 2-1-1",
                },
              ],
            },
            {
              label: "二级 2-2",
              children: [
                {
                  label: "三级 2-2-1",
                },
              ],
            },
          ],
        },
        {
          label: "一级 3",
          children: [
            {
              label: "二级 3-1",
              children: [
                {
                  label: "三级 3-1-1",
                },
              ],
            },
            {
              label: "二级 3-2",
              children: [
                {
                  label: "三级 3-2-1",
                },
              ],
            },
          ],
        },
      ],
      defaultProps: {
        children: "children",
        label: "label",
      },
      tableData: [
        {
          id: 1,
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          id: 2,
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄",
        },
        {
          id: 3,
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄",
          children: [
            {
              id: 31,
              date: "2016-05-01",
              name: "王小虎",
              address: "上海市普陀区金沙江路 1519 弄",
            },
            {
              id: 32,
              date: "2016-05-01",
              name: "王小虎",
              address: "上海市普陀区金沙江路 1519 弄",
            },
          ],
        },
        {
          id: 4,
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
      tableData1: [
        {
          id: 1,
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          id: 2,
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄",
        },
        {
          id: 3,
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄",
          hasChildren: true,
        },
        {
          id: 4,
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
    };
  },
  created() {},
  mounted() {
    // this.getListData()
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    },
    getListData() {
      this.listLoading = true;
      //   getMenuList().then(res => {
      //     if (res.errcode === 0) {
      //       this.menuList = res.data.map(item => {
      //         return {
      //           id: item.id,
      //           title_e: item.title_e,
      //           label: item.title,
      //           children: item.list.map(child => {
      //             return {
      //               id: child.id,
      //               title_e: child.title_e,
      //               label: child.title,
      //               type: 'child',
      //               pid: item.id
      //             }
      //           })
      //         }
      //       })
      //       this.listLoading = false
      //     } else {
      //       this.listLoading = false
      //       this.$message.error(res.errmsg)
      //     }
      //   })
    },
    // 编辑人员
    handleEdit(row, isAdd) {
      if (row) {
        // 编辑
        if (isAdd) {
          // 添加表单
          this.updateForm = {
            title: "",
            title_e: "",
            p_id: row.id,
            id: "",
          };
        } else {
          // 添加表单
          this.updateForm = {
            title: row.label,
            title_e: row.title_e,
            p_id: row.pid ? row.pid : 0,
            id: row.id,
          };
        }
        this.isAdd = false;
        this.editVisible = true;
      } else {
        // 新增
        this.updateForm = new PersonForm();
        this.isAdd = true;
        this.editVisible = true;
      }
    },
    renderContent(h, { node, data, store }) {
      return (
        <span class="custom-tree-node">
          <span>
            {data.label} <span style="margin-left: 20px">{data.title_e}</span>
          </span>
          <span>
            {data.type === "child" ? (
              ""
            ) : (
              <el-button
                size="mini"
                type="text"
                on-click={() => this.handleEdit(data, true)}
              >
                添加子节点
              </el-button>
            )}
            <el-button
              size="mini"
              type="text"
              on-click={() => this.handleEdit(data, false)}
            >
              修改节点
            </el-button>
            <el-button
              size="mini"
              type="text"
              class="color-danger"
              on-click={() => this.handleDelete(node, data)}
            >
              删除
            </el-button>
          </span>
        </span>
      );
    },
    // 删除
    handleDelete(node, data) {
      // 二次确认删除
      this.$confirm("确定要删除吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          //   deleteMenu({ id: data.id }).then(
          //     res => {
          //       if (res.errcode == 0) {
          //         this.$message.success('删除成功')
          //         this.getListData()
          //       } else {
          //         this.$message.error(res.errmsg)
          //       }
          //     }
          //   )
        })
        .catch(() => {});
    },
    // 表单提交
    formEdit(updateForm) {
      this.$refs[updateForm].validate((valid) => {
        if (this.formLoading) {
          return;
        }
        if (valid) {
          this.formLoading = true;
          //   const pream = JSON.parse(JSON.stringify(this.updateForm))
          //   updateMenu(pream).then(
          //     res => {
          //       if (res.errcode == 0) {
          //         this.formClose(updateForm)
          //         this.$message.success('保存成功')
          //         this.getListData()
          //       } else {
          //         this.formLoading = false
          //         this.$message.error(res.errmsg)
          //       }
          //     }
          //   )
        }
      });
    },
    formClose(updateForm) {
      this.$refs[updateForm].resetFields();
      this.formLoading = false;
      this.editVisible = false;
    },
    handleClose(done) {
      // 清除表单验证
      this.$refs["updateForm"].resetFields();
      done();
    },
  },
};
</script>

<style lang="scss" scoped>
.box-card {
  width: 200px;
  margin-right: 15px;
}
</style>
