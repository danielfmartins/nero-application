package com.neroapp.entities.authentication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public enum AuthenticationType {

	USERNAME_PASSWORD(new UsernamePasswordAuthenticationParameterHandler(),
			"USERNAME", "PASSWORD"), 
	FACEBOOK(
			new FacebookAuthenticationParameterHandler(), "FACEBOOKID", "USERNAME", "TOKEN");

	private HashMap<String, String> authenticationParameters;
	private AuthenticationParameterHandler handler;

	public AuthenticationType getAuthenticationType() {
		return this;
	}

	public AuthenticationParameterHandler getParameterHandler() {
		return handler;
	}

	AuthenticationType(AuthenticationParameterHandler handler,
			String... parameters) {
		if (authenticationParameters == null)
			authenticationParameters = new HashMap<String, String>();
		for (String parameter : parameters)
			authenticationParameters.put(parameter, null);
		this.handler = handler;
	}

	/**
	 * Returns all the parameters names
	 * 
	 * @return
	 * @throws AuthenticationSourceException
	 */
	public Set<String> getParameters() throws AuthenticationSourceException {
		return this.authenticationParameters.keySet();
	}

	public void setParameterValue(String parameter, String value)
			throws AuthenticationSourceException {
		if (parameter == null || !this.getParameters().contains(parameter))
			throw new AuthenticationSourceException(
					"Invalid authentication source parameter:" + parameter
							+ "\n" + "Required parameters:"
							+ Arrays.toString(this.getParameters().toArray()));
		if (value == null)
			throw new AuthenticationSourceException(
					"Parameter can not be null.");
		if (authenticationParameters == null)
			authenticationParameters = new HashMap<String, String>();
		parameter.trim().toUpperCase();
		authenticationParameters.put(parameter,
				handler.handlerParameterValue(parameter, value));
	}

	/**
	 * Returns a parameter value
	 * 
	 * @param parameter
	 *            The parameter name.
	 * @return the parameter value, as String.
	 * @throws AuthenticationSourceException
	 */
	public String getParameterValue(String parameter)
			throws AuthenticationSourceException {
		if (!this.getParameters().contains(parameter))
			throw new AuthenticationSourceException(
					"Invalid authentication source parameter:" + parameter
							+ "\n" + "Required parameters:"
							+ Arrays.toString(this.getParameters().toArray()));
		return this.authenticationParameters.get(parameter);
	}

}
