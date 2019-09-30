package com.example.demo.model;

public class ResponseAPI {
	private String message;

	public ResponseAPI(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
