package com.neroapp.console.services.resource;

import java.util.ArrayList;
import java.util.List;

import com.neroapp.entities.Hashtag;

public class HashtagsResource extends Resource<List<Hashtag>> {

	private List<HashtagResource> hashtags;
	
	public HashtagsResource() {
		super();
	}

	public List<HashtagResource> getHashtags() {
		if (this.hashtags == null) {
			this.hashtags = new ArrayList<>();
		}
		return this.hashtags;
	}

	public void setHashtags(List<HashtagResource> hashtags) {
		this.hashtags = hashtags;
	}

	/**
	 * @see com.neroapp.console.services.resource.Resource#unwrap()
	 */
	@Override
	public List<Hashtag> unwrap() {
		List<Hashtag> values = new ArrayList<>();
		for(HashtagResource resource : this.getHashtags()) {
			values.add(resource.unwrap());
		}
		return values;
	}
}