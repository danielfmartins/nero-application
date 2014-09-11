package com.neroapp.services.resource;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.places.Place;

public class PlaceResource extends Resource {

	public static final String URI = "/places";
	public static final String TEMPLATE_URI = "/places/{id}";

	private Place place;

	public PlaceResource(Place place) {
		super();
		this.place = place;

		String uri = resolveTemplate(Collections.singletonMap("id", place.getId()));

		this.add(new Link(uri));

		this.add(new Link("qualify", UriBuilder.fromUri(uri).segment("qualify")
				.toTemplate(), Method.POST));

		this.add(new Link("qualifications",
				QualificationsResource.resolveTemplate(Collections
						.singletonMap("id", place.getId())), Method.GET));

		this.add(new Link("recommended hashtags", HashtagsResource
				.resolveTemplate(Collections.singletonMap("id",
						place.getId())), Method.GET));
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
	
	public static String resolveTemplate(Map<?, ?> parameters) {
		String template = TEMPLATE_URI;
		if (parameters != null) {
			if (parameters.containsKey("id")) {
				template = template.replace("{id}", String.valueOf(parameters.get("id")));
			}
		}
		return UriBuilder.fromUri(template).toTemplate();
	}
}
