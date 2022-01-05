<template>
  <!-- 小组件 -->
  <div class="app-content">
    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>Buttons</span>
        </div>
        <div style="margin-bottom: 50px">
          <el-col :span="4" class="text-center">
            <router-link class="pan-btn blue-btn" to="/documentation/index">
              Documentation
            </router-link>
          </el-col>
          <el-col :span="4" class="text-center">
            <router-link class="pan-btn light-blue-btn" to="/icon/index">
              Icons
            </router-link>
          </el-col>
          <el-col :span="4" class="text-center">
            <router-link class="pan-btn pink-btn" to="/excel/export-excel">
              Excel
            </router-link>
          </el-col>
          <el-col :span="4" class="text-center">
            <router-link class="pan-btn green-btn" to="/table/complex-table">
              Table
            </router-link>
          </el-col>
          <el-col :span="4" class="text-center">
            <router-link class="pan-btn tiffany-btn" to="/example/create">
              Form
            </router-link>
          </el-col>
          <el-col :span="4" class="text-center">
            <router-link class="pan-btn yellow-btn" to="/theme/index">
              Theme
            </router-link>
          </el-col>
        </div>
      </el-card>
    </el-row>
    <el-row :gutter="20" style="margin-top: 50px">
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>Material Design 的input</span>
          </div>
          <div style="height: 100px">
            <el-form :model="demo" :rules="demoRules">
              <el-form-item prop="title">
                <md-input
                  v-model="demo.title"
                  icon="el-icon-search"
                  name="title"
                  placeholder="输入标题"
                >
                  标题
                </md-input>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>图片hover效果</span>
          </div>
          <div class="component-item">
            <pan-thumb
              width="100px"
              height="100px"
              image="https://wpimg.wallstcn.com/577965b9-bb9e-4e02-9f0c-095b41417191"
            >
              vue-element-admin
            </pan-thumb>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>水波纹 waves v-directive</span>
          </div>
          <div class="component-item">
            <el-button v-waves type="primary"> 水波纹效果 </el-button>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>hover text</span>
          </div>
          <div class="component-item">
            <mallki class-name="mallki-text" text="vue-element-admin" />
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row style="margin-top: 50px">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>一键复制</span>
        </div>
        <div style="margin-bottom: 50px">
          <el-input
            v-model="inputData"
            placeholder="Please input"
            style="width: 400px; max-width: 100%"
          />
          <el-button
            v-waves
            v-clipboard:copy="inputData"
            v-clipboard:success="clipboardSuccess"
            type="primary"
            icon="el-icon-document"
          >
            copy
          </el-button>
        </div>
      </el-card>
    </el-row>
  </div>
</template>

<script>
import PanThumb from "@/components/PanThumb";
import Mallki from "@/components/TextHoverEffect/Mallki";
import MdInput from "@/components/MDinput";
import clipboard from "@/directive/clipboard/index.js"; // use clipboard by v-directive
import waves from "@/directive/waves/index.js";

export default {
  name: "ClipboardDemo",
  components: {
    Mallki,
    MdInput,
    PanThumb,
  },
  directives: {
    clipboard,
    waves,
  },
  data() {
    const validate = (rule, value, callback) => {
      if (value.length !== 6) {
        callback(new Error("请输入六个字符"));
      } else {
        callback();
      }
    };
    return {
      activeName: "directly",
      inputData: "https://github.com/webdyc/vue-admin-template",
      demo: {
        title: "",
      },
      demoRules: {
        title: [{ required: true, trigger: "change", validator: validate }],
      },
    };
  },
  methods: {
    clipboardSuccess() {
      this.$message({
        message: "Copy successfully",
        type: "success",
        duration: 1500,
      });
    },
  },
};
</script>
<style scoped>
.component-item {
  min-height: 100px;
}
</style>
