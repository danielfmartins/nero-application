package com.neroapp.resources;

import java.util.List;

public class RootResource extends Resource<List<Link>> {

	public static final String URI = "/";
	
	public RootResource() {
		super();
		this.add(new Link(URI));
		this.add(new Link("create user", UserResource.URI, Method.POST));
		this.add(new Link("authentication", UserResource.AUTHENTICATION_URI, Method.POST));
		this.add(new Link("places", PlacesResource.TEMPLATE_URI));	
	}

	@Override
	public List<Link> unwrap() {
		return super.getLinks();
	}
}