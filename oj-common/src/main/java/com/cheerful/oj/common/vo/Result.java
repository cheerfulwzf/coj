package com.cheerful.oj.common.vo;


import org.springframework.scheduling.annotation.Async;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/12 15:36
 * @DESCRIPTION:
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(){
        return new Result<>(0,"success",null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(0,"success",data);
    }

    public static <T> Result<T> error(){
        return new Result<>(-1,"error",null);
    }

    public static <T> Result<T> error(T data){
        return new Result<T>(-1,"error",data);
    }

    public Result() {
        this.code=1;
        this.msg="success";
        this.data=null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer status) {
        this.code = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
