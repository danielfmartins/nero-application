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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.neroapp.common.NeroException;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.Qualification.Type;
import com.neroapp.entities.User;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;
import com.neroapp.services.resource.HashtagsResource;
import com.neroapp.services.resource.MessageResource;
import com.neroapp.services.resource.PlaceResource;
import com.neroapp.services.resource.PlacesResource;
import com.neroapp.services.resource.QualificationResource;
import com.neroapp.services.resource.QualificationsResource;
import com.neroapp.services.resource.ResourceBuilder;

@Path(PlaceResource.URI)
public class PlaceService {

	@Context
	private UriInfo uriInfo;

	@Inject
	private NeroFacade facade;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response get(@PathParam("id") String id) {
		try {
			Place place = (Place) this.facade.getQualifiableById(null, id);
			if (place == null) {
				return Response
						.status(Status.NOT_FOUND)
						.entity(new MessageResource(String.format(
								"Place %s not found.", id))).build();
			}
			return Response.ok(
					ResourceBuilder.build(PlaceResource.class, place,
							this.uriInfo)).build();
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/recommendedhashtags")
	public Response getHashtags(@PathParam("id") String id,
			@MatrixParam("qualificationType") String type,
			@QueryParam("maxResults") Integer maxResultSize) {
		try {
			Qualifiable place = this.facade.getQualifiableById(null, id);
			if (place == null) {
				return Response
						.status(Status.NOT_FOUND)
						.entity(new MessageResource(String.format(
								"Place %s not found.", id))).build();
			}
			if (type == null || "".equals(type)) {
				return Response
						.status(Status.BAD_REQUEST)
						.entity(new MessageResource(
								"QualificationType is required.")).build();
			}
			Type qualificationType = null;
			try {
				qualificationType = Qualification.Type.valueOf(type);
			} catch (Exception e) {
				return Response
						.status(Status.BAD_REQUEST)
						.entity(new MessageResource(
								"Invalid qualificationType. Expected value 'POSITIVE' or 'NEGATIVE'."))
						.build();
			}
			return Response
					.ok(ResourceBuilder
							.build(HashtagsResource.class, this.facade
									.getRecommendedHashtagsFor(place,
											qualificationType.getValue()),
									this.uriInfo)).build();
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlaces(@MatrixParam("reference") String reference,
			@MatrixParam("name") String name,
			@QueryParam("maxResults") Integer maxResultSize) {
		try {
			if (reference == null || "".equals(reference)) {
				return Response.status(Status.BAD_REQUEST)
						.entity(new MessageResource("Reference is required."))
						.build();
			}
			return Response.ok(
					ResourceBuilder.build(PlacesResource.class, this.facade
							.getQualifiables(reference, name, maxResultSize),
							this.uriInfo)).build();
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/qualifications")
	public Response getQualifications(@PathParam("id") String id) {
		try {
			Place place = (Place) this.facade.getQualifiableById(null, id);
			if (place == null) {
				return Response
						.status(Status.NOT_FOUND)
						.entity(new MessageResource(String.format(
								"Place %s not found.", id))).build();
			}
			return Response.ok(
					ResourceBuilder.build(QualificationsResource.class,
							this.facade.getAllQualifications(place),
							this.uriInfo)).build();
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/qualify")
	public Response qualify(@PathParam("id") String id,
			QualificationResource resource) {
		try {
			Place place = (Place) this.facade.getQualifiableById(null, id);
			if (place == null) {
				return Response
						.status(Status.NOT_FOUND)
						.entity(new MessageResource(String.format(
								"Place %s not found.", id))).build();
			}
			User user = this.facade.findUserByName(resource.getUsername());
			if (user == null) {
				return Response
						.status(Status.BAD_REQUEST)
						.entity(new MessageResource(String.format(
								"Invalid user.", id))).build();
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
