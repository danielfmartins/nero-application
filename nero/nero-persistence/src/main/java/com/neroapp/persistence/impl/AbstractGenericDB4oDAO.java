package com.neroapp.persistence.impl;

import java.util.Iterator;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.neroapp.persistence.api.DAO;
import com.neroapp.persistence.api.Session;
import com.neroapp.persistence.exception.DAOException;

public abstract class AbstractGenericDB4oDAO<T> implements DAO<T> {

	protected ObjectContainer container;
	
	public AbstractGenericDB4oDAO() {
		super();
	}
	
	public AbstractGenericDB4oDAO(ObjectContainer container) {
		super();
		this.container = container;
	}
	
	public void setContainer(ObjectContainer container) {
		this.container = container;
	}

	public T saveOrUpdate(T object) throws DAOException {
		try {
			this.container.ext().store(object, Integer.MAX_VALUE);
		} catch (DatabaseClosedException dce) {
			throw new DAOException(dce);
		} catch (DatabaseReadOnlyException dro) {
			throw new DAOException(dro);
		}
		return object;
	}

	public T update(T object) throws DAOException {
		return this.saveOrUpdate(object);
	}

	public void delete(T object) throws DAOException {
		try {
			this.container.delete(object);
		} catch (DatabaseClosedException dce) {
			throw new DAOException(dce);
		} catch (DatabaseReadOnlyException dro) {
			throw new DAOException(dro);
		} catch (Db4oIOException ioe) {
			throw new DAOException(ioe);
		}
	}

	public List<T> findByExample(T template) throws DAOException {
		List<T> result;
		try {
			result = this.container.queryByExample(template);
		} catch (DatabaseClosedException dce) {
			throw new DAOException(dce);
		} catch (Db4oIOException ioe) {
			throw new DAOException(ioe);
		}
		return result;
	}

	public T findUniqueByExample(T object) throws DAOException {
		T result = null;
		List<T> resultSet = this.findByExample(object);
		if (resultSet != null) {
			Iterator<T> i = resultSet.iterator();
			if (i.hasNext()) {
				// take the unique instance
				result = i.next();
				if (i.hasNext()) {
					// if we have others, is a mistake
					throw new DAOException(
							"Find unique returns more than one instance:"
									+ result.getClass());
				}
			}
		}
		return result;
	}

	public Session getSession() {
		return new Db4oSession(this.container);
	}

}
