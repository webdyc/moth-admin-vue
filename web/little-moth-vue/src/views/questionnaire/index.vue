<template>
  <div class="app-content">
    <VFormDesigner ref="vfDesignerRef"></VFormDesigner>
  </div>
</template>

<script>
import VFormDesigner from '@@/components/form-designer/index.vue'
export default {
  name: "analysis",
  data() {
    return {};
  },
  components: {
     VFormDesigner,
  },
  watch: {},

  methods: {},
};
</script>
<style lang="scss" scoped>
#app .app-content{
  padding:0;
  .main-container{
    margin-left: 0;
    ::v-deep .el-aside.side-panel{
     width: 300px !important;
    }
    ::v-deep aside{
      background:none !important;
      padding: 0;
      .container-widget-item,.field-widget-item{
        width:139px;
      }
    }
  }
}
</style>
