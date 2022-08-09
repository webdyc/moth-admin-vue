import request from "@/api/request";

// 获取列表

export function role_list(options = {}) {
  options = Object.assign(
    {
      // 第几页
      pageNum: 1,
      // 每页多少条
      pageSize: 10,
      // 角色名称
      name: "",
      // 组织名称
      officeName: "",
      //   下拉树value  里面的值  例如（0,13,）
      officeValue: "",
    },
    options
  );

  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/role/list",
    method: "get",
    params: options,
  });
}
// 修改角色状态

export function role_stateChange(options = {}) {
  options = Object.assign(
    {
      id: "",
      // 是否可用 是否启用状态（0正常 1停用）
      useable: "",
    },
    options
  );
  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/role/edit",
    method: "post",
    data: options,
  });
}
// 根据角色id删除

export function role_delete(options = {}) {
  options = Object.assign(
    {
      // id
      id: "",
    },
    options
  );
  return request({
    url:
      process.env.VUE_APP_BASE_API + `/auth/role/delete/${options.id}`,
    method: "get",
  });
}
// 获取角色详情

export function role_detail(options = {}) {
  options = Object.assign(
    {
      id: "",
    },
    options
  );
  return request({
    url: process.env.VUE_APP_BASE_API + `/auth/role/${options.id}`,
    method: "get",
  });
}
// 编辑角色
export function role_edit(options = {}) {
  options = Object.assign(
    {
      // 组织ID
      officeId: "",
      id: "",
      // 角色名称
      name: "",
      // 备注
      remarks: "",
      // 菜单角色
      menuIdList: [],
    },
    options
  );

  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/role/edit",
    method: "post",
    data: options,
  });
}

// 新增角色

export function role_add(options = {}) {
  options = Object.assign(
    {
      // /组织ID
      officeId: "",
      // 角色名称
      name: "",
      // /备注
      remarks: "",
      // 菜单角色
      menuIdList: [],
    },
    options
  );

  return request({
    url: process.env.VUE_APP_BASE_API + "/auth/role/add",
    method: "post",
    data: options,
  });
}

export function treeselectrole(options) {
  return request({
    url: process.env.VUE_APP_BASE_API + "/menu/treeselectrole",
    method: "get",
    data: options,
  });
}
