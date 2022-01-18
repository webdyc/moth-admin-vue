<template>
  <div class="app-content">
    <!-- 搜索输入框 -->
    <el-select
      @change="PlaceSearch"
      class="searchKey"
      id="searchKey"
      v-model="searchKey"
      filterable
      remote
      reserve-keyword
      placeholder="搜索位置、公交站、地铁站"
      :remote-method="remoteMethod"
      :loading="loading"
    >
      <el-option
        v-for="item in searchList"
        :key="item.id"
        :label="item.name"
        :value="item.name"
      >
        <span style="float: left">{{ item.name }}</span>
        <span style="float: right; color: #8492a6; font-size: 13px">{{
          item.district
        }}</span>
      </el-option>
    </el-select>
    <!-- 地图容器 -->
    <div id="container"></div>
  </div>
</template>

<script>
import AMapLoader from "@amap/amap-jsapi-loader";
export default {
  name: "aMap",
  data() {
    return {
      options: [],
      // 搜索关键字
      searchKey: "",
      // 搜索加载效果
      loading: false,
      // 搜索结果列表
      searchList: [],
      map: null,
      // 地图配置信息
      mapInit: {
        // 用户key
        key: "3382477903b4934a1b7070a2b8d26b89",
        version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: [
          "AMap.ToolBar",
          "AMap.Scale",
          "AMap.MapType",
          "AMap.Geolocation",
          "AMap.AutoComplete",
          "AMap.PlaceSearch",
          "AMap.Driving",
        ], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      },
      userLocation: null,
    };
  },
  mounted() {
    AMapLoader.load(this.mapInit)
      .then((AMap) => {
        this.getUserLocation();
      })
      .catch((e) => {
        console.log(e);
      });
  },
  methods: {
    // 初始化地图
    initMap(location) {
      let { lng, lat } = location ? location : this.userLocation;
      this.map = new AMap.Map("container", {
        //设置地图容器id
        viewMode: "3D", //是否为3D地图模式
        zoom: 16, //初始化地图级别
        center: [lng, lat], //初始化地图中心点位置
      });
      // 初始化插件 - 工具条
      this.map.addControl(new AMap.ToolBar());
      // 初始化插件 - 比例尺
      this.map.addControl(new AMap.Scale());
      // 初始化插件 - 在图面添加类别切换控件
      this.map.addControl(new AMap.MapType());
      this.addMarker();
    },
    // 创建标注
    addMarker() {
      // 创建一个 Marker 实例：
      // let marker = new AMap.Marker({
      //   position: new AMap.LngLat(location[0], location[1]), // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
      //   title: "你的位置",
      // });
      // 创建纯文本标记
      var text = new AMap.Text({
        text: "我的位置",
        anchor: "center", // 设置文本标记锚点
        draggable: true,
        cursor: "pointer",
        angle: 10,
        style: {
          padding: "5px 10px",
          "margin-bottom": "1rem",
          "border-radius": ".25rem",
          "background-color": "white",
          width: "80px",
          "border-width": 0,
          "box-shadow": "0 2px 6px 0 rgba(114, 124, 245, .5)",
          "text-align": "center",
          "font-size": "12px",
          color: "blue",
        },
        position: [this.userLocation.lng, this.userLocation.lat],
      });
      // 将创建的点标记添加到已有的地图实例：
      this.map.add(text);
    },
    // 创建多组标注
    async addMarkers(pois) {
      let locationList = await pois.map((item) => {
        // 创建一个 Marker 实例：
        let marker = new AMap.Marker({
          position: new AMap.LngLat(item.location.lng, item.location.lat), // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
          title: item.name,
        });
        return marker;
      });
      // 将创建的点标记添加到已有的地图实例：
      this.map.add(locationList);
      this.initMap(pois[0].location);
    },
    // 获取用户地理位置信息
    getUserLocation() {
      let _this = this;
      AMap.plugin("AMap.Geolocation", function () {
        var geolocation = new AMap.Geolocation({
          // 是否使用高精度定位，默认：true
          enableHighAccuracy: true,
          // 设置定位超时时间，默认：无穷大
          timeout: 10000,
          // 定位按钮的停靠位置的偏移量，默认：Pixel(10, 20)
          buttonOffset: new AMap.Pixel(10, 20),
          //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
          zoomToAccuracy: true,
          //  定位按钮的排放位置,  RB表示右下
          buttonPosition: "RB",
        });
        geolocation.getCurrentPosition(function (status, result) {
          if (status == "complete") {
            onComplete(result);
          } else {
            onError(result);
          }
        });
        function onComplete(data) {
          // data是具体的定位信息
          _this.userLocation = data.position;
          // 显示地图
          _this.initMap(_this.userLocation);
        }
        function onError(data) {
          // 定位出错
          console.log("定位出错");
        }
      });
    },

    // 输入提示插件
    AutoCompleteSearch(keyword) {
      let _this = this;
      // JSAPI 2.0 输入提示插件名称由 AMap.Autocomplete 变更为 AMap.AutoComplete 啦！
      AMap.plugin("AMap.AutoComplete", function () {
        var autoOptions = {
          //city 限定城市，默认全国
          city: "全国",
        };
        // 实例化AutoComplete
        var autoComplete = new AMap.AutoComplete(autoOptions);
        // 根据关键字进行搜索
        autoComplete.search(keyword, function (status, result) {
          // 搜索成功时，result即是对应的匹配数据
          let { info, tips } = result;
          if (info === "OK") {
            _this.searchList = tips;
          }
        });
      });
    },
    // POI搜索插件
    PlaceSearch(keyword) {
      let _this = this;
      var placeSearch = new AMap.PlaceSearch({
        // city 指定搜索所在城市，支持传入格式有：城市名、citycode和adcode
        city: "010",
      });

      placeSearch.search(keyword, (status, result) => {
        let { pois } = result.poiList;
        console.log(pois);
        _this.addMarkers(pois);
        // this.initMap();
      });
    },
    getDriving() {
      let _this = this;
      let { lng, lat } = this.userLocation;
      AMap.plugin("AMap.Driving", function () {
        var driving = new AMap.Driving({
          // 驾车路线规划策略，AMap.DrivingPolicy.LEAST_TIME是最快捷模式
          policy: AMap.DrivingPolicy.LEAST_TIME,
          // map 指定将路线规划方案绘制到对应的AMap.Map对象上
          map: _this.map,
          // panel 指定将结构化的路线详情数据显示的对应的DOM上，传入值需是DOM的ID
          panel: "container",
        });

        var points = [
          { keyword: "魏公村地铁站", city: "北京" },
          { keyword: "亦庄文化园（地铁站）", city: "北京" },
        ];

        // 搜索完成后，将自动绘制路线到地图上
        driving.search(points);
      });
    },
    async remoteMethod(query) {
      if (query !== "") {
        this.loading = true;
        await this.AutoCompleteSearch(query);
        this.loading = false;
      } else {
        this.searchList = [];
      }
    },
  },
};
</script>

<style scoped>
.searchKey {
  width: 400px;
  position: absolute;
  left: 35px;
  top: 35px;
  z-index: 1;
}

#container {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: calc(100vh - 172px) !important;
}

::v-deep .amap-logo,
::v-deep .amap-copyright {
  display: none !important;
}
</style>
