<!-- 
/**
 * 2022/1/12, By: webdyc
 * 快捷日期搜索插件，参数说明：
  * angeSeparator: String, // 时间选择插件中间的字符。 参数：'-' || '至'
 * isDefault: String, 是否需要默认选中项，参数：day || week || month || year
 * timeDefault: Array, // 需要传入回显日期值。 参数：['2022-01-12', '2022-01-12']
 * callBack: Boolean, // 父组件中注册的回调函数名，返回以数组的日期内容
 */
 -->
<template>
  <div class="date-box">
    <!-- 快捷项 -->
    <span
      v-for="item in dateArry"
      :key="item.dateKey"
      :class="[dateKey === item.dateKey ? 'active' : '', 'cursor-pointer']"
      @click="handleShortcut(item.dateKey)"
      >{{ item.label }}</span
    >
    <!-- 日期选择 -->
    <el-date-picker
      class="ml-2"
      v-model="timeValue"
      type="daterange"
      :range-separator="angeSeparator"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      value-format="yyyy-MM-dd"
      size="small"
    >
    </el-date-picker>
  </div>
</template>

<script>
import dayjs from "dayjs";
import { equalsArray } from "@/utils";
export default {
  name: "DateShortcut",
  props: {
    angeSeparator: {
      type: String,
      default: "~",
    },
    isDefault: {
      type: String,
      default: "",
    },
    timeDefault: {
      type: Array,
      default: () => [],
    },
  },
  watch: {
    timeValue(newValue) {
      if (newValue) this.handleTime(newValue);
    },
  },
  data() {
    return {
      // 快捷组
      dateArry: [
        {
          label: "今日",
          value: [dayjs().format("YYYY-MM-DD"), dayjs().format("YYYY-MM-DD")],
          dateKey: "day",
        },
        {
          label: "本周",
          value: [
            dayjs().day(1).format("YYYY-MM-DD"),
            dayjs().day(7).format("YYYY-MM-DD"),
          ],
          dateKey: "week",
        },
        {
          label: "本月",
          value: [
            dayjs().startOf("month").format("YYYY-MM-DD"),
            dayjs().endOf("month").format("YYYY-MM-DD"),
          ],
          dateKey: "month",
        },
        {
          label: "本年",
          value: [
            dayjs().startOf("year").format("YYYY-MM-DD"),
            dayjs().endOf("year").format("YYYY-MM-DD"),
          ],
          dateKey: "year",
        },
      ],
      // 快捷key
      dateKey: null,
      // 时间值
      timeValue: [],
    };
  },
  mounted() {
    if (this.timeDefault) this.timeValue = this.timeDefault;
    if (this.isDefault) this.handleShortcut(this.isDefault);
  },
  methods: {
    //   日期选择
    handleTime(event) {
      let obj = null;
      this.dateArry.forEach((item) => {
        if (equalsArray(item.value, event)) {
          obj = item;
        }
      });
      if (obj) {
        this.dateKey = obj.dateKey;
      } else {
        this.dateKey = null;
      }
      this.$emit("callBack", this.timeValue);
    },
    // 快捷选择项
    handleShortcut(dateKey) {
      let { value } = this.dateArry.find((item) => item.dateKey === dateKey);
      this.timeValue = value;
      this.dateKey = dateKey;
    },
  },
};
</script>

<style scoped lang="scss">
.date-box {
  span {
    color: rgba(0, 0, 0, 0.85);
    font-size: 14px;
    margin: 0 10px;
  }
  .active {
    color: #1890ff;
  }
}
</style>
