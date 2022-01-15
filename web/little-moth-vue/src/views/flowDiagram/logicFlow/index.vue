<template>
  <div class="app-content">
    <div>
      <aside>
        拖拽图形大小问题目前LogicFlow
        1.0版本目前不支持主题中修改大小，需要使用自定义节点设置大小
        <a target="_blank" class="link-type" href="http://logic-flow.org/">
          文档
        </a>
      </aside>
    </div>
    <div class="flex">
      <Panel @mouseDownHandle="mouseDownHandle" />
      <div ref="convas" />
    </div>
  </div>
</template>

<script>
import LogicFlow from "@logicflow/core";
import Panel from "./components/Panel.vue";
import { Control } from "@logicflow/extension";
import "@logicflow/core/dist/style/index.css";
import "@logicflow/extension/lib/style/index.css";
export default {
  components: { Panel },
  data() {
    return {
      lf: LogicFlow,
    };
  },
  mounted() {
    LogicFlow.use(Control);
    this.initLf();
  },
  methods: {
    initLf() {
      this.lf = new LogicFlow({
        width: 1000,
        height: 500,
        container: this.$refs.convas,
        grid: true,
        snapline: true,
        adjustNodePosition: true,
        edgeType: "bezier",
      });
      this.lf.setTheme({
        snapline: {
          stroke: "red",
          strokeWidth: 0.51,
        },
      });
      this.lf.setTheme({
        baseEdge: { stroke: "#ccc", strokeWidth: 2 },
      });
      this.lf.render();
    },
    mouseDownHandle({ style, ...config }) {
      console.log(style);
      this.lf.setTheme(style);
      this.lf.dnd.startDrag(config);
    },
  },
};
</script>

<style lang='scss' scoped>
.flex {
  display: flex;
  width: 1500px;
  .panel {
    margin-right: 30px;
  }
}
</style>
