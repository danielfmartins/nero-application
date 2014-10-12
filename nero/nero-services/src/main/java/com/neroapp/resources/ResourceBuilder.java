package com.neroapp.resources;

import javax.ws.rs.core.UriInfo;

public interface ResourceBuilder {
	
	boolean supports(Class<? extends Resource<?>> resourceType);

	Resource<?> build(Object value, UriInfo uriInfo);

}
