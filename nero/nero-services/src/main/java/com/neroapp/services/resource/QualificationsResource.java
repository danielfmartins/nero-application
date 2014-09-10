package com.neroapp.services.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.Qualification;

public class QualificationsResource extends
		ResourceList<QualificationResource, Qualification> {

	public static final String TEMPLATE_URI = "/places/{id}/qualifications";

	public QualificationsResource(UriInfo uriInfo, List<Qualification> values) {
		super(values);
		URI uri = UriBuilder.fromUri(TEMPLATE_URI).buildFromMap(this.extractParameters(uriInfo));
		add(new Link(uri.toString()));
	}

	public List<QualificationResource> getQualifications() {
		return resourceList();
	}
}
