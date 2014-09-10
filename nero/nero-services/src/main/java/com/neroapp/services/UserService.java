package com.neroapp.services;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neroapp.common.NeroException;
import com.neroapp.facade.NeroFacade;
import com.neroapp.services.resource.UserResource;

@Path("/users")
public class UserService extends AbstractService {

	@Inject
	private NeroFacade facade;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public UserResource create(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("language") String language) {
		try {
			// TODO armazenar a senha do usuário. E o
			return new UserResource(this.facade.registerNewUser(username, language));
		} catch (NeroException e) {
			throw new RuntimeException(e);
		}
	}

	public String login(String username, String password) {
		return username;
	}

}
