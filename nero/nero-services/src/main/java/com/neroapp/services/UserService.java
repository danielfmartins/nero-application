package com.neroapp.services;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.neroapp.common.exceptions.NeroException;
import com.neroapp.common.exceptions.NotFoundExpcetion;
import com.neroapp.entities.User;
import com.neroapp.facade.NeroFacade;
import com.neroapp.resources.AuthenticationTokenResource;
import com.neroapp.resources.ResourceBuilderManager;
import com.neroapp.resources.UserResource;

@Path(UserResource.URI)
public class UserService {

	@Context
	private UriInfo uriInfo;

	@Inject
	private NeroFacade facade;

	@Inject
	private ResourceBuilderManager resourceBuilderManager;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(
			@NotEmpty(message = "{parameter.required.username}") @FormParam("username") String username,
			@NotEmpty(message = "{parameter.required.password}") @FormParam("password") String password,
			@Length(min = 2, message = "{parameter.invalid.language}") @FormParam("language") String language) {
		try {
			return Response
					.status(Status.CREATED)
					.entity(this.resourceBuilderManager.build(
							UserResource.class,
							this.facade.registerNewUser(username, language),
							this.uriInfo)).build();
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResource get(@PathParam("username") String username) {
		try {
			User user = this.getUser(username);
			return this.resourceBuilderManager.build(UserResource.class, user,
					this.uriInfo);
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	private User getUser(String username) throws NeroException {
		User user = this.facade.findUserByName(username);
		if (user == null) {
			throw new NotFoundExpcetion(String.format("User %s not found.",
					username));
		}
		return user;
	}

	@POST
	@Path("authentication")
	@Produces(MediaType.APPLICATION_JSON)
	public AuthenticationTokenResource login(
			@NotEmpty(message = "{parameter.required.username}") @FormParam("username") String username,
			@NotEmpty(message = "{parameter.required.password}") @FormParam("password") String password) {
		try {
			User user = this.getUser(username);
			return new AuthenticationTokenResource(user.getUsername());
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

}
