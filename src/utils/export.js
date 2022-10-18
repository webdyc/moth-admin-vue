// 导出功能js
import Axios from "axios";
import { getToken } from "@/utils/auth";

export function exportDownload (
  Interface,
  data,
  requestType,
  ct = "application/x-www-form-urlencoded"
) {
  let options = "";
  let optionName = requestType == "post" ? "data" : "params";
  let contentType = ct;
  Axios({
    method: requestType,
    url: process.env.VUE_APP_BASE_API + Interface,
    headers: {
      Authorization: getToken(),
      "Content-Type": contentType,
    },
    [optionName]: { ...data },
    responseType: "blob",
  }).then((res) => {
    const blob = new Blob([res.data], {
      type: "application/octet-stream;charset=UTF-8",
    });

    if (res.headers["content-disposition"]) {
      let filename = decodeURI(
        res.headers["content-disposition"].split("=")[1]
      ); //utf-8转码

      if ("download" in document.createElement("a")) {
        //	非ie下载
        const elink = document.createElement("a");
        elink.download = filename;
        elink.style.display = "none";
        elink.href = URL.createObjectURL(blob);
        document.body.appendChild(elink);
        elink.click();
        URL.revokeObjectURL(elink.href); //释放url 对象
        document.body.removeChild(elink);
      } else {
        navigator.msSaveBlob(blob, filename);
      }
    }
  });
}
