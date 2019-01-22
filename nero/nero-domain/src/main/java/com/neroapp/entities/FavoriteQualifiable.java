package com.neroapp.entities;

import java.io.Serializable;


public class FavoriteQualifiable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Qualifiable qualifiable;
	private Instant instant;
	
	public FavoriteQualifiable(){
		super();
	}

	public FavoriteQualifiable(User user, Qualifiable qualifiable) {
		this();
		setUser(user);
		setQualifiable(qualifiable);
		this.instant = new Instant();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		FavoriteQualifiable other = (FavoriteQualifiable) obj;
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
		return this.getQualifiable().toString();
	}

}
