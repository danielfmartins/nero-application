package com.neroapp.console.command.delegate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

import com.neroapp.common.exceptions.NeroException;
import com.neroapp.console.Console;
import com.neroapp.console.NeroConsole;
import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.places.Place;
import com.neroapp.facade.NeroFacade;

public class NeroQualificarLocalDelegate extends NeroCommandDelegate {

	public NeroQualificarLocalDelegate(NeroConsole neroConsole) {
		super(neroConsole);
	}

	private Console console = this.getConsole();
	private NeroFacade fsproxy = this.getFacade();

	private void showHeader() {
		console.println("");
		console.println("QUALIFICAR O LOCAL");
		console.println("");
	}

	@Override
	public void run() {
		showHeader();
		Place place = getSelectedPlace();
		if (place != null) {
			console.println(place);
			Boolean isPositiveQualification = getQualificationType();
			List<Hashtag> recommendedHashtags;
			try {
				recommendedHashtags = fsproxy
						.getRecommendedHashtagsFor(place,
								isPositiveQualification);
				showRecommendedHashTags(recommendedHashtags);
			} catch (NeroException e) {
				console.println("Problemas obtendo recomendações de hashtags");
				e.printStackTrace();
			}
			Set<Hashtag> userHashtags = getUserHashtags(isPositiveQualification);
			String comment = getUserComment();
			Qualification newQualification = new Qualification(getNeroConsole()
					.getCurrentUser(), place, isPositiveQualification, comment);
			newQualification.setHashtags(userHashtags);
			boolean confirmed = confirmQualification(newQualification);
			if (confirmed)
				registerQualification(newQualification);
			else
				cancelQualification();
		}
		console.readLine("Pressione <ENTER> para continuar");
	}

	private void registerQualification(Qualification newQualification) {
		try {
			this.getNeroConsole().getNeroFacade()
					.registerQualification(newQualification);
			console.println("Qualificação registrada com sucesso.");
		} catch (NeroException e) {
			console.println("Problema registrando a qualificação.");
			e.printStackTrace();
		}

	}

	private void cancelQualification() {
		console.println("Qualificação descartada.");
	}

	private boolean confirmQualification(Qualification newQualification) {
		console.println("A qualificacao especificada foi a seguinte:");
		console.println("Lugar:" + newQualification.getQualifiable());
		console.println("Tipo:" + newQualification.getQualificationType());
		console.println("Hashtags:" + newQualification.getHashTagsStringArray());
		console.println("Comentário:" + newQualification.getComment());
		String confirmation = console.readLine("Confirma? (S/N)");
		if (confirmation.trim().equalsIgnoreCase("S"))
			return true;
		else
			return false;
	}

	/**
	 * Dada uma String que contenha uma serie de hashtags nela, retorna as
	 * hashtags
	 * 
	 * @param isPositiveQualification
	 * 
	 * @return
	 */
	private Set<Hashtag> getUserHashtags(Boolean isPositiveQualification) {
		Hashtag hashtag;
		String hashtags = console
				.readLine("Entre com suas hastags (separadas por espaço):");
		HashSet<Hashtag> result = new HashSet<Hashtag>();
		StringTokenizer tokenizer = new StringTokenizer(hashtags);
		String ht;
		while (tokenizer.hasMoreTokens()) {
			ht = tokenizer.nextToken();
			if (ht.indexOf('#') == 0)
				ht = ht.substring(ht.indexOf('#') + 1, ht.length());
			hashtag = new Hashtag(ht);
			hashtag.setDefinitionLocaleCountry(Locale.getDefault());
			result.add(hashtag);
		}
		return result;
	}

	private String getUserComment() {
		String comment = console.readLine("Qual seu comentário:");
		comment.trim();
		if (comment.length() == 0)
			return null;
		else
			return comment;
	}

	private void showRecommendedHashTags(List<Hashtag> hashtagsRecomendadas) {
		console.println("Hashtags recomendadas:");
		if (hashtagsRecomendadas != null) {
			for (Hashtag hastag : hashtagsRecomendadas)
				console.print("#" + hastag + " ");
		}
		console.println("\n");
	}

	private Boolean getQualificationType() {
		Boolean result = true;
		String sQType = console.readLine("Qualificacao positiva? (S/n):")
				.trim();
		if (!"S".equalsIgnoreCase(sQType))
			result = false;
		return result;
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
}
