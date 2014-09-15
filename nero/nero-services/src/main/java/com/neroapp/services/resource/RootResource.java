package com.neroapp.services.resource;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public class RootResource extends Resource {

	public static final String URI = "/";

	public RootResource() {
		super();
	}

	/**
	 * @see com.neroapp.services.resource.Resource#buildLinks(javax.ws.rs.core.UriInfo)
	 */
	@Override
	public void buildLinks(UriInfo uriInfo) {
		this.add(new Link(URI));
		this.add(new Link("create user", UserResource.URI, Method.POST));
		this.add(new Link("authentication", UserResource.AUTHENTICATION_URI, Method.POST));
		this.add(new Link("places", UriBuilder.fromUri(PlacesResource.TEMPLATE_URI).toTemplate()));		
	}
}