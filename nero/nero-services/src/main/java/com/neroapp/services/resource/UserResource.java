package com.neroapp.services.resource;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.User;

public class UserResource extends Resource {

	public static final String URI = "/users";
	public static final String TEMPLATE_URI = "/users/{username}";	
	
	private User user;

	public UserResource(User user) {
		super();
		this.user = user;
		URI uri = UriBuilder.fromUri(TEMPLATE_URI).resolveTemplate("username", user.getUsername()).build();
		this.add(new Link(uri.toString()));
	}

	public String getUsername() {
		return user.getUsername();
	}

	public String getUserPreferredCountry() {
		return user.getUserPreferredCountry();
	}
}
