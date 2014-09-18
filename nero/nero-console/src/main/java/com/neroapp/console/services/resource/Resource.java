package com.neroapp.console.services.resource;

import java.util.ArrayList;
import java.util.List;

public abstract class Resource<T> {

	private List<Link> links = new ArrayList<Link>();

	public Resource() {
		super();
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Link> getLinks() {
		return links;
	}
	
	public abstract T unwrap();
}