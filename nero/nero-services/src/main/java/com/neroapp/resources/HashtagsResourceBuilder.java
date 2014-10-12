package com.neroapp.resources;

import java.net.URI;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.Hashtag;
import com.neroapp.services.uri.NeroUriBuilder;
import com.neroapp.services.uri.UriParameters;

@RequestScoped
@Named("hashtagsResourceBuilder")
public class HashtagsResourceBuilder implements ResourceBuilder {

	@Override
	public boolean supports(Class<? extends Resource<?>> resourceType) {
		return HashtagsResource.class == resourceType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Resource<?> build(Object value, UriInfo uriInfo) {
		List<Hashtag> listValues = (List<Hashtag>) value;
		HashtagsResource resource = new HashtagsResource(listValues);
		UriParameters uriParameters = UriParameters.builder()
				.path("id", "qualificationType").query("maxResults").build();
		URI uri = NeroUriBuilder.build(HashtagsResource.TEMPLATE_URI, uriInfo,
				uriParameters);
		resource.add(new Link(uri.toString()));
		return resource;
	}

}
