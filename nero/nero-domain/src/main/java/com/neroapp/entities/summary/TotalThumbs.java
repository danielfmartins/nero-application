package com.neroapp.entities.summary;

public class TotalThumbs {

	private Integer year;
	private Integer month;
	private Long totalThumbsUp;
	private Long totalThumbsDown;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getTotalThumbsUp() {
		return totalThumbsUp;
	}

	public void setTotalThumbsUp(Long totalThumbsUp) {
		this.totalThumbsUp = totalThumbsUp;
	}
	
	public void addTotalThumbsUp() {
		if (totalThumbsUp==null) totalThumbsUp=0L;
		this.totalThumbsUp++;
	}

	public Long getTotalThumbsDown() {
		return totalThumbsDown;
	}

	public void setTotalThumbsDown(Long totalThumbsDown) {
		this.totalThumbsDown = totalThumbsDown;
	}
	
	public void addTotalThumbsDown() 
	{
		if (totalThumbsDown==null) totalThumbsDown=0L;
		this.totalThumbsDown++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TotalThumbs other = (TotalThumbs) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TotalThumbs [year=" + year + ", month=" + month
				+ ", totalThumbsUp=" + totalThumbsUp + ", totalThumbsDown="
				+ totalThumbsDown + "]";
	}
}
