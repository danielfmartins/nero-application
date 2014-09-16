package com.neroapp.services.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.places.Place;
import com.neroapp.services.uri.NeroUriBuilder;
import com.neroapp.services.uri.UriParameters;

public class PlacesResource extends ResourceList<PlaceResource, Place> {

	public static final String TEMPLATE_URI = "/places;reference={reference}{;name}{?maxResults}";

	public PlacesResource(List<Place> values) {
		super(values);
	}

	/**
	 * @see com.neroapp.services.resource.ResourceList#buildResourceLinks(javax.ws.rs.core.UriInfo)
	 */
	@Override
	protected void buildResourceLinks(UriInfo uriInfo) {
		UriParameters uriParameters = UriParameters.builder().path("reference")
				.matrix("name").query("maxResults").build();
		URI uri = NeroUriBuilder.build(TEMPLATE_URI, uriInfo, uriParameters);
		add(new Link(uri.toString()));
	}

	public List<PlaceResource> getPlaces() {
		return resourceList();
	}

}
