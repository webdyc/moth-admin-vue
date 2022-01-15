<template>
  <div class="">
    <el-row :gutter="10">
      <el-col :span="6">
        <el-card>
          <Details :title="'总销售额'" :count="'¥ 12653'">
            <template slot="charts">
              <span>周同比&nbsp;&nbsp;56.67%</span>
              <span>日同比&nbsp;&nbsp;19.93%</span>
            </template>
            <template slot="footer">
              <span>日销售额¥ 12653</span>
            </template>
          </Details>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <Details :title="'访问量'" :count="'88460'">
            <template slot="charts">
              <!-- 折线阴影图 -->
              <LineCard
                canvasId="HeaderCards"
                color="#b78fed"
                width="100%"
                height="65px"
              ></LineCard>
            </template>
            <template slot="footer">
              <span>日访问量 12653</span>
            </template>
          </Details>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <Details :title="'支付笔数'" :count="'12653'">
            <template slot="charts">
              <!-- 柱状图 -->
              <BarCard
                canvasId="HeaderCards2"
                width="100%"
                height="65px"
              ></BarCard>
            </template>
            <template slot="footer">
              <span>转化率 15%</span>
            </template>
          </Details>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <Details :title="'运营活动效果'" :count="'78%'">
            <template slot="charts">
              <!-- 柱状进度图 -->
              <ProgressMarker
                canvasId="HeaderCards3"
                width="100%"
                height="65px"
              ></ProgressMarker>
            </template>
            <template slot="footer">
              <span>周同比&nbsp;&nbsp;56.67%</span>
              <span>日同比&nbsp;&nbsp;19.93%</span>
            </template>
          </Details>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Details from "./detail";
// 卡片折线图
import LineCard from "@/components/Charts/LineCard";
// 卡片柱状图
import BarCard from "@/components/Charts/BarCard";
// 进度条图
import ProgressMarker from "@/components/Charts/ProgressMarker";
export default {
  name: "card",
  data() {
    return {
      msg: "Welcome to your vueName",
    };
  },
  components: {
    Details,
    LineCard,
    BarCard,
    ProgressMarker,
  },
};
</script>

<style scoped lang="scss"></style>
