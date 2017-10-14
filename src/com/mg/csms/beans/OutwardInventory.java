package com.mg.csms.beans;

import java.util.Date;

public class OutwardInventory {

	private Date date;
	private String partyId;
	private Integer coldId;
	private Integer qty;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public Integer getColdId() {
		return coldId;
	}

	public void setColdId(Integer coldId) {
		this.coldId = coldId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
