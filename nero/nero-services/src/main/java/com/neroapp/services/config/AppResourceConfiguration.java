package com.neroapp.services.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("/*")
public class AppResourceConfiguration extends ResourceConfig {

	public AppResourceConfiguration() {
		super();
		this.init();
	}

	public void init() {
		this.packages("com.neroapp.services")
				.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
				.register(ApiListingResource.class)
				.register(SwaggerSerializers.class)
				.register(ValidationConfigurationContextResolver.class)
				.register(JacksonFeature.class);
		
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/*");
        beanConfig.setResourcePackage("com.neroapp.services");
        beanConfig.setScan(true);
	}
}
