package com.neroapp.services.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.Hashtag;
import com.neroapp.services.uri.NeroUriBuilder;
import com.neroapp.services.uri.UriParameters;

public class HashtagsResource extends ResourceList<HashtagResource, Hashtag> {

	public static final String TEMPLATE_URI = "/places/{id}/recommendedhashtags;qualificationType={qualificationType}{?maxResults}";
	
	public HashtagsResource() {
		super(null);
	}

	public HashtagsResource(List<Hashtag> values) {
		super(values);
	}

	@Override
	protected void buildResourceLinks(UriInfo uriInfo) {
		UriParameters uriParameters = UriParameters.builder()
				.path("id", "qualificationType").query("maxResults").build();
		URI uri = NeroUriBuilder.build(TEMPLATE_URI, uriInfo, uriParameters);
		add(new Link(uri.toString()));
	}

	public List<HashtagResource> getHashtags() {
		return this.resourceList();
	}
}
