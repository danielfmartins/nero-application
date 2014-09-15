package com.neroapp.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neroapp.services.resource.ResourceBuilder;
import com.neroapp.services.resource.RootResource;

@Path(RootResource.URI)
public class RootService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RootResource get() {
		return ResourceBuilder.build(RootResource.class);
	}
}