package com.neroapp.services.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.places.Place;

public class PlacesResource extends ResourceList<PlaceResource, Place> {

	public static final String TEMPLATE_URI = "/places;reference={reference}{;name}{?maxResults}";

	public PlacesResource(UriInfo uriInfo, List<Place> values) {
		super(values);
		URI uri = UriBuilder.fromUri(TEMPLATE_URI).buildFromMap(this.extractParameters(uriInfo));		
		add(new Link(uri.toString()));
	}

	public List<PlaceResource> getPlaces() {
		return resourceList();
	}
}
