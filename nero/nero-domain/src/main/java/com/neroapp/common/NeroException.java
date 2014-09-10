package com.neroapp.common;

public class NeroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NeroException(String msg) {
		super(msg);
	}
	
	public NeroException(Throwable e) {
		super(e);
	}
	

}
