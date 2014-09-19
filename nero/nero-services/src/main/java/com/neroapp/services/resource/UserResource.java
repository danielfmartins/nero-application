package com.neroapp.services.resource;

import javax.ws.rs.core.UriInfo;

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

	/**
	 * @see com.neroapp.services.resource.Resource#buildLinks(javax.ws.rs.core.UriInfo)
	 */
	@Override
	public void buildLinks(UriInfo uriInfo) {
		String uri = TEMPLATE_URI.replace("{username}",
				String.valueOf(this.user.getUsername()));
		this.add(new Link(uri));
	}
}
