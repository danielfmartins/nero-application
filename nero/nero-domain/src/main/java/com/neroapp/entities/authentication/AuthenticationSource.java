package com.neroapp.entities.authentication;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Defines an authentication source an user may have.
 * @author fm
 *
 */
public class AuthenticationSource {
	
	public static AuthenticationSource get(AuthenticationType type) {
		return new AuthenticationSource(type);
	}
	
	private AuthenticationType authenticationType;
	private HashMap<String,String> authenticationData;
	
	private AuthenticationSource(AuthenticationType type) {
		this.authenticationType = type;
	}
	
	/**
	 * Define a value to a parameter
	 * @param parameter
	 * @param value
	 * @throws AuthenticationSourceException
	 */
	public void setParameterValue(String parameter, String value)
			throws AuthenticationSourceException {
		if (parameter == null || !authenticationType.getParameters().contains(parameter))
			throw new AuthenticationSourceException(
					"Invalid authentication source parameter:" + parameter
							+ "\n" + "Required parameters:"
							+ Arrays.toString(authenticationType.getParameters().toArray()));
		if (value == null)
			throw new AuthenticationSourceException(
					"Parameter can not be null.");
		if (authenticationData == null)
			authenticationData = new HashMap<String, String>();
		parameter.trim().toUpperCase();
		authenticationData.put(parameter,
				authenticationType.getParameterHandler().handlerParameterValue(parameter, value));
	}
	
	/**
	 * Returns a parameter value
	 * @param parameter The parameter name.
	 * @return the parameter value, as String.
	 * @throws AuthenticationSourceException
	 */
	public String getParameterValue(String parameter)
			throws AuthenticationSourceException {
		if (!authenticationType.getParameters().contains(parameter))
			throw new AuthenticationSourceException(
					"Invalid authentication parameter:" + parameter
							+ "\n" + "Required parameters:"
							+ Arrays.toString(this.authenticationType.getParameters().toArray()));
		return authenticationData.get(parameter);
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((authenticationType == null) ? 0 : authenticationType
						.hashCode());
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
		AuthenticationSource other = (AuthenticationSource) obj;
		if (authenticationType != other.authenticationType)
			return false;
		return true;
	}

}
