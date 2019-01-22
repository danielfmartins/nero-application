package com.neroapp.entities;
import java.io.Serializable;
import java.util.Locale;


public class HashtagTranslation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Hashtag hashtag;
	private String localeCountry;
	private String hashTagTranslation;
	private Long totalVotes;
	
	public HashtagTranslation() {
		super();
	}
	
	public HashtagTranslation(Hashtag hashtag, Locale locale,
			String hashTagTranslation) {
		this();
		this.hashtag = hashtag;
		this.localeCountry = locale.getCountry();
		this.hashTagTranslation = hashTagTranslation;
	}

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

	public String getHashTagTranslation() {
		return hashTagTranslation;
	}

	public void setHashTagTranslation(String hashTagTranslation) {
		this.hashTagTranslation = hashTagTranslation;
	}

	public Long getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((hashTagTranslation == null) ? 0 : hashTagTranslation
						.hashCode());
		result = prime * result + ((hashtag == null) ? 0 : hashtag.hashCode());
		result = prime * result + ((localeCountry == null) ? 0 : localeCountry.hashCode());
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
		HashtagTranslation other = (HashtagTranslation) obj;
		if (hashTagTranslation == null) {
			if (other.hashTagTranslation != null)
				return false;
		} else if (!hashTagTranslation.equals(other.hashTagTranslation))
			return false;
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
		return true;
	}

	@Override
	public String toString() {
		return this.hashTagTranslation;
	}
}
