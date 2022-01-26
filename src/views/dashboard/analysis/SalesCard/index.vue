<template>
  <div class="Sale-box">
    <el-card>
      <div slot="header" class="flex justify-between">
        <!-- 左侧内容 -->
        <el-tabs
          v-model="activeName"
          @tab-click="handleClick"
          class="header-left"
        >
          <el-tab-pane label="销售额" name="sale"></el-tab-pane>
          <el-tab-pane label="访问量" name="visite"></el-tab-pane>
        </el-tabs>
        <!-- 右侧内容 -->
        <div class="header-right">
          <Date :isDefault="'day'" @callBack="handleTime" />
        </div>
      </div>
      <div class="chart-box">
        <el-row :gutter="10">
          <el-col :span="18">
            <!-- 容器 -->
            <div class="charts">
              <BarCharts width="100%" height="300px" :title="title" />
            </div>
          </el-col>
          <el-col :span="6">
            <div class="shop-title">门店{{ title }}排名</div>
            <ul>
              <li v-for="(item, index) in tableData" :key="item.id">
                <span :class="[index < 3 ? 'active' : '', 'index']">
                  {{ index + 1 }}
                </span>
                <span>{{ item.label }}</span>
                <span class="rvalue">{{ item.value }}</span>
              </li>
            </ul>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
// 引入 echarts
import * as echarts from "echarts";
import Date from "@/components/Date";
import BarCharts from "@/components/Charts/BarMarker";
export default {
  name: "SalesCard",
  data() {
    return {
      myCharts: null,
      activeName: "sale",
      //   日期时间
      timeValue: [],
      //   门店销售额排名数据
      tableData: [
        {
          id: 1,
          label: "中关村 1 号店",
          value: "323,234",
        },
        {
          id: 2,
          label: "中关村 2 号店",
          value: "323,234",
        },
        {
          id: 3,
          label: "中关村 3 号店",
          value: "323,234",
        },
        {
          id: 4,
          label: "中关村 4 号店",
          value: "323,234",
        },
        {
          id: 5,
          label: "中关村 5 号店",
          value: "323,234",
        },
        {
          id: 6,
          label: "中关村 6 号店",
          value: "323,234",
        },
        {
          id: 7,
          label: "中关村 7 号店",
          value: "323,234",
        },
      ],
    };
  },
  components: { Date, BarCharts },
  computed: {
    //   标题
    title() {
      return this.activeName === "sale" ? "销售额" : "访问量";
    },
  },
  watch: {},
  methods: {
    //   快捷项日期搜索插件返回函数
    handleTime(data) {
      this.timeValue = data;
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
};
</script>

<style lang="scss" scoped>
.Sale-box {
  margin-top: 10px;
  ::v-deep .el-card__header {
    position: relative;
    border-bottom: none;
    .header-left {
      width: 100%;
    }
    .header-right {
      position: absolute;
      right: 35px;
      top: 12px;
    }
  }
  .chart-box {
    .shop-title {
      margin-top: 0;
      margin-bottom: 0.5em;
      color: rgba(0, 0, 0, 0.85);
      font-weight: 500;
    }
    ul {
      width: 100%;
      height: 300px;
      margin: 25px 0 0;
      font-size: 14px;
      li {
        margin-top: 16px;
        height: 8%;
        .index {
          display: inline-block;
          width: 20px;
          height: 20px;
          margin-top: 1.5px;
          margin-right: 16px;
          font-weight: 600;
          font-size: 12px;
          line-height: 20px;
          text-align: center;
          background-color: #fafafa;
          border-radius: 20px;
        }
        .active {
          color: #fff;
          background-color: #314659;
        }
        .rvalue {
          float: right;
        }
      }
    }
  }
}
</style>
