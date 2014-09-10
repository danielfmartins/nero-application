package com.neroapp.services.resource;

import java.util.ArrayList;
import java.util.List;

public abstract class Resource {

	private List<Link> links = new ArrayList<Link>();

	protected Resource() {
		super();
	}

	protected void add(Link link) {
		this.links.add(link);
	}

	public List<Link> getLinks() {
		return links;
	}
}