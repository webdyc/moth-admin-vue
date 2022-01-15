<template>
  <div class="app-box">
    <!-- 头部图表 -->
    <HeaderCards></HeaderCards>
    <!-- 销售额、访问量 -->
    <SalesCard></SalesCard>
    <!-- 热门搜索、销售额类别占比 -->
    <ObserveCard></ObserveCard>
    <!-- 转化率图表 -->
    <RatioCard></RatioCard>
  </div>
</template>

<script>
import HeaderCards from "./HeaderCards";
import SalesCard from "./SalesCard";
import ObserveCard from "./ObserveCard";
import RatioCard from "./RatioCard";
export default {
  name: "analysis",
  data() {
    return {};
  },
  components: {
    HeaderCards,
    SalesCard,
    ObserveCard,
    RatioCard,
  },
  watch: {},

  methods: {},
};
</script>
<style lang="scss" scoped></style>
