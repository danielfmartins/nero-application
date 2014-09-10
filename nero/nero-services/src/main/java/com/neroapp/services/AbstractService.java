package com.neroapp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractService {

	protected Map<String, Object> extractParameters(UriInfo uriInfo) {
		Map<String, Object> parameters = new HashMap<>();
		if (!uriInfo.getPathParameters().isEmpty()) {
			for (Entry<String, List<String>> entry : uriInfo
					.getPathParameters(true).entrySet()) {
				putValues(parameters, entry);
			}
		}

		if (!uriInfo.getPathSegments().isEmpty()) {
			for (PathSegment pathSegment : uriInfo.getPathSegments(true)) {
				for (Entry<String, List<String>> entry : pathSegment
						.getMatrixParameters().entrySet()) {
					putValues(parameters, entry);
				}
			}
		}

		if (!uriInfo.getQueryParameters().isEmpty()) {
			for (Entry<String, List<String>> entry : uriInfo
					.getQueryParameters(true).entrySet()) {
				putValues(parameters, entry);
			}
		}

		return parameters;
	}

	private void putValues(Map<String, Object> parameters,
			Entry<String, List<String>> entry) {
		List<String> values = entry.getValue();
		parameters.put(entry.getKey(), values != null ? String.valueOf(values)
				.replaceAll("\\[", "").replaceAll("\\]", "") : "");
	}
	
}
