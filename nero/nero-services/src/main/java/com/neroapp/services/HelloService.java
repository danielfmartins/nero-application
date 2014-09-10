package com.neroapp.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neroapp.business.api.HelloBusiness;

@Path("hello")
public class HelloService {

	@Inject
	private HelloBusiness helloBusiness;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Welcome, this is a JAX-RS Jersey Application";
	}

	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> sayHelloFor(@PathParam("name") String name) {
		Map<String, String> messages = new HashMap<>();
		messages.put("message", this.helloBusiness.sayHelloFor(name));
		return messages;
	}

}
