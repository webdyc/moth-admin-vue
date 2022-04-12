<template>
  <div class="music">
    <el-upload
      class="upload-demo"
      :action="uploadPath"
      :on-remove="handleRemove"
      :before-upload="beforeUpload"
      :on-success="handeleSuccess"
      :on-error="handelError"
      multiple
      :on-exceed="handleExceed"
      :file-list="fileList"
    >
      <el-button size="small" type="primary">{{ text }}</el-button>
      <div slot="tip" class="el-upload__tip displayNone">
        支持wav,mp3音频格式
      </div>
    </el-upload>
  </div>
</template>

<script>
export default {
  props: {
    size: {
      type: Number,
      default: 5,
    },
    uploadUrl: {
      type: String,
      default: "/fmsf/fileUpload",
    },
    text: {
      type: String,
      default: "上传本地录音",
    },
  },
  data() {
    return {
      fileList: [],
      uploadPath: process.env.VUE_APP_BASE_API + this.uploadUrl,
      fileUrl: [],
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },

    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    // 上传前
    beforeUpload(file) {
      const res = ["mp3", "wav"].filter((item) => {
        return file.name.includes(item);
      });
      const isSize = file.size / 1024 / 1024 < this.size;
      if (res.length) {
        console.log(res);
      } else {
        this.$message.error("只支持mp3,wav格式！");
        return false;
      }
      if (isSize) {
        console.log(isSize);
      } else {
        this.$message.error(`大小为${this.size}mb!`);
        return false;
      }
    },
    // 上传成功
    handeleSuccess(response, file, fileList) {
      this.$emit("musicurl", file);
    },
    // 上传失败
    handelError() {
      this.$message.error("上传失败");
    },
  },
};
</script>

<style scoped lang='scss'>
::v-deep .el-upload-list {
  display: none;
}
</style>
