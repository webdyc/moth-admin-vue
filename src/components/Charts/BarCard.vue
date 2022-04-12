<!-- 
/**
 * 2022/1/12, By: webdyc
 * 快捷日期搜索插件，参数说明：
 * title: String, // 时间选择插件中间的字符。 参数：'-' || '至'
 * color: String, 是否需要默认选中项，参数：day || week || month || year
 * className: Array, // 需要传入回显日期值。 参数：['2022-01-12', '2022-01-12']
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
      default: "卡片柱状图",
    },
    color: {
      type: String,
      default: "#27A6FE",
    },
    className: {
      type: String,
      default: "BarCard",
    },
    canvasId: {
      type: String,
      default: "BarCard",
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
        xAxis: {
          // 隐藏x轴
          show: false,
          // 均分
          type: "category",
        },
        yAxis: {
          // 隐藏y轴
          show: false,
        },
        series: [
          {
            // 图表格形式
            type: "bar",
            data: [10, 7, 33, 12, 88, 9, 29, 10, 44, 33, 22, 88],
            color: this.color,
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
