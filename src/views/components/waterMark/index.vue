<template>
  <div class="app-content">
    <el-button type="primary" @click="addWaterMarker">生成水印</el-button>
  </div>
</template>

<script>
import waterMark from "@/utils/waterMark";
export default {
  data() {
    return {
      options: {},
    };
  },
  methods: {
    addWaterMarker() {
      this.options.fillStyle = "#000";
      waterMark(this.options);
    },
  },
};
</script>

<style>
</style>