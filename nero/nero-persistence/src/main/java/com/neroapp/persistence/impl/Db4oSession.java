package com.neroapp.persistence.impl;

import com.db4o.ObjectContainer;
import com.neroapp.persistence.api.Session;

public class Db4oSession implements Session {
	
	private ObjectContainer container;
	
	public Db4oSession(ObjectContainer container) {
		this.container = container;
	}

	@Override
	public void beginTransaction() {
		// do Nothing
	}

	@Override
	public void commit() {
		container.commit();
	}

	@Override
	public void rollback() {
		container.rollback();
	}

}
