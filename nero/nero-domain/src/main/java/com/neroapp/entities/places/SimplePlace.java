package com.neroapp.entities.places;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimplePlace extends CompactPlace {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private transient String address;
	private transient String country;
	private transient Boolean isMyPlace;
	private transient Long totalIsMyPlace;
	private transient String facebook;
	private transient String twitter;
	private transient String phone;
	private transient List<String> recomendedPositiveHashtags;
	private transient List<String> recomendedNegativeHashtags;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getIsMyPlace() {
		return isMyPlace;
	}

	public void setIsMyPlace(Boolean isMyPlace) {
		this.isMyPlace = isMyPlace;
	}

	public Long getTotalIsMyPlace() {
		return totalIsMyPlace;
	}

	public void setTotalIsMyPlace(Long totalIsMyPlace) {
		this.totalIsMyPlace = totalIsMyPlace;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getRecomendedPositiveHashtags() {
		return recomendedPositiveHashtags;
	}

	public void setRecomendedPositiveHashtags(
			List<String> recomendedPositiveHashtags) {
		this.recomendedPositiveHashtags = recomendedPositiveHashtags;
	}

	public List<String> getRecomendedNegativeHashtags() {
		return recomendedNegativeHashtags;
	}

	public void setRecomendedNegativeHashtags(
			List<String> recomendedNegativeHashtags) {
		this.recomendedNegativeHashtags = recomendedNegativeHashtags;
	}

}
