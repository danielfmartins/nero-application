package com.neroapp.console.services.resource;

import java.util.ArrayList;
import java.util.List;

import com.neroapp.entities.Qualification;

public class QualificationsResource extends Resource<List<Qualification>> {

	private List<QualificationResource> qualifications;

	public QualificationsResource() {
		super();
	}

	public List<QualificationResource> getQualifications() {
		if (this.qualifications == null) {
			this.qualifications = new ArrayList<>();
		}
		return qualifications;
	}

	public void setQualifications(List<QualificationResource> qualifications) {
		this.qualifications = qualifications;
	}

	/**
	 * @see com.neroapp.console.services.resource.Resource#unwrap()
	 */
	@Override
	public List<Qualification> unwrap() {
		List<Qualification> values = new ArrayList<>();
		for(QualificationResource resource : this.getQualifications()) {
			values.add(resource.unwrap());
		}
		return values;
	}

}