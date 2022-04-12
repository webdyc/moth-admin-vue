<template>
  <div class="ratio-box">
    <el-card>
      <div class="header">
        <div class="header-content">
          <div
            v-for="(item, index) in tableData"
            :key="item.id"
            :class="[activeId === item.id ? 'active' : '', 'header-list']"
            @click="handleClick(index, item)"
          >
            <div class="title">{{ item.title }}</div>
            <div class="sub-title">{{ item.subtitle }}</div>
            <div class="ratio">{{ item.ratio }}</div>
          </div>
        </div>
        <div id="headerBar" class="header-bar"></div>
      </div>
      <LineMarker id="ratio-chart" width="100%"></LineMarker>
    </el-card>
  </div>
</template>

<script>
import LineMarker from "@/components/Charts/LineMarker";
export default {
  name: "RatioCard",
  data() {
    return {
      activeId: "",
      //   转化率数据
      tableData: [
        {
          id: 1,
          title: "Stores 1",
          subtitle: "转化率",
          ratio: "90%",
        },
        {
          id: 2,
          title: "Stores 2",
          subtitle: "转化率",
          ratio: "90%",
        },
        {
          id: 3,
          title: "Stores 3",
          subtitle: "转化率",
          ratio: "90%",
        },
        {
          id: 4,
          title: "Stores 4",
          subtitle: "转化率",
          ratio: "90%",
        },
        {
          id: 5,
          title: "Stores 5",
          subtitle: "转化率",
          ratio: "90%",
        },
        {
          id: 6,
          title: "Stores 6",
          subtitle: "转化率",
          ratio: "90%",
        },
        {
          id: 7,
          title: "Stores 7",
          subtitle: "转化率",
          ratio: "90%",
        },
        {
          id: 8,
          title: "Stores 8",
          subtitle: "转化率",
          ratio: "90%",
        },
        {
          id: 9,
          title: "Stores 9",
          subtitle: "转化率",
          ratio: "90%",
        },
      ],
    };
  },
  components: { LineMarker },
  computed: {},
  watch: {},
  mounted() {},
  methods: {
    // 转化率切换
    handleClick(index, row) {
      console.log(row);
      this.activeId = row.id;
      let dom = document.getElementById("headerBar");
      dom.style.cssText = `
            display: block;
            transform: translate3d(${146.7 * index}px, 0px, 0px);
            width: 114.7px;
          `;
    },
  },
};
</script>

<style lang="scss" scoped>
.ratio-box {
  margin-top: 10px;
  ::v-deep .el-card__header {
    position: relative;
    border-bottom: none;
  }
  .header {
    box-sizing: border-box;
    border-bottom: 1px solid #f0f0f0;
    margin: 0 0 16px 0;
    position: relative;
    .header-content {
      display: flex;
      align-items: center;
      .header-list {
        margin: 0 32px 0 0;
        padding: 12px 16px;
        cursor: pointer;
        .title {
          margin-bottom: 16px;
          color: rgba(0, 0, 0, 0.85);
          font-size: 16px;
          transition: all 0.3s;
        }
        .sub-title {
          height: 22px;
          overflow: hidden;
          color: rgba(0, 0, 0, 0.45);
          font-size: 14px;
          line-height: 22px;
          white-space: nowrap;
          text-overflow: ellipsis;
          word-break: break-all;
        }
        .ratio {
          margin-top: 4px;
          display: inline-block;
          height: 32px;
          margin-right: 32px;
          color: rgba(0, 0, 0, 0.85);
          font-size: 24px;
          line-height: 32px;
        }
      }
      .active {
        color: #1890ff;
        text-shadow: 0 0 0.25px currentColor;
      }
    }

    .header-bar {
      position: absolute;
      bottom: 1px;
      left: 0;
      z-index: 1;
      box-sizing: border-box;
      height: 2px;
      background-color: #1890ff;
      transform: translate3d(0, 0px, 0px);
      width: 114.7px;
    }
  }
}
</style>
