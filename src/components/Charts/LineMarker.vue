<template>
  <div :id="id" :class="className" :style="{ height: height, width: width }" />
</template>

<script>
import * as echarts from "echarts";
import resize from "./mixins/resize";
export default {
  mixins: [resize],
  props: {
    title: {
      type: String,
      default: "折线图",
    },
    color: {
      type: String,
      default: "#27A6FE",
    },
    className: {
      type: String,
      default: "chart",
    },
    id: {
      type: String,
      default: "chart",
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
    initChart() {
      this.myCharts = echarts.init(document.getElementById(this.id));
      this.myCharts.setOption({
        title: {
          text: this.title,
        },
        tooltip: {
          trigger: "axis",
        },
        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        legend: {
          show: true,
        },
        dataZoom: {},
        // 配置数据
        xAxis: {
          // 均分
          type: "category",
          boundaryGap: false,
          data: [
            "04:00",
            "05:00",
            "06:00",
            "07:00",
            "08:00",
            "09:00",
            "10:00",
            "12:00",
            "13:00",
            "14:00",
            "15:00",
            "16:00",
            "17:00",
            "18:00",
            "19:00",
            "20:00",
            "21:00",
          ],
        },
        yAxis: {
          // 均分
          type: "category",
          type: "value",
        },
        series: [
          {
            name: "客流量",
            type: "line",
            data: [
              75, 109, 48, 47, 93, 71, 86, 37, 21, 86, 18, 85, 25, 88, 103, 12,
              42,
            ],
            // 拐点的样式设置
            itemStyle: {
              opacity: 1,
              color: "#34cccc",
            },
            // 线条样式
            lineStyle: {
              color: "#34cccc",
            },
          },
          {
            name: "支付笔数",
            type: "line",
            data: [
              85, 56, 29, 43, 22, 108, 74, 90, 68, 109, 40, 72, 61, 74, 94, 61,
              36,
            ],
            // 拐点的样式设置
            itemStyle: {
              opacity: 1,
              color: "#5dafff",
            },
            // 线条样式
            lineStyle: {
              color: "#5dafff",
            },
          },
        ],
        // 布局调试
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
      });
    },
  },
};
</script>
