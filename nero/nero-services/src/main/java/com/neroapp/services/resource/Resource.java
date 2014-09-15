package com.neroapp.services.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public abstract class Resource {

	private List<Link> links = new ArrayList<Link>();

	protected Resource() {
		super();
	}

	protected void add(Link link) {
		this.links.add(link);
	}

	private void addMatrixParameters(UriBuilder uriBuilder, UriInfo uriInfo) {
		if (!uriInfo.getPathSegments().isEmpty()) {
			for (PathSegment pathSegment : uriInfo.getPathSegments(true)) {
				for (Entry<String, List<String>> entry : pathSegment
						.getMatrixParameters().entrySet()) {
					List<String> values = entry.getValue();
					if (values != null) {
						for (String value : values) {
							uriBuilder.matrixParam(entry.getKey(), value);
						}
					} else {
						uriBuilder.matrixParam(entry.getKey());
					}
				}
			}
		}
	}

	private void addQueryParameters(UriBuilder uriBuilder, UriInfo uriInfo) {
		if (!uriInfo.getQueryParameters().isEmpty()) {
			for (Entry<String, List<String>> entry : uriInfo
					.getQueryParameters(true).entrySet()) {
				List<String> values = entry.getValue();
				if (values != null) {
					for (String value : values) {
						uriBuilder.queryParam(entry.getKey(), value);
					}
				} else {
					uriBuilder.queryParam(entry.getKey());
				}
			}
		}
	}

	public abstract void buildLinks(UriInfo uriInfo);

	protected URI buildURI(String uriTemplate, UriInfo uriInfo, String...keys) {
		UriBuilder uriBuilder = UriBuilder.fromUri(uriTemplate);
		
		addMatrixParameters(uriBuilder, uriInfo);
		addQueryParameters(uriBuilder, uriInfo);

		if (!uriInfo.getPathParameters().isEmpty()) {
			for (Entry<String, List<String>> entry : uriInfo.getPathParameters(
					true).entrySet()) {
				List<String> values = entry.getValue();
				if (values != null) {
					return uriBuilder.build(values.toArray());
				}
			}
		}
		
		return uriBuilder.build();
//		List<String> values = new ArrayList<>();
//		MultivaluedMap<String, String> params = this.extractParameters(uriInfo);
//		if (keys == null || keys.length == 0) {
//			for (Entry<String, List<String>> entry : params.entrySet()) {
//				values.addAll(entry.getValue());
//			}
//		} else {
//			for (String key : keys) {
//				if (params.containsKey(key)) {
//					values.addAll(params.get(key));
//				}
//			}
//		}
		
//		return uriBuilder.build(values.toArray());
	}

	public List<Link> getLinks() {
		return links;
	}
	
	protected MultivaluedMap<String, String> extractParameters(UriInfo uriInfo) {
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