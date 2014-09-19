package com.neroapp.services.resource;

import javax.ws.rs.core.UriInfo;

import com.neroapp.entities.Hashtag;

public class HashtagResource extends Resource {

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
		return hashtag.getHashtag();
	}

	public String getDefinitionLocaleCountry() {
		return hashtag.getDefinitionLocaleCountry();
	}

	public Long getTotalUse() {
		return hashtag.getTotalUse();
	}

	/**
	 * @see com.neroapp.services.resource.Resource#buildLinks(javax.ws.rs.core.UriInfo)
	 */
	@Override
	public void buildLinks(UriInfo uriInfo) {
		// Implementado para não fazer nada, pois não há links para este
		// recurso.		
	}
}
