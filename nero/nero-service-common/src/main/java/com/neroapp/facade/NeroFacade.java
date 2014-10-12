package com.neroapp.facade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.neroapp.common.exceptions.NeroException;
import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.User;
import com.neroapp.entities.authentication.AuthenticationType;
import com.neroapp.entities.places.Place;

/**
 * Interface that defines all services available for Nero-based applications
 * 
 * @author fm
 * 
 */
public interface NeroFacade {

	/*
	 * TODO
	 */
	User checkExistingUser(String username,
			AuthenticationType authenticationType,
			HashMap<String, String> parameterNameValue) throws NeroException;

	User findUserByName(String userName) throws NeroException;

	User registerNewUser(String username, String language) throws NeroException;

	/**
	 * Get Nero qualifications for the specified qualifiable.
	 * 
	 * @param place
	 * @return
	 * @throws NeroException
	 */
	List<Qualification> getAllQualifications(Qualifiable qualifiable)
			throws NeroException;

	/**
	 * Register a qualification
	 * 
	 * @param newQualification
	 * @throws NeroException
	 */
	void registerQualification(Qualification newQualification)
			throws NeroException;

	/**
	 * 
	 * 
	 * @param qualifiable
	 * @param isPositiveQualification
	 * @return
	 * @throws NeroException
	 */
	List<Hashtag> getRecommendedHashtagsFor(Qualifiable qualifiable,
			Boolean isPositiveQualification) throws NeroException;

	/**
	 * Gets all qualifiables related to this reference
	 * 
	 * @param referenceForQualifiables
	 *            where to search for the qualifiables.
	 * @param qualifiableName
	 *            Name restriction for the qualifiable (beginning with)
	 * @param resultSetSizeLimit
	 *            To limit the resultset size
	 * @return
	 * @throws NeroException
	 */
	List<Place> getQualifiables(Serializable referenceForQualifiables,
			String qualifiableName, Integer resultSetSizeLimit)
			throws NeroException;

	/**
	 * Gets a specific qualifiable by its Id.
	 * 
	 * @param referenceForQualifiables
	 * @param qualifiableId
	 * @return
	 * @throws NeroException
	 */
	Qualifiable getQualifiableById(Serializable referenceForQualifiables,
			Serializable qualifiableId) throws NeroException;

	void setPreferredQualifiableForUser(User user, Qualifiable qualifiable)
			throws NeroException;

	Boolean isPreferredQualifiableForUser(User user, Qualifiable qualifiable)
			throws NeroException;

	Long getTotalPreferredQualifiable(Qualifiable qualifiable)
			throws NeroException;

}