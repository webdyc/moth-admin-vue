/** text 按钮名称 loading loading效果 @import Function 点击确定时触发
参数为上传成功的列表 exportUrl 下载模板的url importUrl 导出模板的url */

<template>
  <div class="FileExcel">
    <el-button type="success" :size="styleSize" @click="handleImport">{{
      text
    }}</el-button>
    <!--批量导入-->
    <el-dialog
      :title="text"
      :visible="updateFormVisible"
      width="33%"
      :close-on-click-modal="false"
      @close="closeUploadModel"
    >
      <div class="upload-tip-box">
        <div class="upload-tip-btns">
          <div class="upload-tip-btns-item">
            <el-button type="primary" @click="downTemplate" :size="styleSize"
              >下载模板</el-button
            >
          </div>
          <div class="upload-tip-btns-item">
            <el-upload
              class="upload-demo"
              :action="uploadPath"
              :before-upload="beforeUpload"
              :on-remove="handleRemove"
              :on-success="uploadedSuccess"
              :on-error="uploadError"
              multiple
              :limit="3"
              :on-exceed="handleExceed"
              :file-list="fileList"
            >
              <el-button type="success" :size="styleSize">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">
                只能上传.xlsx文件，且不超过50MB
              </div>
            </el-upload>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer dialogFootBtn">
        <el-button @click="callBack(false)" :size="styleSize">取 消</el-button>
        <el-button
          :loading="loading"
          type="primary"
          :size="styleSize"
          @click="callBack(true)"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import defaultSettings from "@/settings";
import { exportDownload } from "@/utils/export";
export default {
  name: "UserManage",

  props: {
    importUrl: {
      type: String,
      default: "/fmsf/fileUpload",
    },
    exportUrl: {
      type: String,
      default: "/manrobot/commission/estimate/export",
    },
    text: {
      type: String,
      default: "导入",
    },
  },
  data() {
    return {
      styleSize: defaultSettings.styleSize,
      loading: false,
      // 文件上传弹出层
      updateFormVisible: false,
      // 文件列表
      fileList: [],
      cloneList: [],
      uploadPath: process.env.VUE_APP_BASE_API + this.importUrl,
    };
  },

  methods: {
    handleImport() {
      this.updateFormVisible = true;
    },

    // 上传之前 处理格式
    beforeUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 50;
      if (!isLt2M) {
        this.$message.error("上传文件大小不能超过50MB!");
      }
      const res = [".xlsx"].filter((item) => {
        return file.name.includes(item);
      });

      if (!res.length) {
        this.$message({
          type: "error", // success error warning
          message: "上传格式错误支持.xlsx格式！",
          duration: 2000,
        });
        return false;
      }
    },

    // 上传成功时
    uploadedSuccess(response, file, fileList) {
      this.cloneList = fileList;
    },
    // 上传失败时
    uploadError(err, file, fileList) {
      this.cloneList = fileList;
      this.$message({
        type: "error",
        message: "上传失败",
        duration: 2000,
      });
    },
    // 删除时
    handleRemove(file, fileList) {
      this.cloneList = fileList;
    },
    // 超出时
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    // 返回父组件信息
    callBack(type) {
      if (type) {
        this.loading = true;
        this.$emit("import", this.cloneList);
      } else {
        this.closeUploadModel();
      }
    },
    // 关闭上传页面
    closeUploadModel() {
      this.loading = false;
      this.updateFormVisible = false;
      this.uploadFinish = false;
      this.cloneList = [];
      this.fileList = [];
    },
    downTemplate() {
      exportDownload({ data: 1 }, this.exportUrl, "k");
    },
  },
};
</script>
<style lang="scss" scoped>
.width_200 {
  width: 200px;
}
.resetPasswords {
  width: 100%;
  text-align: center;
  line-height: 24px;
  span {
    display: block;
    &:last-child {
      color: #d9001b;
    }
  }
}
.upload-tip-box {
  .upload-tip-btns {
    margin: 30px 0;
    display: flex;
    width: 100%;
    flex-direction: column;
    .upload-tip-btns-item {
      margin-top: 10px;
    }
  }
}
.FileExcel {
  display: inline-block;
  margin-left: 10px;
}
</style>
