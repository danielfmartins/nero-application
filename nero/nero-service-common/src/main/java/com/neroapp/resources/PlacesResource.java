package com.neroapp.resources;

import java.util.List;

import com.neroapp.entities.places.Place;

public class PlacesResource extends ResourceList<Place, PlaceResource> {
	
	public static final String TEMPLATE_URI = "/places;reference={reference}{;name}{?maxResults}";

	public PlacesResource(List<Place> values) {
		super(values);
	}

	public List<PlaceResource> getPlaces() {
		return resourceList();
	}
}
