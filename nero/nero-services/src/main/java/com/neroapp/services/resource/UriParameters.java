package com.neroapp.services.resource;

public class UriParameters {

	private String required;

	private String optimal;

	public UriParameters(String required) {
		super();
		this.required = required;
	}

	public UriParameters(String required, String optimal) {
		super();
		this.required = required;
		this.optimal = optimal;
	}

	public String getRequired() {
		return required;
	}

	public String getOptimal() {
		return optimal;
	}
}
