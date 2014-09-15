package com.neroapp.services.resource;

import java.util.List;

import javax.ws.rs.core.UriInfo;

public class ResourceBuilder {

	private ResourceBuilder() {
		super();
	}
	
	public static <R extends Resource, T> R build(Class<R> resourceType) {
		return build(resourceType, null, null);
	}

	public static <R extends Resource, T> R build(Class<R> resourceType, T value) {
		return build(resourceType, value, null);
	}

	public static <R extends Resource, T> R build(Class<R> resourceType,
			T value, UriInfo uriInfo) {
		try {
			R resource = null;
			if (value != null) {
				resource = resourceType
						.getConstructor(
								ResourceList.class
										.isAssignableFrom(resourceType) ? List.class
										: value.getClass()).newInstance(value);
			} else {
				resource = resourceType.getConstructor().newInstance();
			}
			resource.buildLinks(uriInfo);
			return resource;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
