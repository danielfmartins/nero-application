package com.neroapp.resources;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;

@RequestScoped
public class ResourceBuilderManager {

	@Inject
	@Any
	private Instance<ResourceBuilder> builders;

	public ResourceBuilderManager() {
		super();
	}

	@SuppressWarnings("unchecked")
	public <T, R extends Resource<T>> R build(Class<R> resourceType,
			T value, UriInfo uriInfo) {
		for (ResourceBuilder builder : this.builders) {
			if (builder.supports(resourceType)) {
				return (R) builder.build(value, uriInfo);
			}
		}
		throw new RuntimeException(String.format(
				"There are not a resource builder registered for type %s",
				resourceType.getName()));
	}
}
