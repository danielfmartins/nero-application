package com.neroapp.resources;

import java.util.List;

import com.neroapp.entities.Hashtag;

public class HashtagsResource extends ResourceList<Hashtag, HashtagResource> {

	public static final String TEMPLATE_URI = "/places/{id}/recommendedhashtags;qualificationType={qualificationType}{?maxResults}";
	
	public HashtagsResource() {
		super(null);
	}

	public HashtagsResource(List<Hashtag> values) {
		super(values);
	}

	public List<HashtagResource> getHashtags() {
		return this.resourceList();
	}
}
