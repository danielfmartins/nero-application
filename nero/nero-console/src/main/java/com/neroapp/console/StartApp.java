package com.neroapp.console;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class StartApp {

	public static void main(String[] args) {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		NeroConsole neroConsole = container.instance()
				.select(NeroConsole.class).get();
		neroConsole.startApp();
		weld.shutdown();
	}
}
