<template>
  <div class="app-content">
    <aside>
      富文本是管理后端的核心功能，但同时它也是一个有很多凹坑的地方。在选择富文本的过程中，我也走了很多弯路。市场上常见的富文本已经基本被使用，我最终选择了Tinymce。请参阅更详细的富文本比较和介绍。
      <a
        target="_blank"
        class="link-type"
        href="https://panjiachen.github.io/vue-element-admin-site/feature/component/rich-editor.html"
      >
        文档
      </a>
    </aside>
    <div class="flex justify-between">
      <div class="flex-1 mr-5">
        <tinymce v-model="content" :height="500" />
      </div>
      <!-- 手机模态框 -->
      <PhoneDome :content="content" />
      <!-- <div class="editor-content" v-html="content" /> -->
    </div>
  </div>
</template>

<script>
import Tinymce from "@/components/Tinymce";
import PhoneDome from "./phone";

export default {
  name: "TinymceDemo",
  components: { Tinymce, PhoneDome },
  data() {
    return {
      content: `<h1 style="text-align: center;">Welcome to the TinyMCE demo!</h1><p style="text-align: center; font-size: 15px;">`,
    };
  },
};
</script>

<style scoped>
.editor-content {
  margin-top: 20px;
}
</style>
