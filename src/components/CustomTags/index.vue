<!-- 自定义标签列表参数说明：
/**
 * 2022/1/12, By: webdyc
  * dataList: Array, // tags数组。 参数：["标签一", "标签二", "标签三", "标签四", "标签五", "标签六"],
 * callBack: Boolean, // 父组件中注册的回调函数名，返回以数组的日期内容
 */
 -->
<template>
  <div class="tags-box">
    <el-tag
      v-for="tag in tagsList"
      :key="tag"
      size="small"
      closable
      :disable-transitions="false"
      @close="handleClose(tag)"
    >
      {{ tag }}
    </el-tag>
    <el-input
      v-if="inputVisible"
      ref="saveTagInput"
      v-model="inputValue"
      class="input-new-tag"
      size="small"
      @keyup.enter.native="handleInputConfirm"
      @blur="handleInputConfirm"
    />
    <el-button v-else class="button-new-tag" size="small" @click="showInput">
      + 新增
    </el-button>
  </div>
</template>

<script>
export default {
  props: {
    dataList: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      filterText: "",
      tagsList: [],
      inputVisible: false,
      inputValue: "",
    };
  },
  watch: {},
  mounted() {
    this.tagsList = this.dataList;
  },
  beforeDestroy() {},
  methods: {
    handleClose(tag) {
      this.tagsList.splice(this.tagsList.indexOf(tag), 1);
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      const inputValue = this.inputValue;
      if (inputValue) {
        this.tagsList.push(inputValue);
        this.$emit("callBack", this.tagsList);
      }
      this.inputVisible = false;
      this.inputValue = "";
    },
  },
};
</script>
<style lang="scss" scoped>
.tags-box {
  display: flex;
  flex-wrap: wrap;
  .el-tag {
    width: 22%;
    margin-right: 3%;
    margin-bottom: 15px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 26px;
  }
  .button-new-tag {
    margin-bottom: 15px;
    height: 26px;
    line-height: 24px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 22%;
    vertical-align: bottom;
  }
}
</style>
