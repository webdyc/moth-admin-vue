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
  <!-- 容器 -->
  <div
    :id="canvasId"
    :class="className"
    :style="{ height: height, width: width }"
  />
</template>

<script>
// 引入 echarts
import * as echarts from "echarts";
import resize from "./mixins/resize";
export default {
  mixins: [resize],
  props: {
    title: {
      type: String,
      default: "进度条图",
    },
    color: {
      type: String,
      default: "#67C23A",
    },
    className: {
      type: String,
      default: "ProgressCharts",
    },
    canvasId: {
      type: String,
      default: "ProgressCharts",
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
          // 最小值与最大值的设置
          min: 0,
          max: 100,
        },
        yAxis: {
          // 隐藏y轴
          show: false,
          // 均分
          type: "category",
        },
        series: [
          {
            // 图表格形式
            type: "bar",
            data: [78],
            color: this.color,
            // 柱状图宽度
            barWidth: "10px",
            // 背景颜色
            showBackground: true,
            // 设置背景颜色
            backgroundStyle: {
              color: "#eeeeee",
            },
            // 文本标签
            label: {
              show: true,
              // 改变文本内容
              formatter: "|",
              // 文本标签位置调试
              position: "right",
              color: this.color,
            },
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

<style scoped lang="scss">
.charts {
  width: 100%;
  height: 100%;
}
</style>
