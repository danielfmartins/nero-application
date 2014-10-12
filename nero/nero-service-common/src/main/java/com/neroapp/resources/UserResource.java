package com.neroapp.resources;

import com.neroapp.entities.User;

public class UserResource extends Resource<User> {

	public static final String URI = "/users";
	public static final String AUTHENTICATION_URI = "/users/authentication";
	public static final String TEMPLATE_URI = "/users/{username}";
	
	private User user;

	public UserResource(User user) {
		super();
		this.user = user;
	}

	public String getUsername() {
		return user.getUsername();
	}

	public String getUserPreferredCountry() {
		return user.getUserPreferredCountry();
	}

	@Override
	public User unwrap() {
		return this.user;
	}
}
