package com.neroapp.business.exception;

public class BOException extends Exception {

	private static final long serialVersionUID = 1L;

	public BOException(String string) {
		super(string);
	}

	public BOException(Throwable e) {
		super(e);
	}

}
