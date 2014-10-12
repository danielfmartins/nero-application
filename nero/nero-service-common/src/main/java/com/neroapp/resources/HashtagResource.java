package com.neroapp.resources;

import com.neroapp.entities.Hashtag;

public class HashtagResource extends Resource<Hashtag> {

	private Hashtag hashtag;
	
	public HashtagResource() {
		super();
		this.hashtag = new Hashtag();
	}

	public HashtagResource(Hashtag hashtag) {
		super();
		this.hashtag = hashtag;
	}

	public String getHashtag() {
		return this.hashtag.getHashtag();
	}

	public String getDefinitionLocaleCountry() {
		return this.hashtag.getDefinitionLocaleCountry();
	}

	public Long getTotalUse() {
		return this.hashtag.getTotalUse();
	}

	@Override
	public Hashtag unwrap() {
		return this.hashtag;
	}

}
