package com.neroapp.services.resource;



public class RootResource extends Resource {

	public static final String URI = "/";

	public RootResource() {
		super();
		this.add(new Link(URI));
		this.add(new Link("authentication", UserResource.resolveTemplate(null), Method.POST));
		this.add(new Link("places", PlacesResource.resolveTemplate(null)));
	}
}