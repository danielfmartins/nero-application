package com.neroapp.services.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriInfo;

public abstract class Resource {

	private List<Link> links = new ArrayList<Link>();

	protected Resource() {
		super();
	}

	protected void add(Link link) {
		this.links.add(link);
	}

	public abstract void buildLinks(UriInfo uriInfo);

	public List<Link> getLinks() {
		return links;
	}
}