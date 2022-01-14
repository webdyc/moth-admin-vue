<template>
  <div class="app-content">
    <div class="x6">
      <DragNode @createNode="createNode" @getList="getList" />
      <div ref="container" />
      <EditNode :istype="type" :selectcell="selectCell" />
    </div>
  </div>
</template>

<script>
import { Graph, Addon, FunctionExt, Shape } from "@antv/x6";
import DragNode from "./components/DragNode.vue";
import EditNode from "./components/EditNode.vue";

const { Dnd } = Addon;
export default {
  components: { DragNode, EditNode },
  data() {
    return {
      graph: null,
      dnd: null,
      Node: null,
      data: [],
      val: "",
      type: null,
      selectCell: {},
      config: {
        width: 800,
        height: 600,
        background: {
          color: "#fffbe6", // 设置画布背景颜色
        },
        grid: {
          size: 10, // 网格大小 10px
          visible: true, // 渲染网格背景
        },
        attrs: {
          line: {
            stroke: "#faad14",
            targetMarker: "classic",
          },
        },
        selecting: true, // 可选
        resizing: {
          // 调整节点宽高
          enabled: true,
          orthogonal: true,
          preserveAspectRatio: true,
        },
        snapline: true,
        interacting: {
          edgeLabelMovable: true,
        },
        connecting: {
          createEdge() {
            return new Shape.Edge({
              anchor: "center",
              connectionPoint: "anchor",
              allowBlank: false,
              snap: true,
              router: "orth",
              attrs: {
                line: {
                  strokeWidth: 1,
                  stroke: "#ccc",
                },
              },
              connector: {
                name: "smooth",
                args: {},
              },
            });
          },
        },
      },
      ports: {
        groups: {
          // 输入链接桩群组定义
          top: {
            position: "top",
            attrs: {
              circle: {
                r: 4,
                magnet: true,
                stroke: "#2D8CF0",
                strokeWidth: 2,
                fill: "#fff",
              },
            },
          },
          // 输出链接桩群组定义
          bottom: {
            position: "bottom",
            attrs: {
              circle: {
                r: 4,
                magnet: true,
                stroke: "#2D8CF0",
                strokeWidth: 2,
                fill: "#fff",
              },
            },
          },
          left: {
            position: "left",
            attrs: {
              circle: {
                r: 4,
                magnet: true,
                stroke: "#2D8CF0",
                strokeWidth: 2,
                fill: "#fff",
              },
            },
          },
          right: {
            position: "right",
            attrs: {
              circle: {
                r: 4,
                magnet: true,
                stroke: "#2D8CF0",
                strokeWidth: 2,
                fill: "#fff",
              },
            },
          },
        },
        items: [
          {
            id: "port1",
            group: "top",
          },
          {
            id: "port2",
            group: "bottom",
          },
          {
            id: "port3",
            group: "left",
          },
          {
            id: "port4",
            group: "right",
          },
        ],
      },
    };
  },
  mounted() {
    this.initX6();
  },
  methods: {
    initX6() {
      this.graph = new Graph({
        container: this.$refs.container,
        ...this.config,
      });
      this.graph.on(
        "node:mouseenter",
        FunctionExt.debounce((e) => {
          // const { data } = e.node.store;
          const container = this.$refs.container;
          const ports = container.querySelectorAll(".x6-port-body");
          this.showPorts(ports, true);
        }),
        500
      );
      this.graph.on("cell:click", ({ cell }) => {
        this.type = cell.isNode() ? "node" : "edge";
      });
      this.graph.on("node:mouseleave", () => {
        const container = this.$refs.container;
        const ports = container.querySelectorAll(".x6-port-body");
        this.showPorts(ports, false);
      });
      this.graph.on("node:click", ({ e, x, y, node, view }) => {
        this.resetColor();
        node.attr("body/fillOpacity", "0.7");
      });
      this.graph.on("node:dblclick", (node) => {
        this.val = node.cell.store.data.attrs.label.text;
        this.flag = true;
      });
      this.graph.on("selection:changed", (args) => {
        args.added.forEach((cell) => {
          this.selectCell = cell;
          if (cell.isEdge()) {
            cell.isEdge() && cell.attr("line/strokeDasharray", 5); // 虚线蚂蚁线
            cell.addTools([
              {
                name: "vertices",
                args: {
                  padding: 4,
                  attrs: {
                    strokeWidth: 0.1,
                    stroke: "#2d8cf0",
                    fill: "#ffffff",
                  },
                },
              },
            ]);
          }
        });
        args.removed.forEach((cell) => {
          cell.isEdge() && cell.attr("line/strokeDasharray", 0); // 正常线
          cell.removeTools();
        });
      });
      this.graph.on("cell:contextmenu", (args) => {
        if (args.node) {
          args.node.addTools({
            name: "button-remove", // 工具名称
            args: { x: 10, y: 10 }, // 工具对应的参数
          });
        } else {
          args.edge.addTools({
            name: "button-remove", // 工具名称
            args: { x: args.e.x, y: center }, // 工具对应的参数
          });
        }
      });
      this.graph.on("blank:click", (args) => {
        console.log(args);
        this.type = false;
      });
      this.dnd = new Dnd({
        target: this.graph,
        scaled: false,
        animation: true,
      });
    },
    showPorts(ports, show) {
      for (let i = 0, len = ports.length; i < len; i = i + 1) {
        ports[i].style.visibility = show ? "visible" : "hidden";
      }
    },
    createNode(e, config) {
      const type = config.type === "ellipse" ? "rect" : config.type;
      this.Node = this.graph.createNode({
        id: config.type,
        width: config.W,
        height: config.H,
        points: "0,10 10,0 20,10 10,20",
        shape: type,
        ports: this.ports,
        attrs: {
          label: {
            text: config.text,
          },
          body: {
            rx: config.rx, // 圆角矩形
            ry: config.ry,
            stroke: config.color,
            fill: config.color,
            storkeWidth: 4,
            fillOpacity: 0.3,
          },
        },
      });

      this.dnd.start(this.Node, e);
    },
    resetColor(node) {
      const nodes = this.graph.getNodes();

      nodes.forEach((node) => {
        node.attr("body/fillOpacity", "0.3");
      });
    },
    getList(data) {
      this.data = data;
    },
    handelConfirm(node) {
      // node.cell.store.data.attrs.label.text = this.val;
    },
  },
};
</script>

<style scoped lang='scss'>
.x6 {
  display: flex;
}
</style>
