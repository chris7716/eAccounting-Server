package com.accsys.server.util;

public class CustomException extends Exception{
	
private static final long serialVersionUID= 100L;
	
	private String errorMessage;
	private int errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}
	public int getErrorCode(){
		return errorCode;
	}
	public CustomException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public CustomException(String errorMessage, int errorCode) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode=errorCode;
	}
	public CustomException() {
		super();
	}

}
