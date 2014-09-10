package com.neroapp.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.neroapp.common.NeroException;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;
import com.neroapp.services.resource.PlaceResource;
import com.neroapp.services.resource.PlacesResource;
import com.neroapp.services.resource.QualificationResource;
import com.neroapp.services.resource.QualificationsResource;

@Path(PlaceResource.URI)
public class PlaceService {
	
	@Context
	private UriInfo uriInfo;

	@Inject
	private NeroFacade facade;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PlacesResource getPlaces(@MatrixParam("reference") String reference,
			@MatrixParam("name") String name,
			@QueryParam("maxResults") Integer maxResultSize) {
		try {
			return new PlacesResource(this.uriInfo, this.facade.getQualifiables(reference,
					name, maxResultSize));
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public PlaceResource get(@PathParam("id") String id) {
		try {
			return new PlaceResource((Place) this.facade.getQualifiableById(null, id));
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/qualifications")
	public QualificationsResource getQualifications(@PathParam("id") String id) {
		try {
			//TODO lançar NOT FOUND CASO PLACE NAO SEJA ENCONTRADO
			Place place = (Place) this.facade.getQualifiableById(null, id);
			return new QualificationsResource(this.uriInfo, this.facade.getAllQualifications(place));
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/qualify")
	public void qualify(@PathParam("id") String id, QualificationResource resource ) {
		try {
			//TODO lançar NOT FOUND CASO PLACE NAO SEJA ENCONTRADO
			Place place = (Place) this.facade.getQualifiableById(null, id);
			//TODO obter o usuario pelo nome e setar no qualificaçao. Primeiro criar o serviço de usuário.
			Qualification qualification = resource.unwrap();
			qualification.setQualifiable(place);
			this.facade.registerQualification(qualification);
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

}
