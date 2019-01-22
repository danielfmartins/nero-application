package com.neroapp.common.exceptions;

public abstract class AbstractHttpException extends RuntimeException {
	
	private static final long serialVersionUID = 6731791188721910870L;
	
	static final int BAD_REQUEST = 400;
	
	static final int NOT_FOUND = 404;
	
	/** Http Status Code*/
	private Integer statusCode;

	protected AbstractHttpException(Integer statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}
	
	public Integer getStatusCode() {
		return this.statusCode;
	}
}
