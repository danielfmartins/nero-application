package com.neroapp.entities;

import java.io.Serializable;

public class HashtagTranslationVote implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private User votingUser;
	private Instant timestamp;
	private HashtagTranslation translation;
	
	public User getVotingUser() {
		return votingUser;
	}
	public Instant getTimestamp() {
		return timestamp;
	}
	public HashtagTranslation getTranslation() {
		return translation;
	}
	public void setVotingUser(User votingUser) {
		this.votingUser = votingUser;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public void setTranslation(HashtagTranslation translation) {
		this.translation = translation;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((translation == null) ? 0 : translation.hashCode());
		result = prime * result
				+ ((votingUser == null) ? 0 : votingUser.hashCode());
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
		HashtagTranslationVote other = (HashtagTranslationVote) obj;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		if (votingUser == null) {
			if (other.votingUser != null)
				return false;
		} else if (!votingUser.equals(other.votingUser))
			return false;
		return true;
	}

}
