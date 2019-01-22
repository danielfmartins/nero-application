package com.neroapp.persistence.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.summary.HashtagTotalNegativeHitByYear;
import com.neroapp.entities.summary.HashtagTotalPositiveHitByYear;
import com.neroapp.persistence.api.QualificationDAO;

class FindNegativeHashtags extends Predicate<HashtagTotalNegativeHitByYear> {

	private static final long serialVersionUID = 1L;
	private Qualifiable qualifiable;
	private Integer limitingYear;

	public FindNegativeHashtags(Qualifiable qualifiable, Integer limitingYear) {
		this.qualifiable = qualifiable;
		this.limitingYear = limitingYear;
	}

	@Override
	public boolean match(HashtagTotalNegativeHitByYear candidate) {
		return candidate.getQualifiable().equals(this.qualifiable)
				&& (candidate.getYear() >= this.limitingYear);
	}
}

class FindPositiveHashtags extends Predicate<HashtagTotalPositiveHitByYear> {

	private static final long serialVersionUID = 1L;
	private Qualifiable qualifiable;
	private Integer limitingYear;

	public FindPositiveHashtags(Qualifiable qualifiable, Integer limitingYear) {
		this.qualifiable = qualifiable;
		this.limitingYear = limitingYear;
	}

	@Override
	public boolean match(HashtagTotalPositiveHitByYear candidate) {
		return candidate.getQualifiable().equals(this.qualifiable)
				&& (candidate.getYear() >= this.limitingYear);
	}

}

@RequestScoped
@Named("qualificationDAO")
public class QualificationDB4oDAO extends AbstractGenericDB4oDAO<Qualification>
		implements QualificationDAO {

	public QualificationDB4oDAO() {
		super();
	}

	@Inject
	public void setContainer(ObjectContainer container) {
		super.setContainer(container);
	}
	
	/**
	 * The top hashtag considers the last two years
	 */
	public List<Hashtag> getTopXHashtagsForQualifiable(Qualifiable qualifiable,
			Boolean isPositiveQualification, int limitX) {
		List<Hashtag> result = null;
		int limitCount = 0;
		Integer limitingYear = Calendar.getInstance().get(Calendar.YEAR) - 2;
		if (isPositiveQualification) {
			// Search in HashtagTotalPositiveHitByYear
			List<HashtagTotalPositiveHitByYear> hby = container.query(
					new FindPositiveHashtags(qualifiable, limitingYear),
					new Comparator<HashtagTotalPositiveHitByYear>() {

						@Override
						public int compare(HashtagTotalPositiveHitByYear o1,
								HashtagTotalPositiveHitByYear o2) {
							int result = -1;
							if (o1.getHits() > o2.getHits())
								result = 1;
							if (o1.getHits() == o2.getHits())
								result = 0;
							if (o1.getHits() < o2.getHits())
								result = -1;
							return result;
						}
					});
			Iterator<HashtagTotalPositiveHitByYear> i = hby.iterator();
			//
			while (i.hasNext() && limitCount < limitX) {
				if (result == null)
					result = new ArrayList<Hashtag>();
				result.add(i.next().getHashtag());
				limitCount++;
			}
		} else {
			// Search in HashtagTotalNegativeHitByYear
			List<HashtagTotalNegativeHitByYear> hby = container.query(
					new FindNegativeHashtags(qualifiable, limitingYear),
					new Comparator<HashtagTotalNegativeHitByYear>() {

						@Override
						public int compare(HashtagTotalNegativeHitByYear o1,
								HashtagTotalNegativeHitByYear o2) {
							int result = -1;
							if (o1.getHits() > o2.getHits())
								result = 1;
							if (o1.getHits() == o2.getHits())
								result = 0;
							if (o1.getHits() < o2.getHits())
								result = -1;
							return result;
						}
					});
			Iterator<HashtagTotalNegativeHitByYear> i = hby.iterator();
			//
			while (i.hasNext() && limitCount < limitX) {
				if (result == null)
					result = new ArrayList<Hashtag>();
				result.add(i.next().getHashtag());
				limitCount++;
			}
		}
		return result;
	}
}