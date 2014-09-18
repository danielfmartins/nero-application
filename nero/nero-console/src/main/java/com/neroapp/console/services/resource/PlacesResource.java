package com.neroapp.console.services.resource;

import java.util.ArrayList;
import java.util.List;

import com.neroapp.entities.places.Place;

public class PlacesResource extends Resource<List<Place>> {

	private List<PlaceResource> places;

	public PlacesResource() {
		super();
	}

	public List<PlaceResource> getPlaces() {
		if (this.places == null) {
			this.places = new ArrayList<>();
		}
		return places;
	}

	public void setPlaces(List<PlaceResource> places) {
		this.places = places;
	}

	@Override
	public List<Place> unwrap() {
		List<Place> values = new ArrayList<>();
		for (PlaceResource resource : this.getPlaces()) {
			values.add(resource.unwrap());
		}
		return values;
	}
}
