package com.neroapp.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.User;

@RequestScoped
@Named("userResourceBuilder")
public class UserResourceBuilder implements ResourceBuilder {

	@Override
	public boolean supports(Class<? extends Resource<?>> resourceType) {
		return UserResource.class == resourceType;
	}

	@Override
	public Resource<?> build(Object value, UriInfo uriInfo) {
		User user = (User) value;
		UserResource resource = new UserResource(user);
		String uri = UserResource.TEMPLATE_URI.replace("{username}",
				String.valueOf(resource.getUsername()));
		resource.add(new Link(uri));
		return resource;
	}

}
