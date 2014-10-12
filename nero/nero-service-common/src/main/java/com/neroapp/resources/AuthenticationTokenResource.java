package com.neroapp.resources;

public class AuthenticationTokenResource {

	private String authenticationToken;

	public AuthenticationTokenResource(String authenticationToken) {
		super();
		this.authenticationToken = authenticationToken;
	}
	
	public String getAuthenticationToken() {
		return authenticationToken;
	}
	
}
