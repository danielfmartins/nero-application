package com.neroapp.business.config;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.neroapp.annotations.Value;

import fi.foyt.foursquare.api.FoursquareApi;

@ApplicationScoped
class FoursquareFactory {

	@Inject
	@Value("foursquare.client.id")
	private String foursquareClientId;

	@Inject
	@Value("foursquare.client.secret")
	private String foursquareClientSecret;

	@Inject
	@Value("foursquare.callback.url")
	private String foursquareCallbackUrl;
	
	private FoursquareApi foursquareApi;

	@PostConstruct
	public void initialize() {
		this.foursquareApi = new FoursquareApi(this.foursquareClientId,
				this.foursquareClientSecret, this.foursquareCallbackUrl);
	}

	@Produces
	public FoursquareApi foursquareApi() {
		return this.foursquareApi;
	}

}
