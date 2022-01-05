export function reader(mp3Blob) {
  const reader = new FileReader(); // 读取文件内容
  reader.readAsArrayBuffer(mp3Blob); // 用于读取Blob或者File内容 主要工作还是在浏览器内处理大文件
}

// 创建url 可以用于在浏览器上预览本地图片或者视频音频
export function createURL(url) {
  return window.URL.createObjectURL(url);
}

// WAV转mp3格式第一个参数:需要转mp3格式的文件 第二个参数:当前录音实例
export function convertToMp3(wavDataView, recorder) {
  // 获取wav头信息
  const wav = lamejs.WavHeader.readHeader(wavDataView); // 此处其实可以不用去读wav头信息，毕竟有对应的config配置
  const { channels, sampleRate } = wav;

  const mp3enc = new lamejs.Mp3Encoder(channels, sampleRate, 128);
  // 获取左右通道数据
  const result = recorder.getChannelData();

  const buffer = [];

  const leftData =
    result.left &&
    new Int16Array(result.left.buffer, 0, result.left.byteLength / 2);
  const rightData =
    result.right &&
    new Int16Array(result.right.buffer, 0, result.right.byteLength / 2);
  const remaining = leftData.length + (rightData ? rightData.length : 0);

  const maxSamples = 1152;
  for (let i = 0; i < remaining; i += maxSamples) {
    const left = leftData.subarray(i, i + maxSamples);
    let right = null;
    let mp3buf = null;

    if (channels === 2) {
      right = rightData.subarray(i, i + maxSamples);
      mp3buf = mp3enc.encodeBuffer(left, right);
    } else {
      mp3buf = mp3enc.encodeBuffer(left);
    }

    if (mp3buf.length > 0) {
      buffer.push(mp3buf);
    }
  }

  const enc = mp3enc.flush();

  if (enc.length > 0) {
    buffer.push(enc);
  }

  return new Blob(buffer, { type: "audio/mp3" });
}

//  秒转时分
export function formatSeconds(value) {
  const result = parseInt(value);
  const m =
    Math.floor((result / 60) % 60) < 10
      ? "0" + Math.floor((result / 60) % 60)
      : Math.floor((result / 60) % 60);
  const s =
    Math.floor(result % 60) < 10
      ? "0" + Math.floor(result % 60)
      : Math.floor(result % 60);

  let res = "";
  res += `${m}:`;
  res += `${s}`;
  return res;
}
