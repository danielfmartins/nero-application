package com.neroapp.console;

import java.util.HashMap;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.neroapp.common.exceptions.NeroException;
import com.neroapp.console.command.NeroCommand;
import com.neroapp.console.command.NeroCommandChain;
import com.neroapp.entities.User;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;

@ApplicationScoped
public class NeroConsole {

	public static final Integer SELECTED_LATITUDE_LONGITUDE = 0;
	public static final Integer PLACES = 1;

	@Inject
	private NeroFacade facade;
	
	private HashMap<Object, Object> consoleMemory;
	private NeroCommand commandChain;
	private Console console;
	private User currentUser;

	public NeroConsole() {
	}
	
	public void startApp() {
		defineMemory();
		this.console = Console.getConsole();
		init();
		commandChain = NeroCommandChain.getCommandChain(this);
		commandChain.run();
	}
	
	private void init() {
		console.println("BEM VINDO AO CONSOLE NEROApp");
		console.println("\n");
		String username = console.readLine("Entre com seu nome de usuario:");
		User user;
		try {
			user = facade.findUserByName(username);
			if (user == null) {
				user = facade.registerNewUser(username, Locale.getDefault()
						.getLanguage());
				console.println("Usuário novo registrado.");
			} else {
				console.println("Usuário encontrado.");
			}
			this.currentUser = user;
		} catch (NeroException e) {
			console.println("Problemas recuperando o usuario.");
			e.printStackTrace();
			this.currentUser = null;
		}
	}

	public NeroFacade getNeroFacade() {
		return this.facade;
	}

	private void defineMemory() {
		if (consoleMemory == null) {
			consoleMemory = new HashMap<Object, Object>();
		}
		consoleMemory.put(NeroConsole.SELECTED_LATITUDE_LONGITUDE, "");
		consoleMemory.put(NeroConsole.PLACES, new HashMap<Long, Place>());
	}

	public HashMap<Object, Object> getConsoleMemory() {
		if (this.consoleMemory == null) {
			this.consoleMemory = new HashMap<Object, Object>();
		}
		return this.consoleMemory;
	}

	public Console getConsole() {
		return console;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
}
