package com.neroapp.console.services.resource;

import java.util.Locale;

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

	public String getDefinitionLocaleCountry() {
		return this.hashtag.getDefinitionLocaleCountry();
	}

	public String getHashtag() {
		return this.hashtag.getHashtag();
	}

	public Long getTotalUse() {
		return this.hashtag.getTotalUse();
	}

	public void setDefinitionLocaleCountry(Locale locale) {
		this.hashtag.setDefinitionLocaleCountry(locale);
	}

	public void setHashtag(String hashtag) {
		this.hashtag.setHashtag(hashtag);
	}

	public void setTotalUse(Long totalUse) {
		this.hashtag.setTotalUse(totalUse);
	}

	/**
	 * @see com.neroapp.console.services.resource.Resource#unwrap()
	 */
	@Override
	public Hashtag unwrap() {
		return this.hashtag;
	}
}