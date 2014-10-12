package com.neroapp.resources;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.places.Place;
import com.neroapp.services.uri.NeroUriBuilder;
import com.neroapp.services.uri.UriParameters;

@RequestScoped
@Named("placesResourceBuilder")
public class PlacesResourceBuilder extends AbstractPlaceResourceBuilder {

	@Override
	public boolean supports(Class<? extends Resource<?>> resourceType) {
		return (PlacesResource.class == resourceType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Resource<?> build(Object value, UriInfo uriInfo) {
		List<Place> values = (List<Place>) value;
		PlacesResource resource = new PlacesResource(values);
		UriParameters uriParameters = UriParameters.builder().path("reference")
				.matrix("name").query("maxResults").build();
		URI uri = NeroUriBuilder.build(PlacesResource.TEMPLATE_URI, uriInfo,
				uriParameters);
		resource.add(new Link(uri.toString()));
		for (PlaceResource obj : resource.resourceList()) {
			this.addLinks(obj, uriInfo);
		}
		return resource;
	}

}
