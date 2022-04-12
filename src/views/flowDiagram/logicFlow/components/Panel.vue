<template>
  <div class="panel">
    <div
      v-for="item in shapeList"
      :key="item.type"
      @mousedown="mouseDown(item)"
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
          style: {
            circle: {
              r: 15,
              fill: "#FFC372",
              stroke: "#FFC372",
              strokeWidth: 2,
              fillOpacity: 0.3,
            },
          },
        },
        {
          type: "rect",
          text: "Normal",
          style: {
            rect: {
              fill: "#A5D3FD",
              stroke: "#A5D3FD",
              strokeWidth: 2,
              fillOpacity: 0.3,
            },
          },
        },
        {
          type: "polygon",
          text: "Decision",
          style: {
            polygon: {
              fill: "#8AE7E0",
              stroke: "#8AE7E0",
              strokeWidth: 2,
              fillOpacity: 0.3,
            },
          },
        },
        {
          type: "ellipse",
          text: "Model",
          style: {
            ellipse: {
              fill: "#E4D0F6",
              stroke: "#E4D0F6",
              strokeWidth: 2,
              fillOpacity: 0.3,
            },
          },
        },
      ],
    };
  },
  mounted() {},
  methods: {
    mouseDown(val) {
      this.$emit("mouseDownHandle", val);
    },
  },
};
</script>

<style>
</style>
