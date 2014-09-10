package com.neroapp.persistence.api;

public interface Session {

	void beginTransaction();

	void commit();

	void rollback();

}
