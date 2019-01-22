package com.neroapp.entities.summary;

import java.io.Serializable;

import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;

public class HashtagTotalNegativeHitByYear implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Qualifiable qualifiable;
	private Hashtag hashtag;
	private Integer year;
	private Long hits;

	public HashtagTotalNegativeHitByYear() {
		super();
	}

	public HashtagTotalNegativeHitByYear(Integer year, Qualifiable qualifiable, Hashtag hashtag,
			Long hits) {
		this();
		this.setYear(year);
		this.setQualifiable(qualifiable);
		this.setHashtag(hashtag);
		this.setHits(hits);
	}

	public Qualifiable getQualifiable() {
		return qualifiable;
	}

	public Hashtag getHashtag() {
		return hashtag;
	}

	public Integer getYear() {
		return year;
	}

	public long getHits() {
		return hits;
	}

	public void setQualifiable(Qualifiable qualifiable) {
		this.qualifiable = qualifiable;
	}

	public void setHashtag(Hashtag hashtag) {
		this.hashtag = hashtag;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}
	
	public void addHit() {
		this.hits++;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashtag == null) ? 0 : hashtag.hashCode());
		result = prime * result + ((qualifiable == null) ? 0 : qualifiable.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		HashtagTotalNegativeHitByYear other = (HashtagTotalNegativeHitByYear) obj;
		if (hashtag == null) {
			if (other.hashtag != null)
				return false;
		} else if (!hashtag.equals(other.hashtag))
			return false;
		if (qualifiable == null) {
			if (other.qualifiable != null)
				return false;
		} else if (!qualifiable.equals(other.qualifiable))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return hits.toString();
	}
}
