package com.example.demo;

public class ExceptionResponse {

	
	@Override
	public String toString() {
		return "ExceptionResponse [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	String message;

	public ExceptionResponse(String message) {
		super();
		this.message = message;
	}
	
}
