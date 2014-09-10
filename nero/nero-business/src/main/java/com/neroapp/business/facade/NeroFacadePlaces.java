package com.neroapp.business.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.neroapp.business.api.BO;
import com.neroapp.business.api.QualificationBO;
import com.neroapp.business.exception.BOException;
import com.neroapp.business.impl.BOFactory;
import com.neroapp.common.NeroException;
import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.places.Place;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CheckinGroup;
import fi.foyt.foursquare.api.entities.CompactUser;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.Contact;
import fi.foyt.foursquare.api.entities.Location;
import fi.foyt.foursquare.api.entities.Photo;
import fi.foyt.foursquare.api.entities.PhotoGroup;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

@RequestScoped
public class NeroFacadePlaces extends NeroFacadeImpl {

	//TODO essas constantes precisa ser definidas em configurações (maven profile)
	public static final String CLIENT_ID = "Q02XOV5HVJPODONU3MVAMOF4RAXXNBFGSMK2SS0YB5GOC2QC";
	public static final String CLIENT_SECRET = "YOPN1WNLO5VXH23JVUP3BKGYBHNWPF0NCRJPDAUHVTUPSBCT";
	public static final String CALLBACK_URL = "http://localhost:8080/fmtestapp/foursquare/callback";

	@Inject
	protected FoursquareApi foursquareApi;

	public NeroFacadePlaces() {
		super();
	}
	
	@Inject
	@Override
	public void setBoFactory(BOFactory boFactory) {
		super.setBoFactory(boFactory);
	}
	
	/**
	 * 
	 * 
	 * @param place
	 * @return
	 * @throws FoursquareApiException
	 */
	public List<String> getPeopleWhoWasHere(Qualifiable place)
			throws FoursquareApiException {
		List<String> result = null;
		Calendar epoch = Calendar.getInstance();
		epoch.set(2008, 01, 01);
		Result<CheckinGroup> cig = foursquareApi.venuesHereNow((String)place.getId(),
				50, 0, epoch.getTimeInMillis() * 1000);
		if (cig.getMeta().getCode() == 200) {
			result = new ArrayList<String>();
			Checkin[] checkins = cig.getResult().getItems();
			for (Checkin checkin : checkins) {
				CompactUser user = checkin.getUser();
				result.add(user.getGender() + "," + user.getFirstName() + " "
						+ user.getLastName());
			}
		}
		return result;
	}

	@Override
	public List<Hashtag> getRecommendedHashtagsFor(Qualifiable qualifiable,
			Boolean isPositiveQualification) throws NeroException {
		QualificationBO qbo = boFactory.getQualificationBO();
		Place place = null;
		if (qualifiable instanceof Place) {
			place = (Place) qualifiable;
		} else {
			throw new NeroException(
					"Qualifiable is not of the required type: PLACE");
		}
		return qbo.getTopXHashtagsForPlace(place, isPositiveQualification, 10);
	};

	/**
	 * Gets all venues around this latitude/longitude.
	 * 
	 * @param referenceForQualifiables
	 *            where to search for qualifiables. Is a string
	 * @param qualifiableName
	 *            Name restriction for qualifiable (beginning with)
	 * @param resultSetSizeLimit
	 *            To limit the resultset size
	 * @return
	 * @throws FoursquareApiException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Place> getQualifiables(
			Serializable referenceForQualifiables, String qualifiableName,
			Integer resultSetSizeLimit) throws NeroException {
		List<Place> result = null;
		Place place;
		BO<Place> bo = boFactory.getDefaultBO();
		try {
			String latitudeLongitude = (String) referenceForQualifiables;
			Result<VenuesSearchResult> vsr = foursquareApi.venuesSearch(
					latitudeLongitude, null, resultSetSizeLimit, null, qualifiableName,
					null, null, null);
			if (vsr.getMeta().getCode() == 200) {
				result = new ArrayList<Place>();
				bo.beginTransaction();
				for (CompactVenue venue : vsr.getResult().getVenues()) {
					place = new Place();
					place.setReference(venue.getLocation().getLat() + ","
							+ venue.getLocation().getLng());
					place.setId(venue.getId());
					try {
						place = bo.findUniqueByExample(place);
						if (place == null) {
							// if the place does not exists in Nero, let's store
							// it.
							Location location = venue.getLocation();
							Contact contact = venue.getContact();
							Result<PhotoGroup> photogroup = foursquareApi.venuesPhotos(venue.getId(), "venue", 1, 0);
							String photoUrl = null;
							if (photogroup!=null && photogroup.getMeta().getCode() == 200) {
								Photo[]  photos = photogroup.getResult().getItems();
								if (photos!=null && photos.length>0)
									photoUrl = photos[0].getUrl();
							}
							place = new Place(
									venue.getId(), 
									venue.getName(),
									venue.getUrl(),
									photoUrl,
									location.getAddress(),
									location.getCrossStreet(),
									location.getCity(), location.getState(),
									location.getPostalCode(),
									location.getCountry(), location.getLat(),
									location.getLng(), contact.getEmail(),
									contact.getFacebook(),
									contact.getTwitter(), 
									contact.getPhone());
							place = bo.saveOrUpdate(place);
						}
						result.add(place);
					} catch (BOException e) {
						e.printStackTrace();
					}
				}
				bo.commit();
			}
		} catch (FoursquareApiException fae) {
			throw new NeroException(fae);
		}
		return result;
	}



}
