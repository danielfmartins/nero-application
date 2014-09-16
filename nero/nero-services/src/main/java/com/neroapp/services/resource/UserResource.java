package com.neroapp.services.resource;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.User;
import com.neroapp.services.uri.NeroUriBuilder;
import com.neroapp.services.uri.UriParameters;

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
		UriParameters uriParameters = UriParameters.builder().path("username")
				.build();
		URI uri = NeroUriBuilder.build(TEMPLATE_URI, uriInfo, uriParameters);
		this.add(new Link(uri.toString()));
	}
}
