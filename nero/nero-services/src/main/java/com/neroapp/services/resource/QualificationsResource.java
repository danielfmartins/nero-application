package com.neroapp.services.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.Qualification;

public class QualificationsResource extends
		ResourceList<QualificationResource, Qualification> {

	public static final String TEMPLATE_URI = "/places/{id}/qualifications";

	public QualificationsResource(List<Qualification> values) {
		super(values);
	}
	
	/**
	 * @see com.neroapp.services.resource.ResourceList#buildResourceLinks(javax.ws.rs.core.UriInfo)
	 */
	@Override
	protected void buildResourceLinks(UriInfo uriInfo) {
		URI uri = this.buildURI(TEMPLATE_URI, uriInfo);
		add(new Link(uri.toString()));
	}

	public List<QualificationResource> getQualifications() {
		return resourceList();
	}
}
