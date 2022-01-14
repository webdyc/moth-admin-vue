import "@antv/x6-vue-shape";
import { Addon, Shape } from "@antv/x6";
// 拖拽生成四边形

// export const createType = (obj) => {
//   const { e, type, graph } = obj;
//   if (type.id === 1) {
//     new Shape.Rect({
//       id: "node1",
//       x: 40,
//       y: 40,
//       width: 100,
//       height: 40,
//       label: "rect",
//       zIndex: 2,
//     });
//   }
// };
export const startDragToGraph = (graph, type, e) => {
  const node = graph.createNode({
    tools: [
      { name: "boundary" },
      {
        name: "button-remove", // 工具名称
        args: { x: 10, y: 10 }, // 工具对应的参数
      },
    ],
    width: type.width || 140, // 节点的宽度
    height: type.height || 40, // 节点的高度
    shape: type.type,
    attrs: {
      label: {
        text: type.text,
        fill: type.color,
        fontSize: 14,
        textWrap: {
          width: -10,
          height: -10,
          ellipsis: true,
        },
      },
      body: {
        stroke: type.color,
        strokeWidth: 1,
        fill: "#ffffff",
      },
    },
    ports: ports,
  });
  const dnd = new Addon.Dnd({ target: graph });
  dnd.start(node, e);
};
const ports = {
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
};
