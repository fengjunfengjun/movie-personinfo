package com.utils;

public class MessageUtil {
	private int code;
	private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private String msg;
	public MessageUtil(int code, Object data, String msg) {
		super();
		this.code = code;
		this.data = data;
		this.msg = msg;
	}
	
	public MessageUtil() {
		
	}
	
}
