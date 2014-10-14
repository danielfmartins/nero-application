package com.neroapp.resources;

public class AuthenticationTokenResource {

	private String authenticationToken;

	public AuthenticationTokenResource() {
		super();
	}

	public AuthenticationTokenResource(String authenticationToken) {
		super();
		this.authenticationToken = authenticationToken;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
}
