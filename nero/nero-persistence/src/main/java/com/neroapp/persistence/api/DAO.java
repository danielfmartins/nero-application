package com.neroapp.persistence.api;

import java.util.List;

import com.neroapp.persistence.exception.DAOException;

public interface DAO<T> {

	T saveOrUpdate(T object) throws DAOException;

	T update(T object) throws DAOException;

	void delete(T object) throws DAOException;

	List<T> findByExample(T object) throws DAOException;
	
	T findUniqueByExample(T object) throws DAOException;

	Session getSession();

}
