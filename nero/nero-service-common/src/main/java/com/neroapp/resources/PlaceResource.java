package com.neroapp.resources;

import java.io.Serializable;

import com.neroapp.entities.places.Place;

public class PlaceResource extends Resource<Place> {
	
	public static final String URI = "/places";
	public static final String TEMPLATE_URI = "/places/{id}";

	private Place place;

	public PlaceResource() {
		super();
		this.place = new Place();
	}
	
	public PlaceResource(Place place) {
		super();
		this.place = place;
	}

	public Serializable getId() {
		return place.getId();
	}
	
	public String getName() {
		return place.getName();
	}

	public Serializable getReference() {
		return place.getReference();
	}

	public Long getTotalThumbsDown() {
		return place.getTotalThumbsDown();
	}

	public Long getTotalThumbsUp() {
		return place.getTotalThumbsUp();
	}

	public void setId(String id) {
		this.place.setId(id);
	}

	public void setName(String name) {
		this.place.setName(name);
	}

	public void setReference(String reference) {
		this.place.setReference(reference);
	}

	public void setTotalThumbsDown(Long totalThumbsDown) {
		this.place.setTotalThumbsDown(totalThumbsDown);
	}

	public void setTotalThumbsUp(Long totalThumbsUp) {
		this.place.setTotalThumbsUp(totalThumbsUp);
	}

	@Override
	public Place unwrap() {
		return this.place;
	}
}
