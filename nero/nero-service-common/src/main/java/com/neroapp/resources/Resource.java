package com.neroapp.resources;

import java.util.ArrayList;
import java.util.List;

public abstract class Resource<T> {

	private List<Link> links = new ArrayList<Link>();

	public Resource() {
		super();
	}

	protected void add(Link link) {
		this.links.add(link);
	}

	public List<Link> getLinks() {
		return links;
	}
	
	public abstract T unwrap();
}