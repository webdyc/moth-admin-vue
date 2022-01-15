<template>
  <div class="dragitem">
    <div
      v-for="item in shapeList"
      :key="item.type"
      @mousedown="mousedown($event, item)"
    >
      <svg-icon
        :icon-class="item.type"
        style="font-size: 80px; user-select: none"
      />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      shapeList: [
        {
          type: "circle",
          text: "Start",
          W: 100,
          H: 100,
          color: "#FFC372",
          id: 1,
        },
        {
          type: "rect",
          text: "Normal",
          W: 120,
          H: 60,
          color: "#A5D3FD",
        },
        {
          type: "polygon",
          text: "Decision",
          color: "#8AE7E0",
          W: 120,
          H: 80,
          refPoints: "0,10 10,0 20,10 10,20",
        },
        {
          type: "ellipse",
          text: "Model",
          W: 120,
          H: 60,
          color: "#E4D0F6",
          rx: 30,
          ry: 34,
        },
      ],
    };
  },
  mounted() {
    this.$emit("getList", this.shapeList);
  },
  methods: {
    mousedown(e, item) {
      this.$emit("createNode", e, item);
    },
  },
};
</script>

<style>
.dragitem {
  width: 150px;
}
</style>
