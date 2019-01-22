package com.neroapp.business.api;

import java.util.List;

import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;

public interface QualificationBO extends BO<Qualification> {
	
	List<Hashtag> getTopXHashtagsForPlace(Qualifiable qualifiable,
			Boolean isPositiveQualification, int limitX);

}
