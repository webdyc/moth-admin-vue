<template>
  <el-form-item :label="i18nt('designer.setting.checkStrictly')">
    <el-checkbox v-model="optionModel.checkStrictly"></el-checkbox>
  </el-form-item>
</template>

<script>
  import i18n from "@@/utils/i18n"

  export default {
    name: "checkStrictly-editor",
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
