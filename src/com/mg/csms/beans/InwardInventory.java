package com.mg.csms.beans;

import java.io.Serializable;
import java.util.Date;

public class InwardInventory implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date date;
	private Integer hsnCode = 0;
	private String itemName;
	private String partyId;
	private Integer coldId = 0;
	private Integer lotNo = 0;
	private Integer qty = 0;
	private Integer bardana = 0;
	private String gadiNo;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(Integer hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public Integer getLotNo() {
		return lotNo;
	}

	public void setLotNo(Integer lotNo) {
		this.lotNo = lotNo;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getBardana() {
		return bardana;
	}

	public void setBardana(Integer bardana) {
		this.bardana = bardana;
	}

	public String getGadiNo() {
		return gadiNo;
	}

	public void setGadiNo(String gadiNo) {
		this.gadiNo = gadiNo;
	}

}
