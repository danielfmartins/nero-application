package com.neroapp.persistence.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DAOException(String string) {
		super(string);
	}
	
	public DAOException(Throwable throwable) {
		super(throwable);
	}

}
