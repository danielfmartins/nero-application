package com.neroapp.common.exceptions;

public class NotFoundExpcetion extends AbstractHttpException {

	private static final long serialVersionUID = -8970216344097079213L;

	public NotFoundExpcetion(String message) {
		super(NOT_FOUND, message);
	}

}
