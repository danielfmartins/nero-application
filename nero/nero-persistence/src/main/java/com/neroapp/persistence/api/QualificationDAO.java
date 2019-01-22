package com.neroapp.persistence.api;

import java.util.List;

import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;

public interface QualificationDAO extends DAO<Qualification> {

	List<Hashtag> getTopXHashtagsForQualifiable(Qualifiable qualifiable,
			Boolean isPositiveQualification, int limitX);

}