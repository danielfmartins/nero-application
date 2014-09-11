package com.neroapp.services.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.places.Place;

public class PlacesResource extends ResourceList<PlaceResource, Place> {

	public static final String TEMPLATE_URI = "/places;reference={reference}{;name}{?maxResults}";

	public PlacesResource(List<Place> values, Map<String, Object> parameters) {
		super(values);
		String uri = resolveTemplate(parameters);		
		add(new Link(uri.toString()));
	}

	public List<PlaceResource> getPlaces() {
		return resourceList();
	}
	
	public static String resolveTemplate(Map<?, ?> parameters) {
		String template = TEMPLATE_URI;
		if (parameters != null) {
			if (parameters.containsKey("reference")) {
				template = template.replace("{reference}", String.valueOf(parameters.get("reference")));
			}
			if (parameters.containsKey("name")) {
				template = template.replace("{name}", String.valueOf(parameters.get("name")));
			}
			if (parameters.containsKey("maxResults")) {
				template = template.replace("{maxResults}", String.valueOf(parameters.get("maxResults")));
			}
		}
		return UriBuilder.fromUri(template).toTemplate();
	}
}
