<template>
  <div class="search-card">
    <!-- 容器 -->
    <div :id="canvasId" :style="{ height: height, width: width }" />
  </div>
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
      default: "卡片折线图",
    },
    color: {
      type: String,
      default: "#27A6FE",
    },
    canvasId: {
      type: String,
      default: "LineShadowMarker",
    },
    width: {
      type: String,
      default: "200px",
    },
    height: {
      type: String,
      default: "400px",
    },
  },
  data() {
    return {};
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
            type: "line",
            data: [10, 7, 33, 12, 48, 9, 29, 10, 44],
            //   节点圆润
            smooth: true,
            // 拐点的样式设置
            itemStyle: {
              opacity: 0,
            },
            // 线条样式
            lineStyle: {
              color: this.color,
            },
            // 区域填充样式
            areaStyle: {
              color: {
                type: "linear",
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  {
                    offset: 0,
                    color: this.color, // 0% 处的颜色
                  },
                  {
                    offset: 1,
                    color: "white", // 100% 处的颜色
                  },
                ],
                global: false, // 缺省为 false
              },
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
      });
    },
  },
};
</script>

<style scoped lang="scss"></style>
