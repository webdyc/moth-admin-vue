<template>
  <el-form-item :label="i18nt('designer.setting.border')">
    <el-checkbox v-model="optionModel.border"></el-checkbox>
  </el-form-item>
</template>

<script>
  import i18n from "@@/utils/i18n"

  export default {
    name: "border-editor",
    mixins: [i18n],
    props: {
      designer: Object,
      selectedWidget: Object,
      optionModel: Object,
    },
  }
</script>

<style scoped>

</style>
