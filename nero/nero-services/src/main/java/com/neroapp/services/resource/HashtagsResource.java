package com.neroapp.services.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.Hashtag;

public class HashtagsResource extends ResourceList<HashtagResource, Hashtag> {

	public static final String TEMPLATE_URI = "/places/{id}/recommendedhashtags;qualificationType={qualificationType}{?maxResults}";

	public HashtagsResource(List<Hashtag> values) {
		super(values);
	}

	@Override
	protected void buildResourceLinks(UriInfo uriInfo) {
		URI uri = this.buildURI(TEMPLATE_URI, uriInfo);
		add(new Link(uri.toString()));
	}

	public List<HashtagResource> getHashtags() {
		return this.resourceList();
	}
}
