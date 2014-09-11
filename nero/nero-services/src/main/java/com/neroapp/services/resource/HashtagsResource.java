package com.neroapp.services.resource;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import com.neroapp.entities.Hashtag;

public class HashtagsResource extends ResourceList<HashtagResource, Hashtag> {

	public static final String TEMPLATE_URI = "/places/{id}/recommendedhashtags;qualificationType={qualificationType}{?maxResults}";

	public HashtagsResource(List<Hashtag> values, Map<String, Object> parameters) {
		super(values);
		URI uri = UriBuilder.fromUri(TEMPLATE_URI).buildFromMap(parameters);
		add(new Link(uri.toString()));
	}

	public List<HashtagResource> getHashtags() {
		return this.resourceList();
	}

}
