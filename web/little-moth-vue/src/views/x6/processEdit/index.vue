<template>
  <div class="app-content">
    <div class="container_warp">
      <div class="list">
        <div
          v-for="item in List"
          :key="item.id"
          :class="item.type"
          :title="item.text"
          :style="{ border: `2px solid ${item.color}` }"
          @mousedown="startDrag(item, $event)"
        >
          <span>
            <!-- {{ item.text }} -->
          </span>
        </div>
      </div>

      <div id="containerChart" ref="containerChart" />
    </div>
  </div>
</template>

<script>
import "@antv/x6-vue-shape";
import { Graph, Shape, FunctionExt } from "@antv/x6";
import { startDragToGraph } from "./utail/dragDrop";
export default {
  data() {
    return {
      List: [
        { id: 1, text: "方形", type: "rect", color: "PaleVioletRed" },
        {
          id: 2,
          text: "圆形",
          type: "circle",
          color: "DarkTurquoise",
          width: 70,
          height: 70,
        },
        {
          id: 3,
          text: "椭圆",
          type: "ellipse",
          color: "OliveDrab",
        },
      ],

      graph: "",
      type: "grid",
      selectCell: "",

      connectEdgeType: {
        // 连线方式
        connector: "normal",
        router: {
          name: "",
        },
      },

      grid: {
        // 网格设置
        size: 20, // 网格大小 10px
        visible: true, // 渲染网格背景
        type: "mesh",
        args: {
          color: "#D0D0D0",
          thickness: 1, // 网格线宽度/网格点大小
          factor: 10,
        },
      },
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.initX6();
    });
  },

  methods: {
    initX6() {
      this.graph = new Graph({
        container: this.$refs.containerChart,
        width: 1000,
        height: 500,
        grid: this.grid,
        panning: {
          enabled: true,
          eventTypes: ["leftMouseDown", "rightMouseDown", "mouseWheel"],
        },
        connecting: {
          // 节点连接
          anchor: "center",
          connectionPoint: "anchor",
          allowBlank: false,
          color: "red",
          snap: true,
          router: "orth",
          line: 1,
          connector: {
            name: "smooth",
            args: {},
          },
        },
        router: {
          // 样式名
          name: "manhattan",
          args: {
            startDirections: ["top", "right", "bottom", "left"], // 支持从哪些方向开始路由。
            endDirections: ["top", "right", "bottom", "left"], // 支持从哪些方向结束路由。
          },
        },
        // createEdge() {
        //   return this.graph.createEdge({
        //     attrs: {
        //       line: {
        //         stroke: "#808080",
        //         strokeWidth: 1,
        //         targetMarker: {
        //           name: "block",
        //           args: {
        //             size: "2",
        //           },
        //         },
        //       },
        //     },
        //   });
        // },
      });
      // 鼠标移入移出节点
      this.graph.on(
        "node:mouseenter",
        FunctionExt.debounce(() => {
          const container = this.$refs.containerChart;
          const ports = container.querySelectorAll(".x6-port-body");
          this.showPorts(ports, true);
        }),
        500
      );
      this.graph.on("node:mouseleave", () => {
        const container = this.$refs.containerChart;
        const ports = container.querySelectorAll(".x6-port-body");
        this.showPorts(ports, false);
      });
      this.graph.on("node:dblclick", ({ e, x, y, node, view }) => {
        console.log(e);
      });
      this.graph.on("node:contextmenu", ({ e, x, y, node, view }) => {
        console.log(e);
      });
      // this.graph.on("selection:changed", (args) => {
      //   console.log(12);
      //   args.added.forEach((cell) => {
      //     this.selectCell = cell;
      //     if (cell.isEdge()) {
      //       cell.isEdge() && cell.attr("line/strokeDasharray", 5); // 虚线蚂蚁线
      //       cell.addTools([
      //         {
      //           name: "vertices",
      //           args: {
      //             padding: 4,
      //             attrs: {
      //               strokeWidth: 0.1,
      //               stroke: "#2d8cf0",
      //               fill: "#ffffff",
      //             },
      //           },
      //         },
      //       ]);
      //     }
      //   });
      //   args.removed.forEach((cell) => {
      //     cell.isEdge() && cell.attr("line/strokeDasharray", 0); // 正常线
      //     cell.removeTools();
      //   });
      // });
    },

    showPorts(ports, show) {
      for (let i = 0, len = ports.length; i < len; i = i + 1) {
        ports[i].style.visibility = show ? "visible" : "hidden";
      }
    },
    // 拖拽生成正方形或者圆形
    startDrag(type, e) {
      console.log(type, e);
      console.log(this.graph);
      startDragToGraph(this.graph, type, e);
    },
  },
};
</script>
<style lang="scss">
.container_warp {
  display: flex;
  #containerChart {
    width: 1000px;
    height: 500px;
    border: 1px solid red;
  }
  .list {
    margin-right: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    div {
      margin-top: 20px;
    }
    .rect {
      width: 120px;
      height: 50px;
      // border: 2px solid PaleVioletRed;
      text-align: center;
      line-height: 50px;
    }
    .circle {
      width: 100px;
      height: 100px;
      // border: 2px solid DarkTurquoise;
      text-align: center;
      line-height: 50px;
      border-radius: 50%;
      line-height: 63px;
    }
    .ellipse {
      width: 170px;
      height: 50px;
      // border: 2px solid OliveDrab;
      text-align: center;
      line-height: 50px;
      border-radius: 50%;
      line-height: 43px;
    }
  }
}
</style>

