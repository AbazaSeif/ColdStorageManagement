package com.mg.csms.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mohak Gupta
 *
 */
public class ColdStorage extends Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer coldId;
	private Date date;
	private String coldName;

	public Integer getColdId() {
		return coldId;
	}

	public void setColdId(Integer coldId) {
		this.coldId = coldId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getColdName() {
		return coldName;
	}

	public void setColdName(String coldName) {
		this.coldName = coldName;
	}

}
