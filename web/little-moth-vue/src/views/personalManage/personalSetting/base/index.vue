<template>
  <div>
    <div class="title">基础设置</div>
    <div class="content">
      <el-form class="content-form" ref="form" :model="form" label-width="80px">
        <el-form-item label="email">
          <el-input v-model="form.name" :size="styleSize"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.name" :size="styleSize"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.resource" :size="styleSize">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="国家/地区">
          <el-select
            v-model="form.region"
            placeholder="请选择"
            :size="styleSize"
          >
            <el-option label="中国" value="shanghai"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="省或市">
          <el-select
            v-model="form.region"
            placeholder="请选择省或市"
            :size="styleSize"
          >
            <el-option label="北京" value="beijing"></el-option>
            <el-option label="上海" value="shanghai"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="街道地址">
          <el-input v-model="form.name" :size="styleSize"></el-input>
        </el-form-item>
        <el-form-item label="个人简介">
          <el-input
            type="textarea"
            v-model="form.desc"
            placeholder="自我介绍"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" :size="styleSize"
            >更新资料</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import defaultSettings from "@/settings";
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
  data() {
    return {
      styleSize: defaultSettings.styleSize,
      // 表单信息
      updateFormLoading: false,
      updateForm: new PersonForm(),
      updateFormRules: PersonForm.getRule(),
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    },
  },
};
</script>
<style lang="scss" scoped>
.title {
  margin-bottom: 12px;
  color: rgba(0, 0, 0, 0.85);
  font-weight: 500;
  font-size: 20px;
  line-height: 28px;
}
.content {
  display: flex;
  padding-top: 12px;
}
</style>
