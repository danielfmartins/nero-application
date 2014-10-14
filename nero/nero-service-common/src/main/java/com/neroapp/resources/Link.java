package com.neroapp.resources;

public class Link {

	private String rel;

	private String href;

	private Method method;

	public Link() {
		super();
	}

	public Link(String href) {
		this("self", href);
	}

	public Link(String rel, String href) {
		this(rel, href, Method.GET);
	}

	public Link(String rel, String href, Method method) {
		this.rel = rel;
		this.href = href;
		this.method = method;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Method getMethod() {
		return method;
	}
}