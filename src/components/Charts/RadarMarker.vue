<!-- 
/**
 * 2022/1/12, By: webdyc
 * 快捷日期搜索插件，参数说明：
 * title: String, // 时间选择插件中间的字符。 参数：'-' || '至'
 * canvasId: Boolean, // 父组件中注册的回调函数名，返回以数组的日期内容
 */
 -->
<template>
  <div :id="canvasId" :style="{ height: height, width: width }" />
</template>

<script>
import * as echarts from "echarts";
import resize from "./mixins/resize";
export default {
  mixins: [resize],
  props: {
    title: {
      type: String,
      default: "",
    },
    canvasId: {
      type: String,
      default: "RadarMarker",
    },
    width: {
      type: String,
      default: "100%",
    },
    height: {
      type: String,
      default: "100%",
    },
  },
  data() {
    return {
      myCharts: null,
    };
  },
  watch: {
    title() {
      // 重新修改图表配置数据
      this.myCharts.setOption({
        title: {
          text: this.title,
        },
      });
    },
  },
  mounted() {
    this.initChart();
  },
  beforeDestroy() {
    if (!this.myCharts) {
      return;
    }
    this.myCharts.dispose();
    this.myCharts = null;
  },
  methods: {
    // 初始化图表
    initChart() {
      this.myCharts = echarts.init(document.getElementById(this.canvasId));
      this.myCharts.setOption({
        title: {
          text: this.title,
        },
        legend: {
          data: [],
        },
        radar: {
          //   shape: "circle",
          indicator: [
            { name: "引用", max: 6500 },
            { name: "口碑", max: 16000 },
            { name: "产量", max: 30000 },
            { name: "贡献", max: 38000 },
            { name: "热度", max: 52000 },
          ],
        },
        series: [
          {
            name: "Budget vs spending",
            type: "radar",
            data: [
              {
                value: [4200, 3000, 20000, 35000, 50000],
                name: "个人",
              },
              {
                value: [5000, 14000, 28000, 26000, 42000],
                name: "团队",
              },
              {
                value: [2000, 16000, 16000, 16000, 16000],
                name: "部门",
              },
            ],
          },
        ],
        // 布局调试
        grid: {
          left: 0,
          top: 0,
          right: 0,
          bottom: 0,
        },
        // 提示信息
        tooltip: {},
      });
    },
  },
};
</script>
