package com.neroapp.entities;

import java.io.Serializable;

public class QualificationHashtag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Qualification qualification;
	private Hashtag hashtag;

	public QualificationHashtag() {
		super();
	};

	public QualificationHashtag(Qualification qualification, Hashtag hashtag) {
		this();
		this.qualification = qualification;
		this.hashtag = hashtag;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification thumbsDetail) {
		this.qualification = thumbsDetail;
	}

	public Hashtag getHashtag() {
		return hashtag;
	}

	public void setHashtag(Hashtag hashtag) {
		this.hashtag = hashtag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashtag == null) ? 0 : hashtag.hashCode());
		result = prime * result
				+ ((qualification == null) ? 0 : qualification.hashCode());
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
		QualificationHashtag other = (QualificationHashtag) obj;
		if (hashtag == null) {
			if (other.hashtag != null)
				return false;
		} else if (!hashtag.equals(other.hashtag))
			return false;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		return true;
	}

}
