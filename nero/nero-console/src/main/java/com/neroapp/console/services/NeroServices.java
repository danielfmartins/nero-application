package com.neroapp.console.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.neroapp.common.exceptions.NeroException;
import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.User;
import com.neroapp.entities.authentication.AuthenticationType;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;
import com.neroapp.resources.HashtagsResource;
import com.neroapp.resources.MessageResource;
import com.neroapp.resources.PlacesResource;
import com.neroapp.resources.QualificationResource;
import com.neroapp.resources.QualificationsResource;
import com.neroapp.resources.UserResource;

@Dependent
@Named("neroServices")
public class NeroServices implements NeroFacade {

	public static final String USERS_URI = "http://localhost:8080/nero-services/users";
	public static final String PLACES_URI = "http://localhost:8080/nero-services/places";

	@Override
	public User checkExistingUser(String username,
			AuthenticationType authenticationType,
			HashMap<String, String> parameterNameValue) throws NeroException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.neroapp.facade.NeroFacade#findUserByName(java.lang.String)
	 */
	@Override
	public User findUserByName(String userName) throws NeroException {
		try {
			Response response = ClientBuilder.newClient()
					.register(JacksonFeature.class).target(USERS_URI)
					.path(userName).request(MediaType.APPLICATION_JSON_TYPE)
					.get();
			if (Status.NOT_FOUND.getStatusCode() == response.getStatus()) {
				return null;
			}

			if (Status.Family.SUCCESSFUL != Status.Family.familyOf(response
					.getStatus())) {
				throw new NeroException(response.hasEntity() ? response
						.readEntity(MessageResource.class).toString()
						: response.toString());
			}
			return response.readEntity(UserResource.class).unwrap();
		} catch (Exception e) {
			throw new NeroException(e);
		}
	}

	@Override
	public List<Qualification> getAllQualifications(Qualifiable qualifiable)
			throws NeroException {
		Response response = ClientBuilder.newClient().target(PLACES_URI)
				.path(String.valueOf(qualifiable.getId()))
				.path("qualifications")
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		if (Status.Family.SUCCESSFUL != Status.Family.familyOf(response
				.getStatus())) {
			throw new NeroException(response.hasEntity() ? response.readEntity(
					MessageResource.class).toString() : response.toString());
		}
		List<Qualification> qualifications = response.readEntity(
				QualificationsResource.class).unwrap();
		return qualifications;
	}

	@Override
	public Qualifiable getQualifiableById(
			Serializable referenceForQualifiables, Serializable qualifiableId)
			throws NeroException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Place> getQualifiables(Serializable referenceForQualifiables,
			String qualifiableName, Integer resultSetSizeLimit)
			throws NeroException {
		Response response = ClientBuilder.newClient()
				.register(JacksonFeature.class).target(PLACES_URI)
				.matrixParam("reference", referenceForQualifiables)
				.matrixParam("name", qualifiableName)
				.queryParam("maxResults", resultSetSizeLimit)
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		if (Status.Family.SUCCESSFUL != Status.Family.familyOf(response
				.getStatus())) {
			throw new NeroException(response.hasEntity() ? response.readEntity(
					MessageResource.class).toString() : response.toString());
		}
		List<Place> places = response.readEntity(PlacesResource.class).unwrap();
		return places;
	}

	@Override
	public List<Hashtag> getRecommendedHashtagsFor(Qualifiable qualifiable,
			Boolean isPositiveQualification) throws NeroException {
		Response response = ClientBuilder
				.newClient()
				.register(JacksonFeature.class)
				.target(PLACES_URI)
				.path(String.valueOf(qualifiable.getId()))
				.path("recommendedhashtags")
				.matrixParam("qualificationType",
						isPositiveQualification ? "POSITIVE" : "NEGATIVE")
				.request(MediaType.APPLICATION_JSON_TYPE).get();

		if (Status.Family.SUCCESSFUL != Status.Family.familyOf(response
				.getStatus())) {
			throw new NeroException(response.hasEntity() ? response.readEntity(
					MessageResource.class).toString() : response.toString());
		}
		List<Hashtag> hashtags = response.readEntity(HashtagsResource.class)
				.unwrap();
		return hashtags;
	}

	@Override
	public Long getTotalPreferredQualifiable(Qualifiable qualifiable)
			throws NeroException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isPreferredQualifiableForUser(User user,
			Qualifiable qualifiable) throws NeroException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.neroapp.facade.NeroFacade#registerNewUser(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public User registerNewUser(String username, String language)
			throws NeroException {
		try {
			User user = ClientBuilder
					.newClient()
					.register(JacksonFeature.class)
					.target(USERS_URI)
					.request(MediaType.APPLICATION_JSON_TYPE)
					.post(Entity.entity(new Form().param("username", username)
							.param("language", language),
							MediaType.APPLICATION_FORM_URLENCODED_TYPE),
							UserResource.class).unwrap();
			return user;
		} catch (Exception e) {
			throw new NeroException(e);
		}
	}

	@Override
	public void registerQualification(Qualification newQualification)
			throws NeroException {
		String id = String.valueOf(newQualification.getQualifiable().getId());
		Response response = ClientBuilder
				.newClient()
				.register(JacksonFeature.class)
				.target(PLACES_URI)
				.path(id)
				.path("qualify")
				.request()
				.post(Entity.entity(
						new QualificationResource(newQualification),
						MediaType.APPLICATION_JSON_TYPE));

		Family family = Status.Family.familyOf(response.getStatus());
		if (Status.Family.SUCCESSFUL != family) {
			throw new NeroException(response.hasEntity() ? response.readEntity(
					MessageResource.class).toString() : response.toString());
		}
	}

	@Override
	public void setPreferredQualifiableForUser(User user,
			Qualifiable qualifiable) throws NeroException {
		// TODO Auto-generated method stub

	}

}
