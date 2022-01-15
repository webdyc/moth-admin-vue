<template>
  <div class="observe-box">
    <el-row :gutter="10">
      <el-col :span="12">
        <el-card>
          <div slot="header" class="flex justify-between">
            <!-- 左侧内容 -->
            <span>线上热门搜索</span>
            <el-dropdown>
              <span class="el-dropdown-link cursor-pointer">
                <i class="el-icon-more"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>操作1</el-dropdown-item>
                <el-dropdown-item>操作2</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <div class="chart-box">
            <!-- 搜索图表 -->
            <el-row :gutter="10">
              <el-col :span="12">
                <SearchCard>
                  <template slot="footer">
                    <!-- 折线阴影图 -->
                    <LineCard
                      canvasId="search-chart"
                      width="100%"
                      height="99px"
                    ></LineCard>
                  </template>
                </SearchCard>
              </el-col>
              <el-col :span="12">
                <SearchCard>
                  <template slot="footer">
                    <!-- 折线阴影图 -->
                    <LineCard
                      canvasId="search-chart2"
                      width="100%"
                      height="99px"
                    ></LineCard>
                  </template>
                </SearchCard>
              </el-col>
            </el-row>
            <el-table :data="tableData" style="width: 100%">
              <el-table-column prop="id" label="排名"> </el-table-column>
              <el-table-column prop="name" label="搜索关键词">
              </el-table-column>
              <el-table-column prop="amount1" sortable label="用户数">
              </el-table-column>
              <el-table-column prop="amount2" sortable label="周涨幅">
              </el-table-column>
            </el-table>
            <el-pagination
              layout="prev, pager, next"
              :total="100"
              class="text-right mt-1"
            >
            </el-pagination>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div slot="header" class="flex justify-between">
            <!-- 左侧内容 -->
            <span>销售额类别占比</span>
            <el-radio-group v-model="ratioType" size="small" class="ratio-type">
              <el-radio-button label="1">全部渠道</el-radio-button>
              <el-radio-button label="2">线上</el-radio-button>
              <el-radio-button label="3">门店</el-radio-button>
            </el-radio-group>
          </div>
          <div class="chart-box">
            <PieMaker
              canvasId="ratioChart"
              title="销售额"
              width="100%"
              height="514px"
            ></PieMaker>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// 热门搜索图表
import SearchCard from "./SearchCard";
// 折线阴影图
import LineCard from "@/components/Charts/LineCard";
// 饼图图表插件
import PieMaker from "@/components/Charts/PieMaker";
export default {
  name: "ObserveCard",
  data() {
    return {
      //   线上热门搜索表格数据
      tableData: [
        {
          id: "1",
          name: "王小虎",
          amount1: "234",
          amount2: "3.2",
          amount3: 10,
        },
        {
          id: "2",
          name: "王小虎",
          amount1: "165",
          amount2: "4.43",
          amount3: 12,
        },
        {
          id: "3",
          name: "王小虎",
          amount1: "324",
          amount2: "1.9",
          amount3: 9,
        },
        {
          id: "4",
          name: "王小虎",
          amount1: "621",
          amount2: "2.2",
          amount3: 17,
        },
        {
          id: "5",
          name: "王小虎",
          amount1: "539",
          amount2: "4.1",
          amount3: 15,
        },
      ],
      // 销售额类别
      ratioType: "1",
    };
  },
  components: { SearchCard, LineCard, PieMaker },
  computed: {},
  watch: {},
  mounted() {},
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
.observe-box {
  margin-top: 10px;
  ::v-deep .el-card__header {
    position: relative;
    .ratio-type {
      position: absolute;
      right: 20px;
      top: 50%;
      transform: translateY(-50%);
    }
  }
  .chart-box {
    width: 100%;
  }
}
</style>
