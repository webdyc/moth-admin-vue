<template>
  <div class="waveform-box">
    <!-- 播放器标题 -->
    <div class="waveform-title">
      <div>{{ playtime }}</div>
      <div>{{ playurl.name }}</div>
      <div>{{ playurl.time }}</div>
    </div>
    <!-- 播放器波纹 -->
    <div id="waveform" class="waveform" />
    <!-- 播放器操作 -->
    <div class="waveform-operation">
      <div>
        <el-button icon="el-icon-back" />
        <el-button icon="el-icon-right" />
      </div>
      <div>
        <el-button
          icon="el-icon-d-arrow-left"
          :disabled="rate == '0.5'"
          @click="setPlaybackRate('0.5')"
        />
        <el-button v-show="!isPlay" icon="el-icon-video-play" @click="play" />
        <el-button v-show="isPlay" icon="el-icon-video-pause" @click="pause" />
        <el-button
          icon="el-icon-d-arrow-right"
          :disabled="rate == '2'"
          @click="setPlaybackRate('2')"
        />
      </div>
      <div>
        <el-button type="text" @click="setMute(true)">静音</el-button>
        <el-button type="text" @click="setMute(false)">取消静音</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import WaveSurfer from "wavesurfer.js";
import { formatSeconds } from "@/utils/audioFormat";
export default {
  props: {
    color: {
      type: String,
      required: false,
      default: "red",
    },
    bgColor: {
      type: String,
      required: false,
      default: "keyblue",
    },
    progressColor: {
      type: String,
      required: false,
      default: "#c0ca2b",
    },
    audiolist: {
      type: Array,
      default: () => [],
    },
  },

  data() {
    return {
      wavesurfer: null,
      // 播放音频信息
      playurl: {},
      // 播放进度
      playtime: "00:00",
      // 当前是否播放
      isPlay: false,
      // 播放进度
      rate: "1",
    };
  },

  mounted() {
    this.initWavesurfer();
  },
  methods: {
    // 波纹初始化
    initWavesurfer() {
      this.wavesurfer = WaveSurfer.create({
        container: "#waveform",
        scrollParent: true,
        // 指示播放头位置的光标填充颜色。
        cursorColor: "#333",
        // 以像素为单位。
        cursorWidth: 1,
        // 光标后面的波形填充色
        waveColor: this.color,
        // 光标后面的波形部分的填充色
        progressColor: this.progressColor,
        backgroundColor: this.bgColor,
        backend: "MediaElementWebAudio",
        mediaControls: false,
        // 播放速度,数值越小越慢
        audioRate: "1",
        // 如果设置，波纹的样式将变成类似柱状图的形状
        barWidth: 10,
        hideScrollbar: true,
        barRadius: 5,
        maxCanvasWidth: 500,
        // 使用的插件
        // plugins: [
        //   Timeline.create({  //时间线需要 Timeline插件
        //     container: "#wave-timeline",
        //   }),
        // ],
      });
      // 加载音频、解码并绘制波形时。
      this.wavesurfer.on("ready", () => {
        this.play();
      });
      // 在音频播放时持续触发
      this.wavesurfer.on("audioprocess", () => {
        // 如果当前正在播放则返回，否则返回false。
        this.isPlaying();
        // 以秒为单位返回当前进度
        this.getCurrentTime();
      });
    },
    //   加载音频
    load(index) {
      this.playurl = this.audiolist[index];
      this.wavesurfer.load(this.playurl.url);
    },
    // 如果当前正在播放则返回，否则返回false。
    isPlaying() {
      this.isPlay = this.wavesurfer.isPlaying();
    },
    // 停止播放。
    pause() {
      this.wavesurfer.pause();
    },
    // 从当前位置开始播放。可选start且end以秒为单位测量可用于设置要播放的音频范围。
    play() {
      this.wavesurfer.play();
    },
    // 以秒为单位返回当前进度
    getCurrentTime() {
      // 秒转时分
      this.playtime = formatSeconds(this.wavesurfer.getCurrentTime());
    },
    // 静音当前声音。可以是true静音或false取消静音的布尔值
    setMute(mute) {
      this.wavesurfer.setMute(mute);
    },
    // 设置播放速度（0.5半速、1正常速度、2双速等）
    setPlaybackRate(rate) {
      if (this.rate === rate) rate = "1";
      this.wavesurfer.setPlaybackRate(rate);
    },
  },
};
</script>
<style lang="scss" scoped>
.waveform-box {
  .waveform-title {
    max-height: 32px;
    padding: 6px;
    font-size: 16px;
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: space-between;
    border: 1px solid #ccc;
    border-bottom: none;
  }
  .waveform {
    width: 100%;
    border: 1px solid #ccc;
  }
  .waveform-operation {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 64px;
    max-height: 64px;
    padding: 0 16px;
    background-color: rgb(63, 81, 181);
    color: rgba(255, 255, 255, 0.87);
  }
}
</style>
