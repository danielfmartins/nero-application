package com.neroapp.console.services.resource;

import java.util.List;

import com.neroapp.entities.Instant;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.User;

public class QualificationResource extends Resource<Qualification> {

	private Qualification qualification;

	public QualificationResource() {
		super();
		this.qualification = new Qualification();
	}

	public QualificationResource(Qualification qualification) {
		super();
		this.qualification = qualification;
	}

	public String getUsername() {
		User user = this.qualification.getUser();
		if (user != null) {
			return user.getUsername();
		}
		return null;
	}

	public void setUsername(String username) {
		this.qualification.setUser(new User(username));
	}

	public String getInstant() {
		Instant instant = this.qualification.getInstant();
		if (instant != null) {
			return instant.toString();
		}
		return null;
	}

	public void setInstant(String source) {
		this.qualification.setInstant(new Instant(source));
	}

	public String getComment() {
		return this.qualification.getComment();
	}

	public void setComment(String comment) {
		this.qualification.setComment(comment);
	}

	public boolean isPositive() {
		return this.qualification.isPositive();
	}

	public void setPositive(boolean positive) {
		this.qualification.setPositiveQualification(Boolean.valueOf(positive));
	}

	public List<String> getHashtags() {
		return this.qualification.getHashtagsValues();
	}

	public void setHashtags(List<String> hashtags) {
		this.qualification.setHashtagsValues(hashtags);
	}

	/**
	 * @see com.neroapp.console.services.resource.Resource#unwrap()
	 */
	public Qualification unwrap() {
		return this.qualification;
	}

}