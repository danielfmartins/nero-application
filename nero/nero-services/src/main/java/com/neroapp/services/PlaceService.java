package com.neroapp.services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Min;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.hibernate.validator.constraints.NotEmpty;

import com.neroapp.common.exceptions.BadRequestException;
import com.neroapp.common.exceptions.NeroException;
import com.neroapp.common.exceptions.NotFoundExpcetion;
import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.User;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;
import com.neroapp.resources.HashtagsResource;
import com.neroapp.resources.PlaceResource;
import com.neroapp.resources.PlacesResource;
import com.neroapp.resources.QualificationResource;
import com.neroapp.resources.QualificationsResource;
import com.neroapp.resources.ResourceBuilderManager;
import com.neroapp.services.validation.constraints.Expected;

@Path(PlaceResource.URI)
public class PlaceService {

	@Context
	private UriInfo uriInfo;

	@Inject
	private NeroFacade facade;

	@Inject
	private ResourceBuilderManager resourceBuilderManager;

	private Place getPlace(String id) throws NeroException {
		Place place = (Place) this.facade.getQualifiableById(null, id);
		if (place == null) {
			throw new NotFoundExpcetion(
					String.format("Place %s not found.", id));
		}
		return place;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public PlaceResource get(@PathParam("id") String id) {
		try {
			Place place = this.getPlace(id);
			return this.resourceBuilderManager.build(PlaceResource.class,
					place, this.uriInfo);
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/recommendedhashtags")
	public HashtagsResource getHashtags(
			@PathParam("id") String id,
			@NotEmpty(message = "{parameter.required.qualificationType}") @Expected(message = "{parameter.expected.qualificationType}", values = {
					"POSITIVE", "NEGATIVE" }) @MatrixParam("qualificationType") String type,
			@Min(message = "{parameter.invalid.maxResults}", value = 1) @QueryParam("maxResults") Integer maxResultSize) {
		try {
			Place place = this.getPlace(id);
			List<Hashtag> hashtags = this.facade.getRecommendedHashtagsFor(
					place, Qualification.Type.valueOf(type).getValue());
			if (maxResultSize != null && hashtags != null) {
				if (maxResultSize > hashtags.size()) {
					maxResultSize = hashtags.size();
				}
				hashtags = hashtags.subList(0, maxResultSize);
			}
			return this.resourceBuilderManager.build(HashtagsResource.class,
					hashtags, this.uriInfo);
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PlacesResource getPlaces(
			@NotEmpty(message = "{parameter.required.reference}") @MatrixParam("reference") String reference,
			@MatrixParam("name") String name,
			@Min(message = "{parameter.invalid.maxResults}", value = 1) @QueryParam("maxResults") Integer maxResultSize) {
		try {
			return this.resourceBuilderManager
					.build(PlacesResource.class, this.facade.getQualifiables(
							reference, name, maxResultSize), this.uriInfo);
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/qualifications")
	public QualificationsResource getQualifications(@PathParam("id") String id) {
		try {
			Place place = this.getPlace(id);
			return this.resourceBuilderManager.build(
					QualificationsResource.class,
					this.facade.getAllQualifications(place), this.uriInfo);
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/qualify")
	public Response qualify(@PathParam("id") String id,
			QualificationResource resource) {
		try {
			Place place = this.getPlace(id);
			User user = this.facade.findUserByName(resource.getUsername());
			if (user == null) {
				throw new BadRequestException(String.format(
						"Invalid user '%s'.", resource.getUsername()));
			}
			Qualification qualification = resource.unwrap();
			qualification.setQualifiable(place);
			qualification.setUser(user);
			this.facade.registerQualification(qualification);
			return Response.status(Status.CREATED).build();
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

}
