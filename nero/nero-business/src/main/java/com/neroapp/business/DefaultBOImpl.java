package com.neroapp.business;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.neroapp.business.impl.AbstractBO;
import com.neroapp.persistence.api.DAO;

@RequestScoped
@Named("defaultBO")
@SuppressWarnings("rawtypes")
class DefaultBOImpl extends AbstractBO implements DefaultBO {
	
	public DefaultBOImpl() {
		super();
	}
	
	@Inject
	@Named("baseDAO")
	@Override
	@SuppressWarnings("unchecked")
	public void setDao(DAO dao) {
		super.setDao(dao);
	}

}
