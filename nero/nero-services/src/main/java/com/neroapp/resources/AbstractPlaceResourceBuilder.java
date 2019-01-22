package com.neroapp.resources;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractPlaceResourceBuilder implements ResourceBuilder {

	protected Resource<?> addLinks(PlaceResource resource, UriInfo uriInfo) {
		if (resource.getId() != null) {
			String uri = PlaceResource.TEMPLATE_URI.replace("{id}",
					String.valueOf(resource.getId()));
			resource.add(new Link(uri));
			resource.add(new Link("qualify", UriBuilder.fromUri(uri)
					.segment("qualify").toTemplate(), Method.POST));
			resource.add(new Link("qualifications",
					QualificationsResource.TEMPLATE_URI.replace("{id}",
							String.valueOf(resource.getId()))));
			resource.add(new Link("recommended hashtags",
					HashtagsResource.TEMPLATE_URI.replace("{id}",
							String.valueOf(resource.getId()))));
		}
		return resource;
	}
}
