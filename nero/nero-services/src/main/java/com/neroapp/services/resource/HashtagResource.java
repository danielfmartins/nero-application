package com.neroapp.services.resource;

import com.neroapp.entities.Hashtag;

public class HashtagResource extends Resource {

	private Hashtag hashtag;

	public HashtagResource(Hashtag hashtag) {
		super();
		this.hashtag = hashtag;
	}

	public String getHashtag() {
		return hashtag.getHashtag();
	}

	public String getDefinitionLocaleCountry() {
		return hashtag.getDefinitionLocaleCountry();
	}

	public Long getTotalUse() {
		return hashtag.getTotalUse();
	}
}
