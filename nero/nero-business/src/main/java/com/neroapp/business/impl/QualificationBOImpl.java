package com.neroapp.business.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.neroapp.business.api.QualificationBO;
import com.neroapp.business.exception.BOException;
import com.neroapp.entities.Hashtag;
import com.neroapp.entities.Qualifiable;
import com.neroapp.entities.Qualification;
import com.neroapp.entities.QualificationHashtag;
import com.neroapp.entities.summary.HashtagTotalHitByMonth;
import com.neroapp.entities.summary.HashtagTotalHitByYear;
import com.neroapp.entities.summary.HashtagTotalNegativeHitByMonth;
import com.neroapp.entities.summary.HashtagTotalNegativeHitByYear;
import com.neroapp.entities.summary.HashtagTotalPositiveHitByMonth;
import com.neroapp.entities.summary.HashtagTotalPositiveHitByYear;
import com.neroapp.entities.summary.TotalThumbs;
import com.neroapp.persistence.BaseDAO;
import com.neroapp.persistence.api.QualificationDAO;
import com.neroapp.persistence.exception.DAOException;

@RequestScoped
@Named("qualificationBO")
public class QualificationBOImpl extends AbstractBO<Qualification> implements QualificationBO {

	@Inject
	@Named("baseDAO")
	private BaseDAO baseDAO;

	public QualificationBOImpl() {
		super();
	}
	
	@Inject
	@Named("qualificationDAO")
	public void setDao(QualificationDAO dao) {
		super.setDao(dao);
	}
	
	@SuppressWarnings("unchecked")
	private void updatePlaceQualifications(Qualification qualification)
			throws BOException {
		Qualifiable qualifiable = qualification.getQualifiable();
		qualifiable.putNewQualification(qualification);
		try {
			this.baseDAO.saveOrUpdate(qualifiable);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void updateTotalThumbs(Qualification qualification)
			throws BOException {
		Integer year = qualification.getInstant().getYear();
		Integer month = qualification.getInstant().getMonth();
		TotalThumbs totalThumbsDummy = new TotalThumbs();
		totalThumbsDummy.setYear(year);
		totalThumbsDummy.setMonth(month);
		try {
			TotalThumbs totalThumbs = (TotalThumbs) this.baseDAO
					.findUniqueByExample(totalThumbsDummy);
			if (totalThumbs == null) {
				totalThumbs = new TotalThumbs();
				totalThumbs.setYear(year);
				totalThumbs.setMonth(month);
				if (qualification.getPositiveQualification())
					totalThumbs.setTotalThumbsUp(1L);
				else
					totalThumbs.setTotalThumbsDown(1L);
			} else {
				if (qualification.getPositiveQualification())
					totalThumbs.addTotalThumbsUp();
				else
					totalThumbs.addTotalThumbsDown();
			}
			this.baseDAO.saveOrUpdate(totalThumbs);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	/**
	 * Updates the total use of that hashtag using the qualification
	 * information. This method is not intended to have a commit command.
	 * 
	 * @param hashtag
	 * @param qualification
	 * @throws BOException
	 */
	@SuppressWarnings("unchecked")
	private void updateHashtagTotalUse(Hashtag hashtag) throws BOException {
		hashtag.incTotalUse();
		try {
			this.baseDAO.saveOrUpdate(hashtag);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	/**
	 * Updates the total hashtag hit by year considering the qualification data.
	 * This method is not intended to have a commit command.
	 * 
	 * @param hashtag
	 * @param qualification
	 * @throws BOException
	 */
	@SuppressWarnings("unchecked")
	private void updateHashtagTotalHitByYear(Hashtag hashtag,
			Qualification qualification) throws BOException {
		HashtagTotalHitByYear dummyByYear, htYear;
		dummyByYear = new HashtagTotalHitByYear();
		dummyByYear.setHashtag(hashtag);
		dummyByYear.setQualifiable(qualification.getQualifiable());
		dummyByYear.setYear(qualification.getInstant().getYear());

		try {
			htYear = (HashtagTotalHitByYear) this.baseDAO
					.findUniqueByExample(dummyByYear);
			if (htYear == null) {
				htYear = new HashtagTotalHitByYear(qualification.getInstant()
						.getYear(), qualification.getQualifiable(), hashtag, 1L);
			} else {
				htYear.addHit();
			}
			this.baseDAO.saveOrUpdate(htYear);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void updateHashtagTotalHitByMonth(Hashtag hashtag,
			Qualification qualification) throws BOException {
		HashtagTotalHitByMonth dummyByMonth, htMonth;

		dummyByMonth = new HashtagTotalHitByMonth();
		dummyByMonth.setQualifiable(qualification.getQualifiable());
		dummyByMonth.setYear(qualification.getInstant().getYear());
		dummyByMonth.setMonth(qualification.getInstant().getMonth());
		dummyByMonth.setHashtag(hashtag);
		try {
			htMonth = (HashtagTotalHitByMonth) this.baseDAO
					.findUniqueByExample(dummyByMonth);
			if (htMonth == null) {
				htMonth = new HashtagTotalHitByMonth(qualification.getInstant()
						.getYear(), qualification.getInstant().getMonth(),
						qualification.getQualifiable(), hashtag, 1L);
			} else {
				htMonth.addHit();
			}
			this.baseDAO.saveOrUpdate(htMonth);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	private void updateTotalPositiveHits(Hashtag hashtag,
			Qualification qualification) throws BOException {
		updateTotalMonthlyPositiveHits(hashtag, qualification);
		updateTotalYearlyPositiveHits(hashtag, qualification);
	}

	@SuppressWarnings("unchecked")
	private void updateTotalMonthlyPositiveHits(Hashtag hashtag,
			Qualification qualification) throws BOException {
		HashtagTotalPositiveHitByMonth dummyByMonth, htMonth;

		dummyByMonth = new HashtagTotalPositiveHitByMonth();
		dummyByMonth.setQualifiable(qualification.getQualifiable());
		dummyByMonth.setYear(qualification.getInstant().getYear());
		dummyByMonth.setMonth(qualification.getInstant().getMonth());
		dummyByMonth.setHashtag(hashtag);
		try {
			htMonth = (HashtagTotalPositiveHitByMonth) this.baseDAO
					.findUniqueByExample(dummyByMonth);
			if (htMonth == null)
				htMonth = new HashtagTotalPositiveHitByMonth(qualification
						.getInstant().getYear(), qualification.getInstant()
						.getMonth(), qualification.getQualifiable(), hashtag,
						1L);
			else
				htMonth.addHit();
			this.baseDAO.saveOrUpdate(htMonth);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void updateTotalYearlyPositiveHits(Hashtag hashtag,
			Qualification qualification) throws BOException {
		HashtagTotalPositiveHitByYear dummyByYear, htYear;

		dummyByYear = new HashtagTotalPositiveHitByYear();
		dummyByYear.setQualifiable(qualification.getQualifiable());
		dummyByYear.setYear(qualification.getInstant().getYear());
		dummyByYear.setHashtag(hashtag);
		try {
			htYear = (HashtagTotalPositiveHitByYear) this.baseDAO
					.findUniqueByExample(dummyByYear);
			if (htYear == null)
				htYear = new HashtagTotalPositiveHitByYear(qualification
						.getInstant().getYear(),
						qualification.getQualifiable(), hashtag, 1L);
			else
				htYear.addHit();
			this.baseDAO.saveOrUpdate(htYear);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	private void updateTotalNegativeHits(Hashtag hashtag,
			Qualification qualification) throws BOException {
		updateTotalMonthlyNegativeHits(hashtag, qualification);
		updateTotalYearlyNegativeHits(hashtag, qualification);
	}

	@SuppressWarnings("unchecked")
	private void updateTotalMonthlyNegativeHits(Hashtag hashtag,
			Qualification qualification) throws BOException {
		HashtagTotalNegativeHitByMonth dummyByMonth, htMonth;

		dummyByMonth = new HashtagTotalNegativeHitByMonth();
		dummyByMonth.setQualifiable(qualification.getQualifiable());
		dummyByMonth.setYear(qualification.getInstant().getYear());
		dummyByMonth.setMonth(qualification.getInstant().getMonth());
		dummyByMonth.setHashtag(hashtag);
		try {
			htMonth = (HashtagTotalNegativeHitByMonth) this.baseDAO
					.findUniqueByExample(dummyByMonth);
			if (htMonth == null) {
				htMonth = new HashtagTotalNegativeHitByMonth(qualification
						.getInstant().getYear(), qualification.getInstant()
						.getMonth(), qualification.getQualifiable(), hashtag,
						1L);
			} else {
				htMonth.addHit();
			}
			this.baseDAO.saveOrUpdate(htMonth);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void updateTotalYearlyNegativeHits(Hashtag hashtag,
			Qualification qualification) throws BOException {
		HashtagTotalNegativeHitByYear dummyByYear, htYear;

		dummyByYear = new HashtagTotalNegativeHitByYear();
		dummyByYear.setQualifiable(qualification.getQualifiable());
		dummyByYear.setYear(qualification.getInstant().getYear());
		dummyByYear.setHashtag(hashtag);
		try {
			htYear = (HashtagTotalNegativeHitByYear) this.baseDAO
					.findUniqueByExample(dummyByYear);
			if (htYear == null) {
				htYear = new HashtagTotalNegativeHitByYear(qualification
						.getInstant().getYear(),
						qualification.getQualifiable(), hashtag, 1L);
			} else {
				htYear.addHit();
			}
			this.baseDAO.saveOrUpdate(htYear);
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	/**
	 * For each hashtag in the qualification, let's update theirs totals
	 * 
	 * @param qualification
	 * @throws BOException
	 */
	private void updateHashtagTotals(Qualification qualification)
			throws BOException {
		for (Hashtag hashtag : qualification.getHashtags()) {
			// update hashtag total use;
			updateHashtagTotalUse(hashtag);
			updateHashtagTotalHitByYear(hashtag, qualification);
			updateHashtagTotalHitByMonth(hashtag, qualification);
			if (qualification.isPositive()) {
				updateTotalPositiveHits(hashtag, qualification);
			} else {
				updateTotalNegativeHits(hashtag, qualification);
			}
			// insert the Qualification-Hashtag relation
			insertQualificationHashtagRelation(hashtag, qualification);
		}
	}

	@SuppressWarnings("unchecked")
	private void insertQualificationHashtagRelation(Hashtag hashtag,
			Qualification qualification) throws BOException {
		try {
			this.baseDAO.saveOrUpdate(new QualificationHashtag(qualification,
					hashtag));
		} catch (DAOException e) {
			throw new BOException(e);
		}
	}

	/**
	 * Salvar uma qualificacao é um processo relativamente complexo. Precisa
	 * atualizar as estatisticas
	 */
	public Qualification saveOrUpdate(Qualification qualification)
			throws BOException {
		this.beginTransaction();
		try {
			qualification = super.saveOrUpdate(qualification);
			updatePlaceQualifications(qualification);
			updateTotalThumbs(qualification);
			updateHashtagTotals(qualification);
			this.commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.rollback();
		}
		return qualification;
	}

	public List<Hashtag> getTopXHashtagsForPlace(Qualifiable qualifiable,
			Boolean isPositiveQualification, int limitX) {
		return ((QualificationDAO) this.dao).getTopXHashtagsForQualifiable(qualifiable,
				isPositiveQualification, limitX);
	}
}
