package com.neroapp.console.command.delegate;

import java.util.HashMap;

import com.neroapp.console.Console;
import com.neroapp.console.NeroConsole;
import com.neroapp.facade.NeroFacade;

public abstract class NeroCommandDelegate implements Runnable {

	private HashMap<Object, Object> consoleMemory;
	private NeroFacade facade;
	private Console console;
	private NeroConsole neroConsole;

	public NeroCommandDelegate(NeroConsole neroConsole) {
		if (neroConsole != null) {
			this.neroConsole = neroConsole;
			this.consoleMemory = neroConsole.getConsoleMemory();
			this.facade = neroConsole.getNeroFacade();
			this.console = neroConsole.getConsole();
		}
	}
	
	protected HashMap<Object, Object> getConsoleMemory() {
		return this.consoleMemory;
	}

	public NeroFacade getFacade() {
		return facade;
	}

	public Console getConsole() {
		return console;
	}
	
	public NeroConsole getNeroConsole() {
		return neroConsole;
	}

	public abstract void run();

}
