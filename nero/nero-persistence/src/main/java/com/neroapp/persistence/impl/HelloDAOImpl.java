package com.neroapp.persistence.impl;

import javax.enterprise.context.RequestScoped;

import com.neroapp.persistence.api.HelloDAO;

@RequestScoped
class HelloDAOImpl implements HelloDAO {

	@Override
	public String getWelcomeMessage(String name) {
		return String.format(
				"Welcome %s, this is a JAX-RS Jersey Application", name);
	}

}
