package com.neroapp.console.command;

import com.neroapp.console.NeroConsole;
import com.neroapp.console.command.delegate.NeroCommandDelegate;
import com.neroapp.console.command.delegate.NeroDetalhesLocalDelegate;
import com.neroapp.console.command.delegate.NeroLatitudeLongitudeDelegate;
import com.neroapp.console.command.delegate.NeroPesquisarLocaisDelegate;
import com.neroapp.console.command.delegate.NeroQualificarLocalDelegate;

public class NeroCommandChain {

	private static NeroConsole neroConsole;

	public static NeroCommand getCommandChain(NeroConsole console) {
		neroConsole = console;

		NeroCompositeCommand main = new NeroCompositeCommand("MENU PRINCIPAL");
		main.setRequestUserInteractionText("Escolha uma opção:");

		// DEFINIR LATITUDE E LONGITUDE
		main.addCommand(new NeroLeafCommand("Definir latitude e longitude.",
				new NeroLatitudeLongitudeDelegate(neroConsole)));
		// PESQUISAR LOCAIS
		main.addCommand(new NeroLeafCommand("Pesquisar locais.",
				new NeroPesquisarLocaisDelegate(neroConsole)));
		// PESQUISAR LOCAIS POR NOME
		main.addCommand(new NeroLeafCommand("Pesquisar locais POR NOME.",
				new NeroPesquisarLocaisDelegate(neroConsole, true)));
		// DETALHES DO LOCAL
		main.addCommand(new NeroLeafCommand("Detalhes do local.",
				new NeroDetalhesLocalDelegate(neroConsole)));
		// QUALIFICAR LOCAL
		main.addCommand(new NeroLeafCommand("Qualificar o local.",
				new NeroQualificarLocalDelegate(neroConsole)));

		main.addCommand(new NeroLeafCommand("Sair.", new NeroCommandDelegate(
				null) {
			@Override
			public void run() {
				System.exit(0);
			}
		}));

		return main;
	}
}
