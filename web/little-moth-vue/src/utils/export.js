import request from "@/api/request";
import { Message } from "element-ui";
/**
 * 导出功能
 * @options 筛选项
 * @Interface  接口url
 * @fileName 导出文件名
 */

export async function exportDownload (options, Interface, fileName) {
  if (!options || !Interface || !fileName) {
    Message.error("参数不合法");
    return;
  }
  const res = await request({
    url: process.env.VUE_APP_BASE_API + Interface,
    method: "post",
    responseType: "blob",
    data: options,
  });
  if (res) {
    const blob = new Blob([res.data], { type: "application/x-excel" });
    const a = document.createElement("a");
    document.body.appendChild(a);
    a.style.display = "none";
    const url = window.URL.createObjectURL(blob);
    a.href = url;
    a.download = fileName + ".xls" || "导出文件.xls";
    a.click();
    document.body.removeChild(a);
    window.URL.revokeObjectURL(url);
  } else {
    Message.error(res.msg);
  }
}
