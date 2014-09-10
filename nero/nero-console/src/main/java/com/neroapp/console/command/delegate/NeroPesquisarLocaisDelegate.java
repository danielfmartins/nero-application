package com.neroapp.console.command.delegate;

import java.util.HashMap;
import java.util.List;

import com.neroapp.common.NeroException;
import com.neroapp.console.Console;
import com.neroapp.console.NeroConsole;
import com.neroapp.console.command.NeroCommand;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;

public class NeroPesquisarLocaisDelegate extends NeroCommandDelegate {

	private Console console = this.getConsole();
	private NeroFacade facade = this.getFacade();
	private String placeName;
	private Boolean askForName = false;

	public NeroPesquisarLocaisDelegate(NeroConsole neroConsole) {
		super(neroConsole);
		this.console = NeroCommand.console;
	}

	public NeroPesquisarLocaisDelegate(NeroConsole neroConsole, Boolean askName) {
		this(neroConsole);
		this.askForName = askName;
	}

	@Override
	public void run() {
		showHeader();
		if (this.askForName)
			askForAName();
		presentPlacesList();
		console.println("Memorize o número de um local para poder fazer as demais operacoes");
		console.readLine("Pressione <ENTER> para continuar");
	}

	private void askForAName() {
		this.placeName = console
				.readLine("Entre com um trecho do nome do local:");
		this.placeName.trim();
		if (this.placeName.length() == 0)
			this.placeName = null;
	}

	private void showHeader() {
		console.println("PESQUISA DE LOCAIS");
		console.println("");
	}

	private void presentPlacesList() {
		HashMap<Integer, Place> memoryPlaces = new HashMap<Integer, Place>();
		List<Place> places;
		String ll = (String) this.getConsoleMemory().get(NeroConsole.SELECTED_LATITUDE_LONGITUDE);
		try {
			places = facade.getQualifiables(ll, placeName, null);
			Integer i = 1;
			if (places != null) {
				for (Place q : places) {
					Place place = q;
					console.println(i + ")" + "\t" + place.toDetailedString());
					memoryPlaces.put(i, place);
					i++;
				}
				this.getConsoleMemory().put(NeroConsole.PLACES, memoryPlaces);
			}
		} catch (NeroException e) {
			e.printStackTrace();
		}
	}

	public void setName(String name) {
		this.placeName = name;
	}

}
