package com.neroapp.entities;

import java.io.Serializable;
import java.util.Locale;

public class Hashtag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The hashtag (without any starting #)
	 */
	private String hashtag;

	/**
	 * Locale used in hashtag definition
	 */
	private String definitionLocaleCountry;

	/**
	 * How many times this hashtag was used
	 */
	private Long totalUse = 0L;

	public Hashtag() {
		super();
	}

	public Hashtag(String hashtag) {
		this();
		this.setHashtag(hashtag);
	}

	public Hashtag(String hashtag, Locale locale) {
		super();
		this.hashtag = hashtag;
		if (locale != null) {
			this.definitionLocaleCountry = locale.getCountry();
		}
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getDefinitionLocaleCountry() {
		return definitionLocaleCountry;
	}
	
	public void setDefinitionLocaleCountry(Locale locale) {
		this.definitionLocaleCountry = locale.getCountry();
	}

	public Long getTotalUse() {
		return totalUse;
	}

	public void setTotalUse(Long totalUse) {
		this.totalUse = totalUse;
	}
	
	public void incTotalUse() {
		if (this.totalUse==null) this.totalUse = 0L;
		this.totalUse++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashtag == null) ? 0 : hashtag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hashtag other = (Hashtag) obj;
		if (hashtag == null) {
			if (other.hashtag != null)
				return false;
		} else if (!hashtag.equals(other.hashtag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getHashtag();
	}

}
