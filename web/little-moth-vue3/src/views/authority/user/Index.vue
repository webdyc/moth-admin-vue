<template>
  <div class="app-content">
    <!-- 搜索项 -->
    <a-form class="search-form" layout="inline" :model="searchForm">
      <a-form-item label="用户名">
        <a-input
          v-model:value="searchForm.username"
          placeholder="请输入用户名"
        />
      </a-form-item>
      <a-form-item label="真实姓名">
        <a-input
          v-model:value="searchForm.truename"
          placeholder="请输入真实姓名"
        />
      </a-form-item>
      <a-form-item label="手机号">
        <a-input v-model:value="searchForm.phone" placeholder="请输入手机号" />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" @click="handleSearch">
          搜索
        </a-button>
        <a-button> 重置 </a-button>
        <a-button> 导出 </a-button>
      </a-form-item>
    </a-form>
    <!-- 表格配置 -->
    <div class="table-config">
      <a-button type="primary" @click="handleEdit">
        <template #icon>
          <PlusOutlined />
        </template>
        新增
      </a-button>
    </div>
    <!-- 表格内容 -->
    <a-table
      :columns="tableColumns"
      :data-source="tableData"
      :row-selection="rowSelection"
      bordered
    >
      <template #status="{ column, record }">
        <a-switch v-model:checked="record.status" />
      </template>
      <template #action="{ column, record }">
        <a-button default type="text">查看</a-button>
        <a-button type="link">编辑</a-button>
        <a-popconfirm
          title="是否要删除?"
          cancel-text="取消"
          ok-text="确定"
          @confirm="handleDelete(record.key)"
        >
          <a-button danger type="text">删除</a-button>
        </a-popconfirm>
      </template>
    </a-table>
    <!-- 分页 -->
    <a-pagination
      class="table-pagination"
      v-model:current="searchForm.pageSize"
      :total="searchForm.total"
      show-size-changer
      :page-size="searchForm.pageNumber"
      @showSizeChange="onShowSizeChange"
    >
    </a-pagination>
    <!-- 新增/编辑弹出框 -->
    <a-modal
      class="table-modal"
      v-model:visible="updateFormVisible"
      title="添加"
      :confirm-loading="updateFormLoading"
      :afterClose="closeForm"
      @ok="handleSubmit"
    >
      <div class="table-modal-content" v-ant-drag-dialog>
        <a-form :model="updateForm" v-bind="updateFormLayout">
          <a-form-item label="用户名">
            <a-input
              v-model:value="updateForm.username"
              placeholder="请输入用户名"
            />
          </a-form-item>
          <a-form-item label="真实姓名">
            <a-input
              v-model:value="updateForm.truename"
              placeholder="请输入真实姓名"
            />
          </a-form-item>
          <a-form-item label="手机号">
            <a-input
              v-model:value="updateForm.phone"
              placeholder="请输入手机号"
            />
          </a-form-item>
          <a-form-item label="身份证">
            <a-input
              v-model:value="updateForm.care_id"
              placeholder="请输入身份证"
            />
          </a-form-item>
          <a-form-item label="密码">
            <a-input
              v-model:value="updateForm.password"
              placeholder="请输入密码"
            />
          </a-form-item>
          <a-form-item label="角色类型">
            <a-radio-group v-model:value="updateForm.role">
              <a-radio value="admin">超管</a-radio>
              <a-radio value="product">产品管理员</a-radio>
              <a-radio value="user">用户管理</a-radio>
            </a-radio-group>
          </a-form-item>
          <a-form-item label="状态">
            <a-switch v-model:checked="updateForm.status" />
          </a-form-item>
        </a-form>
      </div>
    </a-modal>
  </div>
</template>
<script>
import { reactive, toRefs } from '@vue/reactivity'
import { PlusOutlined } from '@ant-design/icons-vue'
import { user_list, user_create } from '@/api/authority/user'
import { onBeforeMount } from '@vue/runtime-core'
import { message } from 'ant-design-vue'

const columns = [
  {
    title: '用户名',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: '真实姓名',
    dataIndex: 'age',
    key: 'age'
  },
  {
    title: '手机号',
    dataIndex: 'address',
    key: 'address'
  },
  {
    title: '角色类型',
    key: 'tags',
    dataIndex: 'tags'
  },
  {
    title: '状态',
    key: 'status',
    dataIndex: 'status',
    slots: { customRender: 'status' }
  },
  {
    title: '操作',
    key: 'action',
    dataIndex: 'action',
    slots: { customRender: 'action' }
  }
]

const data = [
  {
    key: '1',
    name: 'John Brown',
    age: 32,
    address: 'New York No. 1 Lake Park',
    status: true,
    tags: ['nice', 'developer']
  },
  {
    key: '2',
    name: 'Jim Green',
    age: 42,
    address: 'London No. 1 Lake Park',
    status: false,
    tags: ['loser']
  },
  {
    key: '3',
    name: 'Joe Black',
    age: 32,
    address: 'Sidney No. 1 Lake Park',
    status: true,
    tags: ['cool', 'teacher']
  }
]
// PersonForm的类
class PersonForm {
  // 值
  constructor() {
    // 身份证
    this.care_id = ''
    // 用户名
    this.username = ''
    // 真实姓名
    this.truename = ''
    // 手机号
    this.phone = ''
    // 密码（加密）
    this.password = ''
    // 角色类型
    this.role = ''
    // 禁启用（true：启用，false：禁用）
    this.status = true
  }
  // 验证方法
  static getRule() {
    return {
      title: [{ required: true, message: '请填写标题名称', trigger: 'blur' }],
      title_e: [{ required: true, message: '请填写权限名称', trigger: 'blur' }]
    }
  }
}
export default {
  name: 'User',
  components: { PlusOutlined },
  setup() {
    const state = reactive({
      // 搜索项
      searchForm: {
        // 用户名
        username: '',
        // 手机号
        phone: '',
        // 真实姓名
        truename: '',
        // 每页条数
        pageSize: 1,
        // 当前页码
        pageNumber: 10,
        // 禁启用（true：启用，false：禁用）
        status: ''
      },
      // 状态数组
      statusArr: [
        {
          label: '启用',
          value: true
        },
        {
          label: '禁用',
          value: false
        }
      ],
      // 表格数据
      tableData: data,
      // 表头数据
      tableColumns: columns,
      // 总条数
      total: 0,
      // 表单弹出框
      updateFormVisible: false,
      updateFormLoading: false,
      updateForm: new PersonForm(),
      updateFormRules: PersonForm.getRule(),
      // 弹出框表单宽度参数
      updateFormLayout: {
        labelCol: { span: 6 },
        wrapperCol: { span: 18 }
      }
    })
    // 获取列表
    onBeforeMount(async () => {
      handleSearch()
    })
    // 筛选项提交
    const handleSearch = async () => {
      console.log(state.searchForm)
      user_list().then((res) => {
        state.tableData = res.content.data
        state.total = res.content.total
      })
    }
    // 表格复选框
    const rowSelection = {
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(
          `selectedRowKeys: ${selectedRowKeys}`,
          'selectedRows: ',
          selectedRows
        )
      },
      getCheckboxProps: (record) => ({
        disabled: record.name === 'Disabled User',
        // Column configuration not to be checked
        name: record.name
      })
    }
    // 删除
    const handleDelete = () => {}
    // 分页
    const onShowSizeChange = (current, pageSize) => {
      console.log(pageSize)
      state.searchForm.pageNumber = pageSize
    }
    // 打开弹框
    const handleEdit = () => {
      state.updateFormVisible = true
    }
    // 弹窗表单提交
    const handleSubmit = () => {
      state.updateFormLoading = true
      user_create(state.updateForm).then((res) => {
        let { user } = res.content
        if (user) {
          state.updateFormLoading = false
          state.updateFormVisible = false
          handleSearch()
        } else {
          message.warning(res.msg)
          state.updateFormLoading = false
        }
      })
    }
    // 关闭弹窗
    const closeForm = () => {
      state.updateForm = new PersonForm()
      state.updateFormRules = PersonForm.getRule()
      state.updateFormLoading = false
      state.updateFormVisible = false
    }
    return {
      ...toRefs(state),
      rowSelection,
      handleSearch,
      handleEdit,
      handleSubmit,
      handleDelete,
      closeForm,
      onShowSizeChange
    }
  }
}
</script>
