package com.neroapp.business.api;

import java.util.List;

import com.neroapp.business.exception.BOException;

public interface BO<T> {

	void beginTransaction();

	void commit();

	void rollback();

	T saveOrUpdate(T object) throws BOException;

	T update(T object) throws BOException;

	void delete(T object) throws BOException;

	List<T> findByExample(T object) throws BOException;
	
	T findUniqueByExample(T object) throws BOException;

}