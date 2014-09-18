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

import com.neroapp.common.NeroException;
import com.neroapp.entities.User;
import com.neroapp.facade.NeroFacade;
import com.neroapp.services.resource.AuthenticationTokenResource;
import com.neroapp.services.resource.MessageResource;
import com.neroapp.services.resource.ResourceBuilder;
import com.neroapp.services.resource.UserResource;

@Path(UserResource.URI)
public class UserService {

	@Context
	private UriInfo uriInfo;

	@Inject
	private NeroFacade facade;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public UserResource create(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("language") String language) {
		try {
			return ResourceBuilder.build(UserResource.class,
					this.facade.registerNewUser(username, language), this.uriInfo);
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("username") String username) {
		try {
			// TODO usuário não encontrado??? Deve ser lançada uma exceção com
			// status http 404
			User user = this.facade.findUserByName(username);
			if (user == null) {
				return Response
						.status(Status.NOT_FOUND)
						.entity(new MessageResource(String.format(
								"User %s not found.", username))).build();
			}
			return Response.ok(
					ResourceBuilder.build(UserResource.class, user,
							this.uriInfo)).build();
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	@POST
	@Path("authentication")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("username") String username,
			@FormParam("password") String password) {
		try {
			User user = this.facade.findUserByName(username);
			if (user == null) {
				return Response
						.status(Status.UNAUTHORIZED)
						.entity(new MessageResource(
								"Usuário e ou senha inválidos.")).build();
			}
			return Response.ok(new AuthenticationTokenResource(username))
					.build();
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

}
