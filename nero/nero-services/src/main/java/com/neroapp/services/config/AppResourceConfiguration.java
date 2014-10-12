package com.neroapp.services.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.neroapp.services.validation.ValidationConfigurationContextResolver;

@ApplicationPath("/*")
public class AppResourceConfiguration extends ResourceConfig {

	public AppResourceConfiguration() {
		super();
		this.init();
	}

	public void init() {
		this.packages("com.neroapp.services")
				.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
				.register(ValidationConfigurationContextResolver.class)
				.register(JacksonFeature.class);
	}

}
