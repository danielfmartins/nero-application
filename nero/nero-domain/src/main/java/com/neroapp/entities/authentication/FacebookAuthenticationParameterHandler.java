package com.neroapp.entities.authentication;

import java.util.Arrays;
import java.util.Set;


public class FacebookAuthenticationParameterHandler implements AuthenticationParameterHandler {

	@Override
	public String handlerParameterValue(String parameter, String value) throws AuthenticationSourceException {
		Set<String> parameters = AuthenticationType.FACEBOOK.getParameters();
		if (!parameters.contains(parameter)) throw new AuthenticationSourceException(
				"Invalid authentication source parameter:" + parameter
				+ "\n" + "Required parameters:"
				+ Arrays.toString(parameters.toArray()));
		return value.trim();
	}
}