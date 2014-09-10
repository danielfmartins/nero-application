package com.neroapp.entities.places;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompactPlace implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient String id;
	private transient String name;
	private transient String description;
	private transient Long totalPositiveQualifications;
	private transient Long totalNegativeQualifications;
	
	public CompactPlace() {
		super();
	}
	
	public CompactPlace(String id, String name, String description, Long totalPositiveQualifications, Long totalNegativeQualifications)
	{
		this();
		this.setId(id);
		this.setName(name);
		this.setTotalPositiveQualifications(totalPositiveQualifications);
		this.setTotalNegativeQualifications(totalNegativeQualifications);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTotalPositiveQualifications() {
		return totalPositiveQualifications;
	}

	public void setTotalPositiveQualifications(Long totalPositiveQualifications) {
		this.totalPositiveQualifications = totalPositiveQualifications;
	}

	public Long getTotalNegativeQualifications() {
		return totalNegativeQualifications;
	}

	public void setTotalNegativeQualifications(Long totalNegativeQualifications) {
		this.totalNegativeQualifications = totalNegativeQualifications;
	}

}
