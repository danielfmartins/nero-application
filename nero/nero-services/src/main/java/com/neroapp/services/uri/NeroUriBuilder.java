package com.neroapp.services.uri;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public class NeroUriBuilder {
	
	private NeroUriBuilder() {
		super();
	}

	private static void addMatrixParameters(UriBuilder uriBuilder,
			MultivaluedMap<String, String> parameters,
			List<String> matrixParameters) {
		for (String key : matrixParameters) {
			List<String> values = parameters.get(key);
			if (values != null) {
				uriBuilder.matrixParam(key, values.toArray());
			} else {
				uriBuilder.matrixParam(key);
			}
		}
	}

	private static void addQueryParameters(UriBuilder uriBuilder,
			MultivaluedMap<String, String> parameters,
			List<String> queryParameters) {
		for (String key : queryParameters) {
			List<String> values = parameters.get(key);
			if (values != null) {
				uriBuilder.queryParam(key, values.toArray());
			} else {
				uriBuilder.queryParam(key);
			}
		}
	}

	public static URI build(String uriTemplate, UriInfo uriInfo,
			UriParameters uriParameters) {
		MultivaluedMap<String, String> parameters = extractParameters(uriInfo);

		UriBuilder uriBuilder = UriBuilder.fromUri(uriTemplate);

		addMatrixParameters(uriBuilder, parameters,
				uriParameters.getMatrixParameters());

		addQueryParameters(uriBuilder, parameters,
				uriParameters.getQueryParameters());

		List<String> pathParameters = uriParameters.getPathParameters();
		List<String> pathValues = new ArrayList<>();

		for (String key : pathParameters) {
			List<String> values = parameters.get(key);
			if (values != null) {
				pathValues.addAll(values);
			}
		}
		return uriBuilder.build(pathValues.toArray());
	}

	private static MultivaluedMap<String, String> extractParameters(
			UriInfo uriInfo) {
		MultivaluedMap<String, String> parameters = new MultivaluedHashMap<>();
		if (!uriInfo.getPathParameters().isEmpty()) {
			parameters.putAll(uriInfo.getPathParameters(true));
		}

		if (!uriInfo.getPathSegments().isEmpty()) {
			for (PathSegment pathSegment : uriInfo.getPathSegments(true)) {
				parameters.putAll(pathSegment.getMatrixParameters());
			}
		}

		if (!uriInfo.getQueryParameters().isEmpty()) {
			parameters.putAll(uriInfo.getQueryParameters(true));
		}

		return parameters;
	}

}
