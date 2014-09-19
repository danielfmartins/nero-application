package com.neroapp.console.services.resource;

public class MessageResource {

	private String message;

	public MessageResource() {
		super();
	}

	public MessageResource(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return this.message;
	}

}