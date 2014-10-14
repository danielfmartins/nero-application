package com.neroapp.services.config;

import java.util.Arrays;

import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.server.validation.ValidationConfig;
import org.glassfish.jersey.server.validation.internal.InjectingConstraintValidatorFactory;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;

public class ValidationConfigurationContextResolver implements
		ContextResolver<ValidationConfig> {

	@Context
	private ResourceContext resourceContext;

	@Override
	public ValidationConfig getContext(Class<?> type) {
		final ValidationConfig config = new ValidationConfig();
		config.messageInterpolator(new ResourceBundleMessageInterpolator(
				new AggregateResourceBundleLocator(Arrays.asList(
						"ValidationMessages", "messages"))));
		config.constraintValidatorFactory(resourceContext
				.getResource(InjectingConstraintValidatorFactory.class));
		return config;
	}

}
