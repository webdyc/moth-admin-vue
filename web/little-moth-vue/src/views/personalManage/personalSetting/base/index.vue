<template>
  <div>
    <div class="title">基础设置</div>
    <div class="content">
      <el-form
        class="content-form"
        ref="updateForm"
        :model="updateForm"
        label-width="80px"
      >
        <el-form-item label="email">
          <el-input v-model="updateForm.email" :size="styleSize"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="updateForm.name" :size="styleSize"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="updateForm.sex" :size="styleSize">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="国家/地区">
          <el-select
            v-model="updateForm.country"
            placeholder="请选择"
            :size="styleSize"
          >
            <el-option label="中国" value="shanghai"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="省或市">
          <el-select
            v-model="updateForm.city"
            placeholder="请选择省或市"
            :size="styleSize"
          >
            <el-option label="北京" value="beijing"></el-option>
            <el-option label="上海" value="shanghai"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="街道地址">
          <el-input v-model="updateForm.district" :size="styleSize"></el-input>
        </el-form-item>
        <el-form-item label="个人简介">
          <el-input
            type="textarea"
            v-model="updateForm.desc"
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
    // email
    this.email = "";
    // 用户名
    this.name = "";
    // 性别
    this.sex = "";
    // 国家/地区
    this.country = "";
    // 省或市
    this.city = "";
    // 街道地址
    this.district = "";
    // 个人简介
    this.desc = "";
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
  .content-form {
    width: 314px;
  }
}
</style>
