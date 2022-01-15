package com.littlemoth.common.core.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.littlemoth.common.constant.CommonMessageConstants;
import com.littlemoth.common.utils.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author ：zhanghuicheng
 * @date ：Created in 2022/1/4 13:59
 * @description：返回类
 * @modified By：
 * @version: 0.0.1$
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultData<E> implements Serializable {

    public static final Integer SUCCESS = 200;

    /**
     * 序列化
     */
    private static final long serialVersionUID = 801303944859566772L;

    /**
     * 操作结果的状态码，200为成功，其余失败
     */
    @Builder.Default
    private Integer code = 200;

    /**
     * 操作结果的描述信息，可作为页面提示信息使用
     */
    private String msg;

    /**
     * 返回的业务数据
     */
    private E data;

    public static <E> ResultData<E> success() {
        return ResultData.<E>builder()
                .code(CommonMessageConstants.CODE_200)
                .msg(getMessage(CommonMessageConstants.CODE_200))
                .build();
    }
    public static <E> ResultData<E> success(E data) {
        return ResultData.<E>builder()
                .code(CommonMessageConstants.CODE_200)
                .msg(getMessage(CommonMessageConstants.CODE_200))
                .data(data)
                .build();
    }

    public static <E> ResultData<E> fail() {
        return ResultData.<E>builder()
                .code(CommonMessageConstants.CODE_0)
                .msg(getMessage(CommonMessageConstants.CODE_0))
                .build();
    }
    public static <E> ResultData<E> fail(E data) {
        return ResultData.<E>builder()
                .code(CommonMessageConstants.CODE_0)
                .msg(getMessage(CommonMessageConstants.CODE_0))
                .data(data)
                .build();
    }

    public static <E> ResultData<E> error() {
        return ResultData.<E>builder()
                .code(CommonMessageConstants.CODE_500)
                .msg(getMessage(CommonMessageConstants.CODE_500))
                .build();
    }
    public static <E> ResultData<E> error(int code,String msg) {
        return ResultData.<E>builder()
                .code(code)
                .msg(msg)
                .build();
    }
    public static <E> ResultData<E> error(E data) {
        return ResultData.<E>builder()
                .code(CommonMessageConstants.CODE_500)
                .msg(getMessage(CommonMessageConstants.CODE_500))
                .data(data)
                .build();
    }

    public static <E> ResultData<E> byCode(int code){
        return ResultData.<E>builder()
                .code(code)
                .msg(MessageUtils.message(String.valueOf(code)))
                .build();
    }
    public static <E> ResultData<E> byCode(int code,String... args){
        return ResultData.<E>builder()
                .code(code)
                .msg(MessageUtils.message(String.valueOf(code),args))
                .build();
    }
    public static <E> ResultData<E> byCode(int code,E data){
        return ResultData.<E>builder()
                .code(code)
                .msg(getMessage(code))
                .data(data)
                .build();
    }
    public static <E> ResultData<E> byCode(int code,E data,String... args){
        return ResultData.<E>builder()
                .code(code)
                .msg(getMessage(code,args))
                .data(data)
                .build();
    }

    private static String getMessage(int code,String... args){
        return MessageUtils.message(String.valueOf(code),args);
    }

    @Override
    public String toString() {
        return "ResultData{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

}
