package com.neroapp.business.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.neroapp.business.DefaultBO;
import com.neroapp.business.api.QualificationBO;
import com.neroapp.business.api.UserBO;

@RequestScoped
public class BOFactory {

	@Inject
	@Named("userBO")
	private UserBO userBO;
	
	@Inject
	@Named("qualificationBO")
	private QualificationBO qualificationBO;
	
	@Inject
	@Named("defaultBO")
	private DefaultBO defaultBO;
	
	public DefaultBO getDefaultBO() {
		return defaultBO;
	}
	
	public QualificationBO getQualificationBO() {
		return qualificationBO;
	}
	
	public UserBO getUserBO() {
		return userBO;
	}
}
