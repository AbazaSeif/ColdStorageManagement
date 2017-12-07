package com.mg.csms.beans;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Mohak Gupta
 *
 */
public class Vyaapari extends Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer vyaapariId;
	private LocalDate date;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
