<template>
  <div class="warp">
    <div v-if="istype == 'node'">
      <p>节点设置</p>
      <div class="node-warp">
        <el-form
          ref="form"
          label-position="right"
          label-width="100px"
          class="form"
        >
          <el-form-item label="节点名称" prop="username">
            <el-input v-model="nodeForm.name" @blur="handelBlur" />
          </el-form-item>
          <el-form-item label="节点颜色" prop="username">
            <el-color-picker
              v-model="nodeForm.color"
              @change="nodeColorChange"
            />
          </el-form-item>
          <!-- <el-form-item prop="username">
            <el-button type="primary">确定</el-button>
          </el-form-item> -->
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["istype", "selectcell"],
  data() {
    return {
      nodeForm: {
        name: "",
        color: "#ccc",
      },
    };
  },
  watch: {
    selectcell: {
      handler(val) {
        if (val.isNode()) {
          const {
            attrs: { body, label },
          } = val.store.data;
          console.log(val);
          this.nodeForm.name = label.text;
          this.nodeForm.color = body.fill;
        }
      },
    },
    immediate: true,
    deep: false,
  },
  methods: {
    nodeColorChange(newVal) {
      this.selectcell.attr("body/fill", newVal);
    },
    handelBlur() {
      this.selectcell.attr("label/text", this.nodeForm.name);
    },
  },
};
</script>

<style>
</style>
