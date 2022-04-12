=
<template>
  <div class="app-content">
    <div class="flex justify-between">
      <div class="flex-1 mr-5">
        <el-form :model="dataFrom">
          <el-form-item>
            <md-input
              v-model="dataFrom.title"
              icon="el-icon-search"
              name="title"
              placeholder="输入标题"
            >
              标题
            </md-input>
          </el-form-item>
        </el-form>
        <tinymce :value="dataFrom.content" :height="500" @callBack="callBack" />
        <div class="mt-1 text-right">
          <el-button
            v-waves
            v-clipboard:copy="dataFrom.content"
            v-clipboard:success="clipboardSuccess"
            type="primary"
            icon="el-icon-document"
            size="small"
          >
            一键复制
          </el-button>
        </div>
      </div>
      <!-- 手机模态框 -->
      <PhoneDome :content="dataFrom.content" />
      <!-- <div class="editor-content" v-html="content" /> -->
    </div>
  </div>
</template>

<script>
import MdInput from "@/components/MDinput";
import Tinymce from "@/components/Tinymce";
import clipboard from "@/directive/clipboard/index.js"; // use clipboard by v-directive
import waves from "@/directive/waves/index.js";
import PhoneDome from "./phone";

export default {
  name: "TinymceDemo",
  directives: {
    clipboard,
    waves,
  },
  components: { MdInput, Tinymce, PhoneDome },
  data() {
    return {
      dataFrom: {
        title: "",
        content: `<h1 style="text-align: center;">Welcome to the TinyMCE demo!</h1><p style="text-align: center; font-size: 15px;">`,
      },
    };
  },
  methods: {
    // 富文本返回函数
    callBack(data) {
      this.content = data;
    },
    // 一键复制
    clipboardSuccess() {
      this.$message({
        message: "复制成功",
        type: "success",
        duration: 1500,
      });
    },
  },
};
</script>

<style scoped>
.editor-content {
  margin-top: 20px;
}
</style>
