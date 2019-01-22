package com.neroapp.entities.authentication;

/**
 * Defines objects that may handle the parameters for a certain AuthenticationSource
 * @author fm
 *
 */
public interface AuthenticationParameterHandler {
	
	// handle parameter to guarantee its compliance with handler requirements
	String handlerParameterValue(String parameter, String value) throws AuthenticationSourceException;

}
