package com.neroapp.services.resource;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.places.Place;

public class PlacesResource extends ResourceList<PlaceResource, Place> {

	public static final String TEMPLATE_URI = "/places;reference={reference}{;name}{?maxResults}";

	public PlacesResource(List<Place> values) {
		super(values);
	}

	/**
	 * @see com.neroapp.services.resource.ResourceList#buildResourceLinks(javax.ws.rs.core.UriInfo)
	 */
	@Override
	protected void buildResourceLinks(UriInfo uriInfo) {
		MultivaluedMap<String, String> parameters = extractParameters(uriInfo);
		UriBuilder uriBuilder = UriBuilder.fromUri(TEMPLATE_URI);
		uriBuilder.matrixParam("name", parameters.containsKey("name") ? parameters.get("name").toArray() : new String[]{});
		uriBuilder.queryParam("maxResults", parameters.containsKey("maxResults") ? parameters.get("maxResults").toArray() : new String[]{});
		String uri = uriBuilder.build(parameters.get("reference").toArray()).toString();
		add(new Link(uri));
	}
	
	public static void main(String[] args) {
		Object uri = UriBuilder.fromUri(TEMPLATE_URI)
				.matrixParam("name")
				.queryParam("maxResults", "5")
				.build("xpto");
		System.out.println(uri);
	}

	public List<PlaceResource> getPlaces() {
		return resourceList();
	}
	
}
