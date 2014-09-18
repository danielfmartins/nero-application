package com.neroapp.console.services.resource;

import java.util.Locale;
import java.util.Set;

import com.neroapp.entities.User;
import com.neroapp.entities.authentication.AuthenticationSource;

public class UserResource extends Resource<User> {

	private User user;
	
	public UserResource() {
		super();
		this.user = new User();
	}

	public UserResource(User user) {
		super();
		this.user = user;
	}

	public void addAuthenticationSource(AuthenticationSource source) {
		user.addAuthenticationSource(source);
	}

	public Set<AuthenticationSource> getAuthenticationSources() {
		return user.getAuthenticationSources();
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public String getUserPreferredCountry() {
		return user.getUserPreferredCountry();
	}

	public void removeAuthenticationSource(AuthenticationSource source) {
		user.removeAuthenticationSource(source);
	}

	public void setPassword(String password) {
		user.setPassword(password);
	}

	public void setUsername(String username) {
		user.setUsername(username);
	}

	public void setUserPreferredLocale(Locale locale) {
		user.setUserPreferredLocale(locale);
	}

	public void setUserPreferredCountry(String language) {
		user.setUserPreferredCountry(language);
	}

	@Override
	public User unwrap() {
		return this.user;
	}
}