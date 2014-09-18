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

import org.glassfish.jersey.jackson.JacksonFeature;

import com.neroapp.common.NeroException;
import com.neroapp.console.services.resource.PlacesResource;
import com.neroapp.console.services.resource.QualificationsResource;
import com.neroapp.console.services.resource.UserResource;
import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.User;
import com.neroapp.entities.authentication.AuthenticationType;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;

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
			return response.readEntity(UserResource.class).unwrap();
		} catch (Exception e) {
			throw new NeroException(e);
		}
	}

	@Override
	public List<Qualification> getAllQualifications(Qualifiable qualifiable)
			throws NeroException {
		List<Qualification> qualifications = ClientBuilder.newClient()
				.target(PLACES_URI).path(String.valueOf(qualifiable.getId()))
				.path("qualifications")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get(QualificationsResource.class).unwrap();
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
		List<Place> places = ClientBuilder.newClient()
				.register(JacksonFeature.class).target(PLACES_URI)
				.matrixParam("reference", referenceForQualifiables)
				.matrixParam("name", qualifiableName)
				.queryParam("maxResults", resultSetSizeLimit)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get(PlacesResource.class).unwrap();
		return places;
	}

	@Override
	public List<Hashtag> getRecommendedHashtagsFor(Qualifiable qualifiable,
			Boolean isPositiveQualification) throws NeroException {
		//TODO request for recommendedHashtags
		return null;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setPreferredQualifiableForUser(User user,
			Qualifiable qualifiable) throws NeroException {
		// TODO Auto-generated method stub

	}

}
