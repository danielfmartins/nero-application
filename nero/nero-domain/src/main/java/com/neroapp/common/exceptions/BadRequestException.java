package com.neroapp.common.exceptions;

public class BadRequestException extends AbstractHttpException {
	
	private static final long serialVersionUID = 5920102238117811071L;

	public BadRequestException(String message) {
		super(BAD_REQUEST, message);
	}
	
}
