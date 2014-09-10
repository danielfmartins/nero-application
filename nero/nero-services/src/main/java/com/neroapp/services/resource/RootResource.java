package com.neroapp.services.resource;

import javax.ws.rs.core.UriBuilder;


public class RootResource extends Resource {

	public static final String URI = "/";

	public RootResource() {
		super();
		this.add(new Link(URI));
		this.add(new Link("places", UriBuilder.fromUri(PlacesResource.TEMPLATE_URI).toTemplate()));
	}
}