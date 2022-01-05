import { debounce } from "@/utils";

export default {
  data() {
    return {
      $_sidebarElm: null,
      $_resizeHandler: null,
    };
  },
  mounted() {
    this.initListener();
  },
  activated() {
    if (!this.$_resizeHandler) {
      // 避免重复初始化
      this.initListener();
    }

    // 激活“保持活动状态”图表时，自动调整大小
    this.resize();
  },
  beforeDestroy() {
    this.destroyListener();
  },
  deactivated() {
    this.destroyListener();
  },
  methods: {
    // use $_ for mixins properties
    // https://vuejs.org/v2/style-guide/index.html#Private-property-names-essential
    $_sidebarResizeHandler(e) {
      if (e.propertyName === "width") {
        this.$_resizeHandler();
      }
    },
    initListener() {
      this.$_resizeHandler = debounce(() => {
        this.resize();
      }, 100);
      window.addEventListener("resize", this.$_resizeHandler);

      this.$_sidebarElm =
        document.getElementsByClassName("sidebar-container")[0];
      this.$_sidebarElm &&
        this.$_sidebarElm.addEventListener(
          "transitionend",
          this.$_sidebarResizeHandler
        );
    },
    destroyListener() {
      window.removeEventListener("resize", this.$_resizeHandler);
      this.$_resizeHandler = null;

      this.$_sidebarElm &&
        this.$_sidebarElm.removeEventListener(
          "transitionend",
          this.$_sidebarResizeHandler
        );
    },
    resize() {
      const { chart } = this;
      chart && chart.resize();
    },
  },
};
