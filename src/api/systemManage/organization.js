import request from "@/api/request";
// 右边tabel数据
export function organization_tabel (options = {}) {
    options = Object.assign(
        {
            parentIds: "",
            // 客户姓名
            name: "",
            // 每页条数
            pageSize: 10,
            // 当前页码
            pageNum: 1,
        },
        options
    );

    return request({
        url: process.env.VUE_APP_BASE_API + "/office/list",
        method: "get",
        params: options,
    });
}

// 左边树数据

export function organization_treeselect (options = {}) {
    return request({
        url: process.env.VUE_APP_BASE_API + "/office/treeselect",
        method: "get",
        params: options,
    });
}
// 修改组织状态
export function organization_stateChange (options = {}) {
    return request({
        url: process.env.VUE_APP_BASE_API + "/office/edit",
        method: "post",
        data: options,
    });
}
// 新增组织
export function organization_add (options = {}) {
    return request({
        url: process.env.VUE_APP_BASE_API + "/office/add",
        method: "post",
        data: options,
    });
}
// 删除组织

export function organization_remove (options = {}) {
    return request({
        url:
            process.env.VUE_APP_BASE_API +
            `/office/delete/${options.id}`,
        method: "get",
    });
}
// 编辑组织

export function organization_edit (options = {}) {
    return request({
        url: process.env.VUE_APP_BASE_API + "/office/edit",
        method: "post",
        data: options,
    });
}
// 查看组织

export function organization_detail (options = {}) {
    return request({
        url: process.env.VUE_APP_BASE_API + `/office/id/${options}`,
    });
}

// // 点击组织树列表根据ID查询所有组织

// export function organization_getTerr(options = {}) {
//   return request({
//     url: process.env.VUE_APP_BASE_API + "/unioncall/office/treeselect",
//     method: "get",
//     params: options,
//   });
// }

// 附加信息

export function addInfo (options = {}) {
    return request({
        url: process.env.VUE_APP_BASE_API + "/auth/officePeculiar/edit",
        method: "post",
        data: options,
    });
}

// 组织附加信息详情
export function officePeculiar (options = {}) {
    return request({
        url: process.env.VUE_APP_BASE_API + "/auth/officePeculiar/detail",
        method: "post",
        data: options,
    });
}
// //app创建应用接口
export function semantic (options) {
    return request({
        url: process.env.VUE_APP_BASE_API + "/semantic/system/applications",
        method: "post",
        data: options,
    });
}
