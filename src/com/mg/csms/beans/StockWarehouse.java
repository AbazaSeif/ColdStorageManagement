package com.mg.csms.beans;

import java.util.Date;

public class StockWarehouse {

	private Date date;
	private Integer hsnCode;
	private String itemName;
	private String partyId;
	private Integer coldId;
	private Integer lotNo;
	private Integer qty;
	private Integer balanceQty;
	private Integer bardana;
	private Integer floorNo;
	private Integer rackNo;

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

	public Integer getBalanceQty() {
		return balanceQty;
	}

	public void setBalanceQty(Integer balanceQty) {
		this.balanceQty = balanceQty;
	}

	public Integer getBardana() {
		return bardana;
	}

	public void setBardana(Integer bardana) {
		this.bardana = bardana;
	}

	public Integer getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(Integer floorNo) {
		this.floorNo = floorNo;
	}

	public Integer getRackNo() {
		return rackNo;
	}

	public void setRackNo(Integer rackNo) {
		this.rackNo = rackNo;
	}

}
