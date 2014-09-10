package com.neroapp.services.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/*")
public class AppResourceConfiguration extends ResourceConfig {

	public AppResourceConfiguration() {
		super();
		this.init();
	}

	public void init() {
		this.packages("com.neroapp.services").register(JacksonFeature.class);
	}

}
