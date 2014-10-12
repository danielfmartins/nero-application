package com.neroapp.resources;

import java.util.List;

import com.neroapp.entities.Qualification;

public class QualificationsResource extends
		ResourceList<Qualification, QualificationResource> {
	
	public static final String TEMPLATE_URI = "/places/{id}/qualifications";

	public QualificationsResource(List<Qualification> values) {
		super(values);
	}

	public List<QualificationResource> getQualifications() {
		return resourceList();
	}
}
