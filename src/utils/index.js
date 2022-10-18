import CryptoJS from "crypto-js";
/**
 * 检测是否为纯粹的对象
 * @param {*} value 手机号
 */
export const isPlainObject = function isPlainObject (obj) {
  let proto, Ctor;
  if (!obj || Object.prototype.toString.call(obj) !== "[object Object]")
    return false;
  proto = Object.getPrototypeOf(obj);
  if (!proto) return true;
  Ctor = proto.hasOwnProperty("constructor") && proto.constructor;
  return typeof Ctor === "function" && Ctor === Object;
};

/**
 * 将时间解析为字符串
 * @param {(Object|string|number)} time
 * @param {string} cFormat
 * @returns {string | null}
 */
export function parseTime (time, cFormat) {
  if (arguments.length === 0 || !time) {
    return null;
  }
  const format = cFormat || "{y}-{m}-{d} {h}:{i}:{s}";
  let date;
  if (typeof time === "object") {
    date = time;
  } else {
    if (typeof time === "string") {
      if (/^[0-9]+$/.test(time)) {
        // support "1548221490638"
        time = parseInt(time);
      } else {
        // support safari
        // https://stackoverflow.com/questions/4310953/invalid-date-in-safari
        time = time.replace(new RegExp(/-/gm), "/");
      }
    }

    if (typeof time === "number" && time.toString().length === 10) {
      time = time * 1000;
    }
    date = new Date(time);
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay(),
  };
  const time_str = format.replace(/{([ymdhisa])+}/g, (result, key) => {
    const value = formatObj[key];
    // Note: getDay() returns 0 on Sunday
    if (key === "a") {
      return ["日", "一", "二", "三", "四", "五", "六"][value];
    }
    return value.toString().padStart(2, "0");
  });
  return time_str;
}

/**
 * 检测两个数组是否相同
 * @param {*} value 数组
 * @author webdyc
 */
export const equalsArray = function equalsArray (array1, array2) {
  if (!array1 && !array2) return false;

  if (array1.length != array2.length) return false;

  for (var i = 0, l = array1.length; i < l; i++) {
    if (array1[i] instanceof Array && array2[i] instanceof Array) {
      if (!array1[i].equals(array2[i])) return false;
    } else if (array1[i] != array2[i]) {
      return false;
    }
  }
  return true;
};

/**
 * 检测两个对象是否相同
 * @param {*} value 数组
 * @author xiliankum
 */
export const equalsObject = function equalsObject (object1, object2) {
  //For the first loop, we only check for types
  for (let propName in object1) {
    //Check for inherited methods and properties - like .equals itself
    //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/hasOwnProperty
    //Return false if the return value is different
    if (object1.hasOwnProperty(propName) != object2.hasOwnProperty(propName)) {
      return false;
    }
    //Check instance type
    else if (typeof object1[propName] != typeof object2[propName]) {
      //Different types => not equal
      return false;
    }
  }
  //Now a deeper check using other objects property names
  for (let propName in object2) {
    //We must check instances anyway, there may be a property that only exists in object2
    //I wonder, if remembering the checked values from the first loop would be faster or not
    if (object1.hasOwnProperty(propName) != object2.hasOwnProperty(propName)) {
      return false;
    } else if (typeof object1[propName] != typeof object2[propName]) {
      return false;
    }
    //If the property is inherited, do not check any more (it must be equa if both objects inherit it)
    if (!object1.hasOwnProperty(propName)) continue;

    //Now the detail check and recursion

    //This returns the script back to the array comparing
    /**REQUIRES Array.equals**/
    if (
      object1[propName] instanceof Array &&
      object2[propName] instanceof Array
    ) {
      // recurse into the nested arrays
      if (!object1[propName].equals(object2[propName])) return false;
    } else if (
      object1[propName] instanceof Object &&
      object2[propName] instanceof Object
    ) {
      // recurse into another objects
      //console.log("Recursing to compare ", this[propName],"with",object2[propName], " both named \""+propName+"\"");
      if (!object1[propName].equals(object2[propName])) return false;
    }
    //Normal value comparison for strings and numbers
    else if (object1[propName] != object2[propName]) {
      return false;
    }
  }
  //If everything passed, let's say YES
  return true;
};

/**
 * 树状数组根据子节点查找所有父节点
 * @param {
 *  array: 数组
 *  value: 值
 *  valueName: "value"  需要遍历数组的字段名
 *  childrenName: "children" 需要遍历数组的嵌套子级名
 * }
 * @author webdyc
 */
export const findPatentValue = function findPatentValue (
  array,
  value,
  valueName = "value",
  childrenName = "children"
) {
  if (!value || !Array.isArray(array)) return [];
  const result = [];
  let valid = false;
  const seek = (array, value) => {
    let parentValue = "";
    const up = (array, value, lastValue) => {
      array.forEach((v) => {
        const val = v[valueName];
        const child = v[childrenName];
        if (val === value) {
          valid = true;
          parentValue = lastValue;
          return;
        }
        if (child && child.length) up(child, value, val);
      });
    };
    up(array, value);
    if (parentValue) {
      result.unshift(parentValue);
      seek(array, parentValue);
    }
  };
  seek(array, value);
  return valid ? [...result, value] : [];
};

/**
 * @param {number} time
 * @param {string} option
 * @returns {string}
 */
export function formatTime (time, option) {
  if (("" + time).length === 10) {
    time = parseInt(time) * 1000;
  } else {
    time = +time;
  }
  const d = new Date(time);
  const now = Date.now();

  const diff = (now - d) / 1000;

  if (diff < 30) {
    return "刚刚";
  } else if (diff < 3600) {
    // less 1 hour
    return Math.ceil(diff / 60) + "分钟前";
  } else if (diff < 3600 * 24) {
    return Math.ceil(diff / 3600) + "小时前";
  } else if (diff < 3600 * 24 * 2) {
    return "1天前";
  }
  if (option) {
    return parseTime(time, option);
  } else {
    return (
      d.getMonth() +
      1 +
      "月" +
      d.getDate() +
      "日" +
      d.getHours() +
      "时" +
      d.getMinutes() +
      "分"
    );
  }
}

/**
 * @param {string} url
 * @returns {Object}
 */
export function getQueryObject (url) {
  url = url == null ? window.location.href : url;
  const search = url.substring(url.lastIndexOf("?") + 1);
  const obj = {};
  const reg = /([^?&=]+)=([^?&=]*)/g;
  search.replace(reg, (rs, $1, $2) => {
    const name = decodeURIComponent($1);
    let val = decodeURIComponent($2);
    val = String(val);
    obj[name] = val;
    return rs;
  });
  return obj;
}

/**
 * @param {string} input value
 * @returns {number} output value
 */
export function byteLength (str) {
  // returns the byte length of an utf8 string
  let s = str.length;
  for (var i = str.length - 1; i >= 0; i--) {
    const code = str.charCodeAt(i);
    if (code > 0x7f && code <= 0x7ff) s++;
    else if (code > 0x7ff && code <= 0xffff) s += 2;
    if (code >= 0xdc00 && code <= 0xdfff) i--;
  }
  return s;
}

/**
 * @param {Array} actual
 * @returns {Array}
 */
export function cleanArray (actual) {
  const newArray = [];
  for (let i = 0; i < actual.length; i++) {
    if (actual[i]) {
      newArray.push(actual[i]);
    }
  }
  return newArray;
}

/**
 * @param {Object} json
 * @returns {Array}
 */
export function param (json) {
  if (!json) return "";
  return cleanArray(
    Object.keys(json).map((key) => {
      if (json[key] === undefined) return "";
      return encodeURIComponent(key) + "=" + encodeURIComponent(json[key]);
    })
  ).join("&");
}

/**
 * @param {string} url
 * @returns {Object}
 */
export function param2Obj (url) {
  const search = decodeURIComponent(url.split("?")[1]).replace(/\+/g, " ");
  if (!search) {
    return {};
  }
  const obj = {};
  const searchArr = search.split("&");
  searchArr.forEach((v) => {
    const index = v.indexOf("=");
    if (index !== -1) {
      const name = v.substring(0, index);
      const val = v.substring(index + 1, v.length);
      obj[name] = val;
    }
  });
  return obj;
}

/**
 * @param {string} val
 * @returns {string}
 */
export function html2Text (val) {
  const div = document.createElement("div");
  div.innerHTML = val;
  return div.textContent || div.innerText;
}

/**
 * Merges two objects, giving the last one precedence
 * @param {Object} target
 * @param {(Object|Array)} source
 * @returns {Object}
 */
export function objectMerge (target, source) {
  if (typeof target !== "object") {
    target = {};
  }
  if (Array.isArray(source)) {
    return source.slice();
  }
  Object.keys(source).forEach((property) => {
    const sourceProperty = source[property];
    if (typeof sourceProperty === "object") {
      target[property] = objectMerge(target[property], sourceProperty);
    } else {
      target[property] = sourceProperty;
    }
  });
  return target;
}

/**
 * @param {HTMLElement} element
 * @param {string} className
 */
export function toggleClass (element, className) {
  if (!element || !className) {
    return;
  }
  let classString = element.className;
  const nameIndex = classString.indexOf(className);
  if (nameIndex === -1) {
    classString += "" + className;
  } else {
    classString =
      classString.substr(0, nameIndex) +
      classString.substr(nameIndex + className.length);
  }
  element.className = classString;
}

/**
 * @param {string} type
 * @returns {Date}
 */
export function getTime (type) {
  if (type === "start") {
    return new Date().getTime() - 3600 * 1000 * 24 * 90;
  } else {
    return new Date(new Date().toDateString());
  }
}

/**
 * 防抖
 * @param {Function} func
 * @param {number} wait
 * @param {boolean} immediate
 * @return {*}
 */
export function debounce (func, wait, immediate) {
  let timeout, args, context, timestamp, result;

  const later = function () {
    // 据上一次触发时间间隔
    const last = +new Date() - timestamp;

    // 上次被包装函数被调用时间间隔 last 小于设定时间间隔 wait
    if (last < wait && last > 0) {
      timeout = setTimeout(later, wait - last);
    } else {
      timeout = null;
      // 如果设定为immediate===true，因为开始边界已经调用过了此处无需调用
      if (!immediate) {
        result = func.apply(context, args);
        if (!timeout) context = args = null;
      }
    }
  };

  return function (...args) {
    context = this;
    timestamp = +new Date();
    const callNow = immediate && !timeout;
    // 如果延时不存在，重新设定延时
    if (!timeout) timeout = setTimeout(later, wait);
    if (callNow) {
      result = func.apply(context, args);
      context = args = null;
    }

    return result;
  };
}

/**
 * 这只是深度复制的一个简单版本
 * 有很多 bug
 * 如果你想使用完美的深度拷贝，请使用lodash的_.cloneDeep
 * @param {Object} source
 * @returns {Object}
 */
export function deepClone (source) {
  if (!source && typeof source !== "object") {
    throw new Error("error arguments", "deepClone");
  }
  const targetObj = source.constructor === Array ? [] : {};
  Object.keys(source).forEach((keys) => {
    if (source[keys] && typeof source[keys] === "object") {
      targetObj[keys] = deepClone(source[keys]);
    } else {
      targetObj[keys] = source[keys];
    }
  });
  return targetObj;
}

/**
 * @param {Array} arr
 * @returns {Array}
 */
export function uniqueArr (arr) {
  return Array.from(new Set(arr));
}

/**
 * @returns {string}
 */
export function createUniqueString () {
  const timestamp = +new Date() + "";
  const randomNum = parseInt((1 + Math.random()) * 65536) + "";
  return (+(randomNum + timestamp)).toString(32);
}

/**
 * 检查元素是否有类
 * @param {HTMLElement} elm
 * @param {string} cls
 * @returns {boolean}
 */
export function hasClass (ele, cls) {
  return !!ele.className.match(new RegExp("(\\s|^)" + cls + "(\\s|$)"));
}

/**
 * 将类添加到元素
 * @param {HTMLElement} elm
 * @param {string} cls
 */
export function addClass (ele, cls) {
  if (!hasClass(ele, cls)) ele.className += " " + cls;
}

/**
 * 从元素中删除类
 * @param {HTMLElement} elm
 * @param {string} cls
 */
export function removeClass (ele, cls) {
  if (hasClass(ele, cls)) {
    const reg = new RegExp("(\\s|^)" + cls + "(\\s|$)");
    ele.className = ele.className.replace(reg, " ");
  }
}

//拉平数据对象
export function steamroller (arr) {
  var newArr = [];
  arr.forEach((element) => {
    newArr.push(element);
    if (element.children) {
      newArr.push.apply(newArr, steamroller(element.children));
      delete element.children;
    } else {
    }
  });

  return newArr;
}

/**
 * 加密密码
 * @param {*} word 需要加密的值
 * @param {*} keyStr 加密规则
 */
export function encrypt (word, keyStr) {
  keyStr = keyStr ? keyStr : "gsTd20221S**=YJU"; //判断是否存在ksy，不存在就用定义好的key
  var key = CryptoJS.enc.Utf8.parse(keyStr);
  var srcs = CryptoJS.enc.Utf8.parse(word);
  var encrypted = CryptoJS.AES.encrypt(srcs, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7,
  });
  return encrypted.toString();
}