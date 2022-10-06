package com.cheerful.oj.common.vo;


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

	public Result() {
		this.code = 1;
		this.msg = "success";
		this.data = null;
	}

	public static <T> Result<T> success() {
		return new Result<>(0, "success", null);
	}

	public static <T> Result<T> success(Integer status, String msg) {
		return new Result<>(status, msg, null);
	}

	public static <T> Result<T> success(T data) {
		return new Result<>(0, "success", data);
	}

	public static <T> Result<T> error() {
		return new Result<>(-1, "error", null);
	}

	public static <T> Result<T> error(Integer status, String msg) {
		return new Result<>(status, msg, null);
	}

	public static <T> Result<T> error(T data) {
		return new Result<T>(-1, "error", data);
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
