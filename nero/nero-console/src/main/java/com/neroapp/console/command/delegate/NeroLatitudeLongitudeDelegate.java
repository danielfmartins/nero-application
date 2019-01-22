package com.neroapp.console.command.delegate;

import com.neroapp.console.Console;
import com.neroapp.console.NeroConsole;

public class NeroLatitudeLongitudeDelegate extends NeroCommandDelegate {

	public NeroLatitudeLongitudeDelegate(NeroConsole neroConsole) {
		super(neroConsole);
	}

	@Override
	public void run() {
		Console console = this.getConsole();
		console.println("");
		console.println("DEFININDO LATITUDE E LONGITUDE");
		String latitude = console.readLine("Latitude:");
		String longitude = console.readLine("Longitude:");
		this.getConsoleMemory().put(NeroConsole.SELECTED_LATITUDE_LONGITUDE,
				latitude + "," + longitude);
		console.println("Lat/Long definido para :"
				+ this.getConsoleMemory().get(
						NeroConsole.SELECTED_LATITUDE_LONGITUDE));
	}

}
