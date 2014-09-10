package com.neroapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.neroapp.entities.authentication.AuthenticationSource;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String userPreferredCountry;
	private Set<AuthenticationSource> authenticationSources;

	public User() {
		super();
		this.authenticationSources = new HashSet<AuthenticationSource>();
	}

	public User(String name) {
		this();
		this.setUsername(name);
	}

	public User(String username, String password, String userPreferredCountry) {
		super();
		this.username = username;
		this.password = password;
		this.userPreferredCountry = userPreferredCountry;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserPreferredCountry() {
		return userPreferredCountry;
	}

	public void setUserPreferredCountry(Locale locale) {
		this.userPreferredCountry = locale.getCountry();
	}
	
	public void setUserPreferredCountry(String language) {
		this.userPreferredCountry = language;
	}

	public void addAuthenticationSource(AuthenticationSource source) {
		if (this.authenticationSources == null)
			this.authenticationSources = new HashSet<AuthenticationSource>();
		this.authenticationSources.add(source);
	}

	public void removeAuthenticationSource(AuthenticationSource source) {
		if (this.authenticationSources != null)
			this.authenticationSources.remove(source);
	}

	public Set<AuthenticationSource> getAuthenticationSources() {
		return this.authenticationSources;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return username;
	}

}
