package com.neroapp.services.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.Qualification;

public class QualificationsResource extends
		ResourceList<QualificationResource, Qualification> {

	public static final String TEMPLATE_URI = "/places/{id}/qualifications";

	public QualificationsResource(List<Qualification> values, Map<String, Object> parameters) {
		super(values);
		String uri = resolveTemplate(parameters);
		add(new Link(uri.toString()));
	}

	public List<QualificationResource> getQualifications() {
		return resourceList();
	}
	
	public static String resolveTemplate(Map<?, ?> parameters) {
		String template = TEMPLATE_URI;
		if (parameters != null) {
			if (parameters.containsKey("id")) {
				template = template.replace("{id}", String.valueOf(parameters.get("id")));
			}
		}
		return UriBuilder.fromUri(template).toTemplate();
	}
}
