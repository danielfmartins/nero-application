package com.neroapp.console.command.delegate;

import java.util.HashMap;
import java.util.List;

import com.neroapp.common.NeroException;
import com.neroapp.console.Console;
import com.neroapp.console.NeroConsole;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;

public class NeroDetalhesLocalDelegate extends NeroCommandDelegate {

	public NeroDetalhesLocalDelegate(NeroConsole neroConsole) {
		super(neroConsole);
	}

	private Console console = this.getConsole();
	private NeroFacade facade = this.getFacade();

	private void showHeader() {
		console.println("");
		console.println("DETALHES DO LOCAL");
		console.println("");
	}

	@Override
	public void run() {
		showHeader();
		Place place = getSelectedPlace();
		if (place != null) {
			showQualifications(place);
			showDetails(place);
		}
		console.readLine("Pressione <ENTER> para continuar");
	}

	private void showDetails(Place place) {
		console.println("Codigo Foursquare :" + place.getId());
		console.println("Nome Foursquare   :" + place.getName());
		console.println(place);
	}

	private Place getSelectedPlace() {
		Place result = null;
		String sNumber = console.readLine("Entre com um numero de um local:");
		try {
			Integer placeId = Integer.parseInt(sNumber);
			@SuppressWarnings("unchecked")
			HashMap<Integer, Place> places = (HashMap<Integer, Place>) this
					.getNeroConsole().getConsoleMemory()
					.get(NeroConsole.PLACES);
			result = places.get(placeId);

		} catch (NumberFormatException nfe) {
			console.println("ERRO - Numero invalido: " + sNumber);
		}
		return result;
	}

	private void showQualifications(Place place) {
		List<Qualification> qualifications = null;
		try {
			qualifications = facade.getAllQualifications(place);
			console.println("Qualificacoes");
			if (qualifications != null && !qualifications.isEmpty()) {
				int i = 1;
				for (Qualification q : qualifications) {
					console.println("\t\tQualificacao " + i);
					console.println("\t\tQuem:" + q.getUser());
					console.println("\t\tQuando:" + q.getInstant().toString());
					console.println("\t\tTipo:" + q.getQualificationType());
					console.println("\t\thashtags:"
							+ q.getHashTagsStringArray());
					i++;

				}
			} else
				console.println(" Sem qualificacoes ate o momento");
		} catch (NeroException e) {
			console.println("Problema na obtencao das qualificacoes");
			e.printStackTrace();
		}
	}

}
