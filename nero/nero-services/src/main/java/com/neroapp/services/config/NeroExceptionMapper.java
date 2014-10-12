package com.neroapp.services.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.neroapp.common.exceptions.AbstractHttpException;
import com.neroapp.resources.MessageResource;

@Provider
public class NeroExceptionMapper implements
		ExceptionMapper<AbstractHttpException> {

	@Override
	public Response toResponse(AbstractHttpException e) {
		return Response.status(e.getStatusCode())
				.entity(new MessageResource(e.getMessage())).build();
	}

}
