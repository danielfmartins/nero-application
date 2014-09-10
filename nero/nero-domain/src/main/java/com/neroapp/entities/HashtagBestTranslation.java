package com.neroapp.entities;

import java.io.Serializable;
import java.util.Locale;

/**
 * The translation with more votes in a certain locale
 * 
 * @author fm
 * 
 */
public class HashtagBestTranslation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Hashtag hashtag;
	private String localeCountry;
	private String translation;



	public Hashtag getHashtag() {
		return hashtag;
	}

	public void setHashtag(Hashtag hashtag) {
		this.hashtag = hashtag;
	}

	public String getLocaleCountry() {
		return localeCountry;
	}

	public void setLocaleCountry(Locale locale) {
		this.localeCountry = locale.getCountry();
	}

	public void setLocaleCountry(String country) {
		this.localeCountry = country;
	}
	

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashtag == null) ? 0 : hashtag.hashCode());
		result = prime * result
				+ ((localeCountry == null) ? 0 : localeCountry.hashCode());
		result = prime * result
				+ ((translation == null) ? 0 : translation.hashCode());
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
		HashtagBestTranslation other = (HashtagBestTranslation) obj;
		if (hashtag == null) {
			if (other.hashtag != null)
				return false;
		} else if (!hashtag.equals(other.hashtag))
			return false;
		if (localeCountry == null) {
			if (other.localeCountry != null)
				return false;
		} else if (!localeCountry.equals(other.localeCountry))
			return false;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.translation;
	}

}
