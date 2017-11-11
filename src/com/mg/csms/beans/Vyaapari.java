package com.mg.csms.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mohak Gupta
 *
 */
public class Vyaapari extends Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer vyaapariId;
	private Date date;
	private String vyaapariName;

	public Integer getVyaapariId() {
		return vyaapariId;
	}

	public void setVyaapariId(Integer vyaapariId) {
		this.vyaapariId = vyaapariId;
	}

	public String getVyaapariName() {
		return vyaapariName;
	}

	public void setVyaapariName(String vyaapariName) {
		this.vyaapariName = vyaapariName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getColdName() {
		return vyaapariName;
	}

	public void setColdName(String vyaapariName) {
		this.vyaapariName = vyaapariName;
	}

}
