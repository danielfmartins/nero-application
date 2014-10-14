package com.neroapp.producers;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import com.neroapp.annotations.Value;
import com.neroapp.resolvers.PropertyResolver;

@Dependent
public class ValueProducer {

	@Inject
	private PropertyResolver resolver;

	@Produces
	@Value
	public String getConfigValue(InjectionPoint injectPoint) {

		String fullyQualifiedfieldName = String.format("%s.%s", injectPoint
				.getMember().getDeclaringClass().getName(), injectPoint
				.getMember().getName());

		Value annotation = injectPoint.getAnnotated()
				.getAnnotation(Value.class);

		String key = annotation.value();

		boolean isKeyDefined = !key.trim().isEmpty();

		boolean valueRequired = annotation.required();

		String defaultValue = annotation.defaulValue();

		if (isKeyDefined) {
			String value = this.resolver.getValue(key, defaultValue);
			return value;
		}

		// Falling back to fully-qualified field name resolving.
		key = fullyQualifiedfieldName;

		String value = this.resolver.getValue(fullyQualifiedfieldName,
				defaultValue);

		// No luck... so perhaps just the field name?
		if (value == null) {
			key = injectPoint.getMember().getName();
			value = this.resolver.getValue(key, defaultValue);
		}

		// No can do - no value found but you've said it's required.
		if (value == null && valueRequired) {
			throw new IllegalStateException(
					String.format(
							"No value defined for field %s but field was marked as required.",
							fullyQualifiedfieldName));
		}

		return value;
	}

	@Produces
	@Value
	public Double getDoubleValue(InjectionPoint ip) {
		String value = getConfigValue(ip);
		return (value != null) ? Double.valueOf(value) : null;
	}

	@Produces
	@Value
	public Integer getIntegerValue(InjectionPoint ip) {
		String value = getConfigValue(ip);
		return (value != null) ? Integer.valueOf(value) : null;
	}

}
