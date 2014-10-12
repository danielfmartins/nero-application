package com.neroapp.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.places.Place;

@RequestScoped
@Named("placeResourceBuilder")
public class PlaceResourceBuilder extends AbstractPlaceResourceBuilder {
	
	@Override
	public boolean supports(Class<? extends Resource<?>> resourceType) {
		return (PlaceResource.class == resourceType);
	}
	
	@Override
	public Resource<?> build(Object value, UriInfo uriInfo) {
		Place place = (Place) value;
		PlaceResource resource = new PlaceResource(place);
		this.addLinks(resource, uriInfo);
		return resource;
	}

}
