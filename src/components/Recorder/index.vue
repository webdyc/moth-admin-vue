<template>
  <div>
    <el-button type="primary" :size="styleSize" @click="start">
      {{ text }}
    </el-button>
    <el-button v-show="flag" type="primary" :size="styleSize" @click="stop">
      停止
    </el-button>
  </div>
</template>

<script>
import defaultSettings from "@/settings";
import Recorder from "js-audio-recorder";
import {
  reader,
  createURL,
  convertToMp3,
  formatSeconds,
} from "@/utils/audioFormat";
let recorder = new Recorder({
  sampleBits: 16, // 采样位数，支持 8 或 16，默认是16
  sampleRate: 48000, // 采样率，支持 11025、16000、22050、24000、44100、48000，根据浏览器默认值，我的chrome是48000
  numChannels: 1, // 声道，支持 1 或 2， 默认是1
  // compiling: false,(0.x版本中生效,1.x增加中)  // 是否边录边转换，默认是false
});
export default {
  props: {
    format: {
      type: String,
      required: false,
      default: "mp3",
    },
  },
  data() {
    return {
      styleSize: defaultSettings.styleSize,
      // 是否获取过权限
      isGetPermissions: false,
      text: "录制", // 录制按钮的初始状态
      flag: false, // 控制停止按钮的显示隐藏
    };
  },
  created() {
    if (
      this.format.toLocaleLowerCase() !== "mp3" &&
      this.format.toLocaleLowerCase() !== "wav"
    ) {
      this.$message("暂时不支持该类型");
    }
  },
  methods: {
    // 获取麦克风权限。
    getPermissions() {
      return Recorder.getPermission().then(
        () => {
          console.log("给权限了");
          this.isGetPermissions = true;
        },
        () => {
          this.isGetPermissions = false;
          this.$message.error("未获得权限!");
        }
      );
    },
    // 开始录制
    async start() {
      !this.isGetPermissions && this.getPermissions();
      if (this.text === "录制") {
        recorder.start().then(
          (res) => {
            this.flag = true;
            this.text = "暂停";
            // 此方法可以获取到录音的时长、大小、音量百分比、总数据 但每秒获取的数据会非常多。慎用！
            // recorder.onprogress = function(params) {
            //   console.log('录音时长(秒)', params.duration);
            //   console.log('录音大小(字节)', params.fileSize);
            //   console.log('录音音量百分比(%)', params.vol);
            //   console.log('当前录音的总数据([DataView, DataView...])', params.data);
            // }
          },
          () => {
            this.$message.error("未知错误!");
          }
        );
      } else if (this.text === "暂停") {
        this.pause();
        this.text = "继续";
      } else if (this.text === "继续") {
        this.resume();
        this.text = "暂停";
      }
    },
    // 录音暂停
    pause() {
      recorder.pause();
    },
    // 继续录音
    resume() {
      recorder.resume();
    },
    // 停止录制
    stop() {
      recorder.stop();
      const data = {
        url: null,
        time: this.getDuration(),
        name: new Date().getTime() + `.${this.format}`,
      };

      if (this.format === "mp3" || "MP3") {
        data.url = this.getWAV();
      } else if (this.format === "wav" || "WAV") {
        data.url = this.getWAVBlob();
      }
      this.$emit("stopRecording", data);
      this.text = "录制";
      this.flag = false;
    },
    // 录音播放
    play() {
      recorder.play();
    },
    // 暂停录音播放
    pausePlay() {
      recorder.pausePlay();
    },
    // 恢复录音播放
    resumePlay() {
      recorder.resumePlay();
    },
    // 停止播放
    stopPlay() {
      recorder.stopPlay();
    },
    // 销毁实例
    destroy() {
      recorder.destroy().then(() => {
        recorder = null;
      });
    },
    // 获取音频已经播的时长
    getPlayTime() {
      recorder.getPlayTime();
    },
    // 获取录音的总时长
    getDuration() {
      // 秒转时分
      return formatSeconds(recorder.duration);
    },
    // 录音文件大小（单位：字节）
    getFileSize() {
      return recorder.fileSize;
    },
    // 获取 WAV 数据，在录音结束后使用
    getWAVBlob() {
      // 获取 WAV 数据
      const wav = recorder.getWAVBlob();
      return createURL(wav);
    },
    getWAV() {
      const mp3Blob = convertToMp3(recorder.getWAV(), recorder);
      // 处理wav数据
      reader(mp3Blob);
      return createURL(mp3Blob);
    },
  },
};
</script>
