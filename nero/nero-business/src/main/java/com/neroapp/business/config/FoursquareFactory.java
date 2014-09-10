package com.neroapp.business.config;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import fi.foyt.foursquare.api.FoursquareApi;

@ApplicationScoped
class FoursquareFactory {

	// CLIENT_ID e CLIENT_SECRET são os parametros de identificação da APP e NAO
	// DEVEM SER DIVULGADOS
	static final String CLIENT_ID = "Q02XOV5HVJPODONU3MVAMOF4RAXXNBFGSMK2SS0YB5GOC2QC";
	static final String CLIENT_SECRET = "YOPN1WNLO5VXH23JVUP3BKGYBHNWPF0NCRJPDAUHVTUPSBCT";
	static final String CALLBACK_URL = "http://localhost:8080/fmtestapp/foursquare/callback";
	static String REDIRECT_URL = "http://localhost:8080/fmtestapp/";
	//static String AUTHORIZATION_CODE = "HFNGWUWOAHRQVSIU40SA0FHDFKFFKZ2RVWSXKGENGTM3DITB";

	private FoursquareApi foursquareApi;
	
	@PostConstruct
	public void initialize() {
		this.foursquareApi = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, CALLBACK_URL);
	}
	
	@Produces
	public FoursquareApi foursquareApi() {
		return this.foursquareApi;
	}

}
