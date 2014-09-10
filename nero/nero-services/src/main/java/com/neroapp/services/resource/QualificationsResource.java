package com.neroapp.services.resource;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.Qualification;

public class QualificationsResource extends
		ResourceList<QualificationResource, Qualification> {

	public static final String TEMPLATE_URI = "/places/{id}/qualifications";

	public QualificationsResource(List<Qualification> values, Map<String, Object> parameters) {
		super(values);
		URI uri = UriBuilder.fromUri(TEMPLATE_URI).buildFromMap(parameters);
		add(new Link(uri.toString()));
	}

	public List<QualificationResource> getQualifications() {
		return resourceList();
	}
}
