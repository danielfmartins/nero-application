package com.neroapp.persistence;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.db4o.ObjectContainer;
import com.neroapp.persistence.impl.AbstractGenericDB4oDAO;

@RequestScoped
@Named("baseDAO")
@SuppressWarnings("rawtypes")
class DB4oBaseDAO extends AbstractGenericDB4oDAO implements BaseDAO {

	public DB4oBaseDAO() {
		super();
	}
	
	@Inject
	@Override
	public void setContainer(ObjectContainer container) {
		super.setContainer(container);
	}
}
