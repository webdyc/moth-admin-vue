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
      default: "柱状图",
    },
    color: {
      type: String,
      default: "#27A6FE",
    },
    className: {
      type: String,
      default: "chart",
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
          text: `${this.title}`,
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 指示器样式
            type: "shadow",
            shadowStyle: {
              color: "rgba(0, 0, 0, 0.1)",
            },
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: [
          {
            type: "category",
            data: [
              "1月",
              "2月",
              "3月",
              "4月",
              "5月",
              "6月",
              "7月",
              "8月",
              "9月",
              "10月",
              "11月",
              "12月",
            ],
            axisTick: {
              alignWithLabel: true,
            },
          },
        ],
        yAxis: [
          {
            type: "value",
            splitLine: {
              show: true,
              lineStyle: {
                type: "dashed",
              },
            },
          },
        ],
        series: [
          {
            name: "Direct",
            type: "bar",
            barWidth: "40%",
            data: [10, 52, 200, 334, 390, 330, 220, 110, 153, 80, 123, 44],
            color: this.color,
          },
        ],
      });
    },
  },
};
</script>
