package com.neroapp.services.resource;

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
