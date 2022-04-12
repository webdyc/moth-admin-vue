<template>
  <div class="app-content">
    <aside>
      音浪通过自定义属性可进行一些简单的基础配置,详见WaveSurfer组件(播放只支持mp3格式，可通过lamejs进行转换),录音组件可通过自定义type属性传入
      mp3||wav 返回两种格式的数据 默认mp3
    </aside>
    <div class="mb-2">
      <Recorder format="wav" @stopRecording="recordingEnd" />
    </div>
    <!-- 波形绘制区域 -->
    <div class="mb-1 recpower">
      <WaveSurfer ref="WaveSurfer" :audiolist="audiolist" />
    </div>
    <!-- 播放列表 -->
    <div class="audio-list">
      <div class="audio-title">播放列表</div>
      <template v-if="audiolist.length">
        <div
          v-for="(item, index) in audiolist"
          :key="index"
          class="audio-list-item"
          @click="onPlay(index)"
        >
          <!-- 录音名称 -->
          <div>{{ item.name }}</div>
          <!-- 录音时长 -->
          <div>{{ item.time }}</div>
        </div>
      </template>
      <div v-else class="audio-list-none">暂无数据,请先录音</div>
    </div>
  </div>
</template>

<script>
import Recorder from "@/components/Recorder";
import WaveSurfer from "@/components/Voices";

export default {
  components: {
    Recorder,
    WaveSurfer,
  },
  data() {
    return {
      audiolist: [], // 录音列表
    };
  },

  methods: {
    // 录音停止
    recordingEnd(val) {
      if (val) {
        this.audiolist.push(val);
      } else {
        console.log("未知错误！");
      }
    },
    // audio 播放
    onPlay(index) {
      this.$refs.WaveSurfer.load(index);
    },
  },
};
</script>

<style lang="scss" scoped>
.audio-list {
  color: #333333;
  .audio-title {
    max-height: 32px;
    padding: 6px 15px;
    font-size: 16px;
    background-color: rgb(63, 81, 181);
    color: rgba(255, 255, 255, 0.87);
  }
  .audio-list-item {
    display: flex;
    justify-content: space-between;
    padding: 0 15px;
    line-height: 36px;
    border: 1px solid #ccc;
    border-top: none;
    cursor: pointer;
  }
  .audio-list-none {
    text-align: center;
    border: 1px solid #ccc;
    border-top: none;
    padding: 20px 0;
  }
}
</style>
