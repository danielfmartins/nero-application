package com.neroapp.services.resource;

import java.io.Serializable;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.places.Place;

public class PlaceResource extends Resource {

	public static final String URI = "/places";
	public static final String TEMPLATE_URI = "/places/{id}";

	private Place place;

	public PlaceResource(Place place) {
		super();
		this.place = place;
	}

	/**
	 * @see com.neroapp.services.resource.Resource#buildLinks(javax.ws.rs.core.UriInfo)
	 */
	@Override
	public void buildLinks(UriInfo uriInfo) {
		String uri = TEMPLATE_URI.replace("{id}",
				String.valueOf(this.place.getId()));

		this.add(new Link(uri));

		this.add(new Link("qualify", UriBuilder.fromUri(uri).segment("qualify")
				.toTemplate(), Method.POST));

		this.add(new Link("qualifications", QualificationsResource.TEMPLATE_URI
				.replace("{id}", String.valueOf(place.getId()))));

		this.add(new Link("recommended hashtags", HashtagsResource.TEMPLATE_URI
				.replace("{id}", String.valueOf(place.getId()))));

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
}
