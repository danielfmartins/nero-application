package com.neroapp.services.resource;

import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.User;

public class UserResource extends Resource {

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
	
	public static String resolveTemplate(Map<?, ?> parameters) {
		String template = TEMPLATE_URI;
		if (parameters != null) {
			if (parameters.containsKey("username")) {
				template = template.replace("{username}", String.valueOf(parameters.get("username")));
			}
		}
		return UriBuilder.fromUri(template).toTemplate();
	}	
}
