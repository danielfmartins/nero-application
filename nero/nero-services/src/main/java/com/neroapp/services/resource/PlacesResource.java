package com.neroapp.services.resource;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.places.Place;

public class PlacesResource extends ResourceList<PlaceResource, Place> {

	public static final String TEMPLATE_URI = "/places;reference={reference}{;name}{?maxResults}";

	public PlacesResource(List<Place> values, Map<String, Object> parameters) {
		super(values);
		URI uri = UriBuilder.fromUri(TEMPLATE_URI).buildFromMap(parameters);		
		add(new Link(uri.toString()));
	}

	public List<PlaceResource> getPlaces() {
		return resourceList();
	}
}
