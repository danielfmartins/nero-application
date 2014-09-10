package com.neroapp.business.facade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.neroapp.business.api.BO;
import com.neroapp.business.api.QualificationBO;
import com.neroapp.business.api.UserBO;
import com.neroapp.business.exception.BOException;
import com.neroapp.business.impl.BOFactory;
import com.neroapp.common.NeroException;
import com.neroapp.entities.FavoriteQualifiable;
import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.User;
import com.neroapp.entities.authentication.AuthenticationType;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;

public abstract class NeroFacadeImpl implements NeroFacade {

	protected BOFactory boFactory;
	
	public NeroFacadeImpl() {
		super();
	}
	
	public NeroFacadeImpl(BOFactory boFactory) {
		super();
		this.boFactory = boFactory;
	}
	
	public void setBoFactory(BOFactory boFactory) {
		this.boFactory = boFactory;
	}

	/*
	 * TODO
	 */
	@Override
	public User checkExistingUser(String username,
			AuthenticationType authenticationType,
			HashMap<String, String> parameterNameValue) {
		return null;
	}

	@Override
	public User findUserByName(String userName) {
		UserBO bo = boFactory.getUserBO();
		User user = null, result = null;
		user = new User();
		user.setUsername(userName);
		try {
			result = bo.findUniqueByExample(user);
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User registerNewUser(String username, String language) {
		UserBO bo = boFactory.getUserBO();
		User user = new User();
		try {
			user.setUsername(username);
			List<User> users = bo.findByExample(user);
			if (users != null && !users.isEmpty()) {
				user = users.get(0);
			}
			user.setUserPreferredCountry(language);
			bo.saveOrUpdate(user);
		} catch (BOException e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Get Nero qualifications for the specified place.
	 * 
	 * @param place
	 * @return
	 * @throws NeroException 
	 */
	@Override
	public List<Qualification> getAllQualifications(Qualifiable qualifiable) throws NeroException {
		List<Qualification> result = null;
		QualificationBO bo = boFactory.getQualificationBO();
		Qualification dummy = new Qualification();
		dummy.setQualifiable(qualifiable);
		try {
			result = bo.findByExample(dummy);
		} catch (BOException e) {
			e.printStackTrace();
			throw new NeroException(e);
		}
		//result = bo.findByQualifiableId((qualifiable.getId()));
		return result;
	}

	@Override
	public void registerQualification(Qualification newQualification) {
		// Registrar as hashtags, se não existirem e as atualiza na
		// Qualificacao.
		QualificationBO bo = boFactory.getQualificationBO();
		try {
			bo.saveOrUpdate(newQualification);
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Hashtag> getRecommendedHashtagsFor(Qualifiable qualifiable,
			Boolean isPositiveQualification) throws NeroException {
		QualificationBO qbo = boFactory.getQualificationBO();
		return qbo.getTopXHashtagsForPlace(qualifiable, isPositiveQualification, 10);
	}

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
	@Override
	public abstract List<Place> getQualifiables(Serializable referenceForQualifiables,
			String qualifiableName, Integer resultSetSizeLimit)
			throws NeroException;
	
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Qualifiable getQualifiableById(
			Serializable referenceForQualifiables, Serializable qualifiableId)
			throws NeroException {
		BO<Place> bo = boFactory.getDefaultBO();
		Place dummyPlace = null, place = null;
		dummyPlace = new Place();
		String placeId = (String) qualifiableId;
		dummyPlace.setId(placeId);
		try {
			place = bo.findUniqueByExample(dummyPlace);
			return place;
		} catch (BOException e) {
			e.printStackTrace();
			throw new NeroException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setPreferredQualifiableForUser(User user,
			Qualifiable qualifiable) throws NeroException{
		BO<FavoriteQualifiable> bo = boFactory.getDefaultBO();
		FavoriteQualifiable dummy = null, favorite = null;
		dummy = new FavoriteQualifiable();
		dummy.setQualifiable(qualifiable);
		dummy.setUser(user);
		try {
			favorite = (FavoriteQualifiable) bo.findUniqueByExample(dummy);
			if (favorite!=null) {
				favorite = new FavoriteQualifiable(user,qualifiable);
				bo.saveOrUpdate(favorite);
			}
		} catch (BOException e) {
			e.printStackTrace();
			throw new NeroException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean isPreferredQualifiableForUser(User user,
			Qualifiable qualifiable) throws NeroException {
		boolean result = false;
		BO<FavoriteQualifiable> bo = boFactory.getDefaultBO();
		FavoriteQualifiable dummy = null, favorite = null;
		dummy = new FavoriteQualifiable();
		dummy.setQualifiable(qualifiable);
		dummy.setUser(user);
		try {
			favorite = (FavoriteQualifiable) bo.findUniqueByExample(dummy);
			if (favorite!=null) {
				result = true;
			}
		} catch (BOException e) {
			e.printStackTrace();
			throw new NeroException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getTotalPreferredQualifiable(Qualifiable qualifiable) throws NeroException {
		Long result = 0l;
		BO<FavoriteQualifiable> bo = boFactory.getDefaultBO();
		List<FavoriteQualifiable> list = null;
		FavoriteQualifiable dummy = null;
		dummy = new FavoriteQualifiable();
		dummy.setQualifiable(qualifiable);
		try {
			list = bo.findByExample(dummy);
			if (list!=null && !list.isEmpty()) {
				result = (long) list.size();
			}
		} catch (BOException e) {
			e.printStackTrace();
			throw new NeroException(e);
		}
		return result;
	}
}
