package com.neroapp.services.uri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class UriParameters {

	public static final class UriParametersBuilder {

		private UriParameters uriParameters;

		private UriParametersBuilder() {
			super();
			this.uriParameters = new UriParameters();
		}

		public UriParameters build() {
			return this.uriParameters;
		}

		public UriParametersBuilder matrix(String... parameters) {
			this.uriParameters.put(UriParameterType.MATRIX, parameters);
			return this;
		}

		public UriParametersBuilder path(String... parameters) {
			this.uriParameters.put(UriParameterType.PATH, parameters);
			return this;
		}

		public UriParametersBuilder query(String... parameters) {
			this.uriParameters.put(UriParameterType.QUERY, parameters);
			return this;
		}
	}

	public static UriParametersBuilder builder() {
		return new UriParametersBuilder();
	}

	private Map<UriParameterType, List<String>> parameters;

	private UriParameters() {
		super();
		this.parameters = new HashMap<>();
	}

	public List<String> getMatrixParameters() {
		List<String> values = this.parameters.get(UriParameterType.MATRIX);
		if (values != null) {
			return values;
		}
		return Collections.emptyList();
	}

	public List<String> getPathParameters() {
		List<String> values = this.parameters.get(UriParameterType.PATH);
		if (values != null) {
			return values;
		}
		return Collections.emptyList();
	}

	public List<String> getQueryParameters() {
		List<String> values = this.parameters.get(UriParameterType.QUERY);
		if (values != null) {
			return values;
		}
		return Collections.emptyList();
	}

	private void put(UriParameterType type, String[] parameters) {
		List<String> values = this.parameters.get(type);
		if (values == null) {
			this.parameters.put(type, (parameters != null) ? new ArrayList<>(
					Arrays.asList(parameters)) : new ArrayList<String>());
		} else if (parameters != null) {
			values.addAll(Arrays.asList(parameters));
		}
	}
}
