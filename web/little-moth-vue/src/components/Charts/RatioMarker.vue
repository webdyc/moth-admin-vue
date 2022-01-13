<!-- 
/**
 * 2022/1/12, By: webdyc
 * 快捷日期搜索插件，参数说明：
 * title: String, // 时间选择插件中间的字符。 参数：'-' || '至'
 * canvasId: Boolean, // 父组件中注册的回调函数名，返回以数组的日期内容
 */
 -->
<template>
  <div
    :id="canvasId"
    :class="className"
    :style="{ height: height, width: width }"
  />
</template>

<script>
import * as echarts from "echarts";
import resize from "./mixins/resize";
export default {
  mixins: [resize],
  props: {
    title: {
      type: String,
      default: "饼状图",
    },
    canvasId: {
      type: String,
      default: "chart",
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
          left: "left",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          right: "right",
        },
        series: [
          {
            name: this.title,
            type: "pie",
            radius: ["40%", "65%"],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 0,
              borderColor: "#fff",
              borderWidth: 1,
            },
            label: {
              show: true,
              position: "outside",
            },
            labelLine: {
              show: true,
            },
            data: [
              { value: 1048, name: "家用电器" },
              { value: 735, name: "实用酒水" },
              { value: 580, name: "个护健康" },
              { value: 484, name: "服饰箱包" },
              { value: 300, name: "母婴产品" },
              { value: 600, name: "其他" },
            ],
          },
        ],
      });
    },
  },
};
</script>
