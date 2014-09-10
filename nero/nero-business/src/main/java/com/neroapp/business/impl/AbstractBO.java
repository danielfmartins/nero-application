package com.neroapp.business.impl;

import java.util.List;

import com.neroapp.business.api.BO;
import com.neroapp.business.exception.BOException;
import com.neroapp.persistence.api.DAO;
import com.neroapp.persistence.exception.DAOException;

public abstract class AbstractBO<T> implements BO<T> {
	
	protected DAO<T> dao;
	
	public AbstractBO() {
		super();
	}
	
	public AbstractBO(DAO<T> dao) {
		this.dao = dao;
	}
	
	public void setDao(DAO<T> dao) {
		this.dao = dao;
	}
	
	public T saveOrUpdate(T object) throws BOException {
		T result = null;
		try {
			result = this.dao.saveOrUpdate(object);
		} catch (DAOException e) {
			throw new BOException(e);
		}
		return result;
	}

	public void delete(T object) throws BOException {
		try {
			this.dao.delete(object);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	public List<T> findByExample(T object) throws BOException {
		List<T> result = null;
		try {
			result = this.dao.findByExample(object);
		} catch (DAOException e) {
			throw new BOException(e);
		}
		return result;
	}

	public T findUniqueByExample(T object) throws BOException {
		T result = null;
		try {
			result = this.dao.findUniqueByExample(object);
		} catch (DAOException e) {
			throw new BOException(e);
		}
		return result;
	}

	public T update(T object) throws BOException {
		T result = null;
		try {
			result = this.dao.update(object);
		} catch (DAOException e) {
			throw new BOException(e);
		}
		return result;
	}

	public void beginTransaction() {
		this.dao.getSession().beginTransaction();
	}

	public void commit() {
		this.dao.getSession().commit();
	}

	public void rollback() {
		this.dao.getSession().rollback();
	}
}
