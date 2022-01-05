// 获取当前svg目录所有为.svg结尾的文件
const req = require.context('./svg', false, /\.svg$/)
// 解析获取的.svg文件的文件名称，并返回
const requireAll = (requireContext) => requireContext.keys().map(requireContext)
requireAll(req)
