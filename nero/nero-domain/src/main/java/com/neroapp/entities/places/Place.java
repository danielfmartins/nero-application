package com.neroapp.entities.places;

import java.io.Serializable;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;

public class Place implements Serializable, Qualifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Reference for the place is a string in the form "latitude,longitude"
	 */
	private Serializable reference;

	/**
	 * Place ID
	 */
	private String foursquareId;
	/**
	 * Place name
	 */
	private String foursquareName;

	/**
	 * From fi.foyt.foursquare.api.entities.CompactVenue
	 */
	private String url;

	/**
	 * From compactVenue->VenuesPhotos->Photogroup->Photo
	 */
	private String photoUrl;

	/**
	 * From fi.foyt.foursquare.api.entities.Location
	 */
	private String address;
	/**
	 * From fi.foyt.foursquare.api.entities.Location
	 */
	private String crossStreet;
	/**
	 * From fi.foyt.foursquare.api.entities.Location
	 */
	private String city;
	/**
	 * From fi.foyt.foursquare.api.entities.Location
	 */
	private String state;
	/**
	 * From fi.foyt.foursquare.api.entities.Location
	 */
	private String postalCode;
	/**
	 * From fi.foyt.foursquare.api.entities.Location
	 */
	private String country;
	/**
	 * From fi.foyt.foursquare.api.entities.Location
	 */
	private Double latitude;
	/**
	 * From fi.foyt.foursquare.api.entities.Location
	 */
	private Double longitude;
	/**
	 * From fi.foyt.foursquare.api.entities.Contact
	 */
	private String email;
	/**
	 * From fi.foyt.foursquare.api.entities.Contact
	 */
	private String facebook;
	/**
	 * From fi.foyt.foursquare.api.entities.Contact
	 */
	private String twitter;
	/**
	 * From fi.foyt.foursquare.api.entities.Contact
	 */
	private String phone;

	/**
	 * Place total thumbs down
	 */
	private Long totalThumbsUp;
	/**
	 * Place total thumbs down
	 */
	private Long totalThumbsDown;

	private Deque<Qualification> last10ThumbsUp;
	private Deque<Qualification> last10ThumbsDown;

	public Place() {
		super();
	};

	protected Place(String id, String name) {
		this();
		this.foursquareId = id;
		this.foursquareName = name;
		this.totalThumbsDown = 0l;
		this.totalThumbsUp = 0l;
		this.last10ThumbsDown = new ConcurrentLinkedDeque<Qualification>();
		this.last10ThumbsUp = new ConcurrentLinkedDeque<Qualification>();
	}

	public Place(String id, String name, String url, String photoUrl,
			String address, String crossStreet, String city, String state,
			String postalCode, String country, Double latitude,
			Double longitude, String email, String facebook, String twitter,
			String phone) {
		this(id, name);
		this.url = url;
		this.setPhotoUrl(photoUrl);
		this.address = address;
		this.crossStreet = crossStreet;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.email = email;
		this.facebook = facebook;
		this.twitter = twitter;
		this.phone = phone;
		this.reference = latitude + "," + longitude;

	}

	@Override
	public void setReference(Serializable reference) {
		this.reference = reference;
	}

	@Override
	public Serializable getReference() {
		return reference;
	}

	@Override
	public void setId(Serializable id) {
		this.foursquareId = (String) id;
	}

	@Override
	public Serializable getId() {
		return foursquareId;
	}

	@Override
	public void setName(String name) {
		this.foursquareName = name;
	}

	@Override
	public String getName() {
		return foursquareName;
	}

	public String getUrl() {
		return url;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getAddress() {
		return address;
	}

	public String getCrossStreet() {
		return crossStreet;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getEmail() {
		return email;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public Long getTotalThumbsUp() {
		return totalThumbsUp;
	}

	@Override
	public void setTotalThumbsUp(Long totalThumbsUp) {
		this.totalThumbsUp = totalThumbsUp;
	}

	@Override
	public Long getTotalThumbsDown() {
		return totalThumbsDown;
	}

	@Override
	public void setTotalThumbsDown(Long totalThumbsDown) {
		this.totalThumbsDown = totalThumbsDown;
	}

	@Override
	public Deque<Qualification> getLast10ThumbsUp() {
		return last10ThumbsUp;
	}

	@Override
	public void setLast10ThumbsUp(Deque<Qualification> last10ThumbsUp) {
		this.last10ThumbsUp = last10ThumbsUp;
	}

	@Override
	public Deque<Qualification> getLast10ThumbsDown() {
		return last10ThumbsDown;
	}

	@Override
	public void setLast10ThumbsDown(Deque<Qualification> last10ThumbsDown) {
		this.last10ThumbsDown = last10ThumbsDown;
	}

	@Override
	public void putNewQualification(Qualification thumbsDetail) {
		if (thumbsDetail.isPositive()) {
			this.last10ThumbsUp.addFirst(thumbsDetail);
			if (this.last10ThumbsUp.size() > 10)
				this.last10ThumbsUp.removeLast();
			this.totalThumbsUp++;
		} else {
			this.last10ThumbsDown.addFirst(thumbsDetail);
			if (this.last10ThumbsDown.size() > 10)
				this.last10ThumbsDown.removeLast();
			this.totalThumbsDown++;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((foursquareId == null) ? 0 : foursquareId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		if (foursquareId == null) {
			if (other.foursquareId != null)
				return false;
		} else if (!foursquareId.equals(other.foursquareId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public String toDetailedString() {
		String result = null;
		result = this.toString() + "\tTotalUP:" + this.totalThumbsUp
				+ "\tTotalDown:" + this.totalThumbsDown;
		return result;
	}

	@Override
	public int compareTo(Qualifiable other) {
		int result = 0;
		if (other == null || !(other instanceof Place))
			result = 1;
		Place otherplace = (Place) other;
		if (this.getTotalThumbsUp() > otherplace.getTotalThumbsUp())
			result = 1;
		if (this.getTotalThumbsUp() == otherplace.getTotalThumbsUp())
			result = 0;
		if (this.getTotalThumbsUp() < otherplace.getTotalThumbsUp())
			result = -1;
		return result;
	}

}