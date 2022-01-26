const getters = {
  sidebar: (state) => state.app.sidebar,
  device: (state) => state.app.device,
  token: (state) => state.user.token,
  roles: (state) => state.permission.roles,
  permission_routes: (state) => state.permission.routes,
  user_info: (state) => state.user.info,
};
export default getters;
