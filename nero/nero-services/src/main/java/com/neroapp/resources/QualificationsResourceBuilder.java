package com.neroapp.resources;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.Qualification;
import com.neroapp.services.uri.NeroUriBuilder;
import com.neroapp.services.uri.UriParameters;

@RequestScoped
@Named("qualificationsResourceBuilder")
public class QualificationsResourceBuilder implements ResourceBuilder {

	@Override
	public boolean supports(Class<? extends Resource<?>> resourceType) {
		return QualificationsResource.class == resourceType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Resource<?> build(Object value, UriInfo uriInfo) {
		List<Qualification> listValues = (List<Qualification>) value;
		QualificationsResource resource = new QualificationsResource(listValues);
		UriParameters uriParameters = UriParameters.builder().path("id")
				.build();
		URI uri = NeroUriBuilder.build(QualificationsResource.TEMPLATE_URI,
				uriInfo, uriParameters);
		resource.add(new Link(uri.toString()));
		return resource;
	}

}
