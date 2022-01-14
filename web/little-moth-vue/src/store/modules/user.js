import { login, logout } from "@/api/acount";
import {
  getToken,
  setToken,
  removeToken,
  setLocalStorage,
  getLocalStorage,
  removeLocalStorage,
} from "@/utils/auth";
import { resetRouter } from "@/router";
import defaultSettings from "@/settings";

const getDefaultState = () => {
  return {
    token: getToken(),
    info: {
      username: getLocalStorage("userInfo")
        ? getLocalStorage("userInfo").username
        : defaultSettings.userName,
      avatar: defaultSettings.avatar,
    },
  };
};

const state = getDefaultState();

const mutations = {
  // 重置State
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState());
  },
  // 储存token
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  // 储存用户信息
  SET_USERINFO: (state, userInfo) => {
    state.info = userInfo;
  },
};

const actions = {
  // 登录
  async Login ({ commit }, userInfo) {
    let result = await login(userInfo)
    if (result.code === 200) {
      const { token } = result;
      // 储存token
      commit("SET_TOKEN", token);
      setToken(token);
      return true
    } else {
      return Promise.reject(new Error(result.msg))
    }
  },

  // 获取用户信息
  async getUserInfo ({ commit, state }) {
    let result = await login(userInfo)
    if (result.code === 200) {
      const { token } = res;
      // 储存token
      commit("SET_USERINFO", token);
      return true
    } else {
      return Promise.reject(new Error(result.msg))
    }
  },

  // 退出系统
  async LogOut ({ commit, state }) {
    let result = await logout(state.token)
    if (result.code === 200) {
      removeToken() // 必须先删除token
      commit('RESET_STATE', '')
      console.log(1312312312);
      return true
    } else {
      return Promise.reject(new Error(result.msg))
    }
  },

  // 前端 登出
  async FedLogOut ({ commit }) {
    // return new Promise((resolve) => {
    //   removeToken(); // 必须先删除token
    //   commit("RESET_STATE");
    //   resolve();
    // });
    removeToken() // 必须先删除token
    commit('RESET_STATE', '')
    console.log(1312312312);
    return true
  },

};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
