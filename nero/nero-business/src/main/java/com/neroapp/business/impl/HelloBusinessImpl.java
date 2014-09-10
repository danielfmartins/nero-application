package com.neroapp.business.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.neroapp.business.api.HelloBusiness;
import com.neroapp.persistence.api.HelloDAO;

@RequestScoped
class HelloBusinessImpl implements HelloBusiness {
	
	@Inject
	private HelloDAO helloDAO;

	@Override
	public String sayHelloFor(String name) {
		return this.helloDAO.getWelcomeMessage(name);
	}

}
