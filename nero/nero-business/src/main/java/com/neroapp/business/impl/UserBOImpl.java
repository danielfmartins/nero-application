package com.neroapp.business.impl;

import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.neroapp.business.api.UserBO;
import com.neroapp.business.exception.BOException;
import com.neroapp.entities.User;
import com.neroapp.persistence.api.DAO;

@RequestScoped
@Named("userBO")
public class UserBOImpl extends AbstractBO<User> implements UserBO {

	public UserBOImpl() {
		super();
	}
	
	@Inject
	@Named("baseDAO")
	@Override
	public void setDao(DAO<User> dao) {
		super.setDao(dao);
	}

	public User saveOrUpdate(User user) throws BOException {
		if (user.getUserPreferredCountry() == null) {
			user.setUserPreferredLocale(Locale.getDefault());
		}
		if (user.getUsername() == null) {
			throw new BOException("User without name.");
		}
		try {
			this.beginTransaction();
			User result = super.saveOrUpdate(user);
			this.commit();
			return result;
		} catch (BOException e) {
			this.rollback();
			throw e;
		}
	}

}
