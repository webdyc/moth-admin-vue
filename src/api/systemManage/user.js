import request from "@/api/request";

// 查询用户列表

export function user_searchUserList(options = {}) {
  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/user/list",
    method: "get",
    params: options,
  });
}

// 修改用户状态

export function user_stateChange(options = {}) {
  options = Object.assign(
    {
      // 修改的id
      id: "",
      //   是否启用组织状态（0正常 1停用）
      useable: "",
    },
    options
  );
  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/user/edit",
    method: "post",
    data: options,
  });
}

export function user_detail(options = {}) {
  options = Object.assign(
    {
      id: "",
    },
    options
  );
  return request({
    url: process.env.VUE_APP_BASE_API + `/auth/user/${options.id}`,
    method: "get",
  });
}

export function user_add(options = {}) {
  // options = Object.assign(
  //   {
  //     // 用户名
  //     userName: "",
  //     // 组织id
  //     officeId: "",
  //     // 角色编号
  //     roleId: "",
  //     // 用户账号
  //     no: "",
  //     // 密码
  //     password: "",
  //     // 是否可用 是否启用组织状态（0正常 1停用）
  //     useable: "",
  //     // 是否脱敏  默认0  是否脱敏 0否  1是
  //     isDesensitize: "",
  //   },
  //   options
  // );
  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/user/add",
    method: "post",
    data: options,
  });
}
// 根据组织id查询角色
export function user_roleMap(options = {}) {
  options = Object.assign(
    {
      // 组织id
      id: "",
    },
    options
  );
  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/role/roleMap",
    method: "get",
    params: options,
  });
}
// 根据用户id删除
export function user_delete(options = {}) {
  // options ==id
  return request({
    url: process.env.VUE_APP_BASE_API + `/auth/user/delete/${options}`,
    method: "get",
  });
}

export function user_edit(options = {}) {
  options = Object.assign(
    {
      // 用户名
      userName: "",
      // 用户主键
      id: "",
      // 组织ID
      officeId: null,
      // 角色编号
      roleId: null,
      // 用户账号
      no: "",
      // 密码
      password: "",
      // 是否可用 是否启用组织状态（0正常 1停用）
      useable: "",
      // 是否脱敏  默认0  是否脱敏 0否  1是
      isDesensitize: "",
    },
    options
  );
  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/user/edit",
    method: "post",
    data: options,
  });
}

export function user_transfer(options = {}) {
  options = Object.assign(
    {
      // 用户住建
      id: "",
      // 组织id
      officeId: "",
      // 角色编号
      roleId: "",
      // 是否可用 是否启用组织状态（0正常 1停用）
      useable: "",
    },
    options
  );
  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/user/transferUser",
    method: "post",
    data: options,
  });
}

// 个人中心编辑
export function editCurrentUser(options = {}) {
  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/user/editCurrentUser",
    method: "post",
    data: options,
  });
}
