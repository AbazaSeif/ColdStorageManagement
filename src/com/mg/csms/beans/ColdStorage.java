package com.mg.csms.beans;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Mohak Gupta
 *
 */
public class ColdStorage extends Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer coldId;
	private LocalDate date;
	private String coldName;

	public Integer getColdId() {
		return coldId;
	}

	public void setColdId(Integer coldId) {
		this.coldId = coldId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getColdName() {
		return coldName;
	}

	public void setColdName(String coldName) {
		this.coldName = coldName;
	}

}
