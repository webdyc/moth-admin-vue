import Cookies from "js-cookie";
import defaultSettings from "@/settings.js";

const TokenKey = `${defaultSettings.systemTitle}_token`;

export function getToken() {
  return Cookies.get(TokenKey);
}

export function setToken(token) {
  return Cookies.set(TokenKey, token);
}

export function removeToken() {
  return Cookies.remove(TokenKey);
}

// localStorage存储
export function setLocalStorage(key, val) {
  return localStorage.setItem(
    `${defaultSettings.systemTitle}${key}`,
    JSON.stringify(val)
  );
}
export function getLocalStorage(key) {
  return JSON.parse(
    localStorage.getItem(`${defaultSettings.systemTitle}${key}`)
  );
}
export function removeLocalStorage(key) {
  return localStorage.removeItem(`${defaultSettings.systemTitle}${key}`);
}
export function clearLocalStorage() {
  return localStorage.clear();
}
