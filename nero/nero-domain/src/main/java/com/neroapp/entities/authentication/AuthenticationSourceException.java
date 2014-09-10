package com.neroapp.entities.authentication;


public class AuthenticationSourceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public AuthenticationSourceException(String details) {
		super(details);
	}


	public AuthenticationSourceException(Throwable e) {
		super(e);
	}


}
