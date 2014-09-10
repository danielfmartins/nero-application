package com.neroapp.services.resource;

import java.io.Serializable;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.places.Place;

public class PlaceResource extends Resource {

	public static final String URI = "/places";
	public static final String TEMPLATE_URI = "/places/{id}";

	private Place place;

	public PlaceResource(Place place) {
		super();
		this.place = place;
		URI uri = UriBuilder.fromUri(TEMPLATE_URI).resolveTemplate("id", place.getId()).build();
		this.add(new Link(uri.toString()));
		this.add(new Link("qualifications", UriBuilder.fromUri(uri).segment("qualifications").build().toString(), Method.GET));
		this.add(new Link("qualify", UriBuilder.fromUri(uri).segment("qualify").build().toString(), Method.POST));
	}

	public Serializable getReference() {
		return place.getReference();
	}

	public Serializable getId() {
		return place.getId();
	}

	public String getName() {
		return place.getName();
	}

	public Long getTotalThumbsUp() {
		return place.getTotalThumbsUp();
	}

	public Long getTotalThumbsDown() {
		return place.getTotalThumbsDown();
	}
}
