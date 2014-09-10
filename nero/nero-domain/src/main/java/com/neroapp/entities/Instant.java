package com.neroapp.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Instant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final DateFormat SIMPLE_DATETIME_FORMAT = SimpleDateFormat
			.getDateTimeInstance();
	private Long timestamp;
	private Date date;
	private TimeZone timezone;
	private Integer day;
	private Integer month;
	private Integer year;
	private Integer hour;
	private Integer minute;
	private Integer second;

	public Instant() {
		this.initialize(Calendar.getInstance());
	}
	
	public Instant(String dateSource) {
		try {
			Date date = SIMPLE_DATETIME_FORMAT.parse(dateSource);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			this.initialize(c);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void initialize(Calendar cal) {
		this.timestamp = cal.getTimeInMillis();
		this.timezone = cal.getTimeZone();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);
		second = cal.get(Calendar.SECOND);
		this.date = cal.getTime();
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public TimeZone getTimezone() {
		return timezone;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getDay() {
		return day;
	}

	public Integer getHour() {
		return hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public Integer getSecond() {
		return second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result
				+ ((timezone == null) ? 0 : timezone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		if (!(otherObject instanceof Instant))
			return false;
		Instant otherInstant = (Instant) otherObject;
		if (!this.timezone.equals(otherInstant.timezone))
			return false;
		else if (!this.timestamp.equals(otherInstant.timezone))
			return false;
		else
			return true;
	}

	@Override
	public String toString() {
		return SIMPLE_DATETIME_FORMAT.format(date);
	}
}