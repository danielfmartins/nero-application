package com.neroapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Represents the details of a qualification made by some user
 * 
 * @author fm
 * 
 */
public class Qualification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Qualifiable qualifiable;
	private Instant instant;
	private Boolean positiveQualification = null;
	private String comment;
	private Set<Hashtag> hashtags = new HashSet<>();

	public Qualification() {
		super();
	}

	public Qualification(User user, Qualifiable qualifiable, Boolean positiveQualification) {
		this();
		this.user = user;
		this.qualifiable = qualifiable;
		this.instant = new Instant();
		this.positiveQualification = positiveQualification;
	}

	public Qualification(User currentUser, Qualifiable qualifiable,
			Boolean isPositiveQualification, String comment) {
		this(currentUser, qualifiable, isPositiveQualification);
		this.setComment(comment);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Qualifiable getQualifiable() {
		return qualifiable;
	}

	public void setQualifiable(Qualifiable qualifiable) {
		this.qualifiable = qualifiable;
	}

	public Instant getInstant() {
		return instant;
	}
	
	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean isPositive() {
		return getPositiveQualification() == true;
	}

	public Boolean getPositiveQualification() {
		return positiveQualification;
	}

	public String getQualificationType() {
		return (isPositive() ? "POSITIVE" : "NEGATIVE");

	}

	public void setPositiveQualification(Boolean thumbsUp) {
		this.positiveQualification = thumbsUp;
	}

	public Set<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(Set<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	public String getHashTagsStringArray() {
		StringBuilder result = new StringBuilder("");
		for (Hashtag hashtag : hashtags) {
			result.append("#").append(hashtag.getHashtag()).append(" ");
		}
		return result.toString();
	}
	
	public List<String> getHashtagsValues() {
		List<String> hashtagList = new ArrayList<>();
		String value = this.getHashTagsStringArray();
		if (value != null && !"".equals(value.trim())) {
			hashtagList.addAll(Arrays.asList(value.trim().split(" ")));
		}
		return hashtagList;
	}
	
	public void setHashtagsValues(List<String> hashtagList) {
		if (hashtagList != null) {
			for (String value : hashtagList) {
				this.hashtags.add(new Hashtag(value.replaceFirst("#", ""),
						Locale.getDefault()));
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instant == null) ? 0 : instant.hashCode());
		result = prime * result + ((qualifiable == null) ? 0 : qualifiable.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Qualification other = (Qualification) obj;
		if (instant == null) {
			if (other.instant != null)
				return false;
		} else if (!instant.equals(other.instant))
			return false;
		if (qualifiable == null) {
			if (other.qualifiable != null)
				return false;
		} else if (!qualifiable.equals(other.qualifiable))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QualificationDetail [user=" + user + ", qualifiable=" + qualifiable
				+ ", instant=" + instant + ", thumbsUp="
				+ positiveQualification + ", comment=" + comment + "]";
	}

}
