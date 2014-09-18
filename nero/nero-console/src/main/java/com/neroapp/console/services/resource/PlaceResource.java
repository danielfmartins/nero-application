package com.neroapp.console.services.resource;

import com.neroapp.entities.places.Place;

public class PlaceResource extends Resource<Place> {

	private Place place;
	
	public PlaceResource() {
		super();
		this.place = new Place();
	}

	public PlaceResource(Place place) {
		super();
		this.place = place;
	}

	public String getId() {
		return (String) place.getId();
	}

	public String getName() {
		return place.getName();
	}

	public String getReference() {
		return (String) place.getReference();
	}

	public Long getTotalThumbsDown() {
		return place.getTotalThumbsDown();
	}

	public Long getTotalThumbsUp() {
		return place.getTotalThumbsUp();
	}

	public void setId(String id) {
		place.setId(id);
	}

	public void setName(String name) {
		place.setName(name);
	}

	public void setReference(String reference) {
		place.setReference(reference);
	}

	public void setTotalThumbsDown(Long totalThumbsDown) {
		place.setTotalThumbsDown(totalThumbsDown);
	}

	public void setTotalThumbsUp(Long totalThumbsUp) {
		place.setTotalThumbsUp(totalThumbsUp);
	}

	@Override
	public Place unwrap() {
		return this.place;
	}
}