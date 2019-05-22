package com.cafe24.mysite.dto;

public class JSONResult {
	private String result; // succes, fail
	private String message; // 실패 할 경우 세팅
	private Object data; // 성공할 경우 데이터를 넣어라

	public static JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}

	public static JSONResult success(Object data) {
		return new JSONResult("success", null, data);
	}

	private JSONResult(String result, String message, Object obj) {
		this.result = result;
		this.message = message;
		this.data = obj;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	
	
}
