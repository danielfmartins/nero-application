package com.neroapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompactQualification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Serializable qualifiableId;
	private String userName;
	private String comment;
	private Date date;
	private Boolean positive;

	public CompactQualification(Serializable qualifiableId, String userName, String comment, Date date, Boolean positive) {
		super();
		this.setQualifiableId(qualifiableId);
		this.setUserName(userName);
		this.setComment(comment);
		this.setDate(date);
		this.setPositive(positive);
	}
	
	public CompactQualification() {
		
	}
	
	public CompactQualification(Qualification qualification) {
		super();
		this.qualifiableId = qualification.getQualifiable().getId();
		this.userName = qualification.getUser().getUsername();
	}

	public Serializable getQualifiableId() {
		return qualifiableId;
	}

	public void setQualifiableId(Serializable qualifiableId) {
		this.qualifiableId = qualifiableId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getPositive() {
		return positive;
	}

	public void setPositive(Boolean positive) {
		this.positive = positive;
	}
}
