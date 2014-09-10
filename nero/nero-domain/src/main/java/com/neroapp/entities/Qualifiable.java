package com.neroapp.entities;

import java.io.Serializable;
import java.util.Deque;

public interface Qualifiable extends Comparable<Qualifiable> {
	
	/**
	 * Reference for Qualifiable refers to "a way to find the Qualifiable"
	 * For places is latitude,longitude
	 * For cars, maybe it will me a CATEGORY
	 * @return
	 */
	Serializable getReference();
	
	void setReference(Serializable reference);
	
	Serializable getId();

	void setId(Serializable id);

	String getName();

	void setName(String name);

	Long getTotalThumbsUp();

	void setTotalThumbsUp(Long totalThumbsUp);

	Long getTotalThumbsDown();

	void setTotalThumbsDown(Long totalThumbsDown);

	Deque<Qualification> getLast10ThumbsUp();

	void setLast10ThumbsUp(Deque<Qualification> last10ThumbsUp);

	Deque<Qualification> getLast10ThumbsDown();

	void setLast10ThumbsDown(Deque<Qualification> last10ThumbsDown);

	void putNewQualification(Qualification thumbsDetail);

	String toDetailedString();

}