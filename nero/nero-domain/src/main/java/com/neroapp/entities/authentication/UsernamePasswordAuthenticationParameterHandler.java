package com.neroapp.entities.authentication;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class UsernamePasswordAuthenticationParameterHandler implements AuthenticationParameterHandler {

	/**
	 * Handle parameters USERNAME and PASSWORD
	 */
	@Override
	public String handlerParameterValue(String parameter, String value)
			throws AuthenticationSourceException {
		String result = value;
		if ("USERNAME".equals(parameter.trim().toUpperCase())) result = handleUserName(value);
		if ("PASSWORD".equals(parameter.trim().toUpperCase())) result = handlePassword(value);
		return result;
	}
	
	private String handleUserName(String value) {
		return value.trim();
	}
	
	private String handlePassword(String value) throws AuthenticationSourceException {
		String result = null;
		value.trim();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(value.getBytes("UTF-8"));
			result = new String(md.digest());
			result = new String(Hex.encodeHex(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			throw new AuthenticationSourceException("Invalid algorithm on AuthenticationSourceUsernamePassword.\n"+e);
		} catch (UnsupportedEncodingException e) {
			throw new AuthenticationSourceException(e);
		}
		return result;
	}
}