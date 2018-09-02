package com.accsys.server.util;
public class PosException extends Exception {
	
	private static final long serialVersionUID = 100L;
	
	private String errorMessage;
	private int errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public PosException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public PosException(String errorMessage, int errorCode) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode=errorCode;
	}
	public PosException() {
		super();
	}
}