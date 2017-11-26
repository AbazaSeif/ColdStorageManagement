package com.mg.csms.beans;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Mohak Gupta
 *
 */
public class InwardStockItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer recordId;
	private Integer stockId;
	private Integer lotNo;
	private Integer coldNo;
	private String item;
	private Integer quantity;
	private Float rate;
	private InwardStock inwardStock;
	private Date entryDate;
	private String gadiNo;

	public InwardStockItem() {
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Integer getLotNo() {
		return lotNo;
	}

	public void setLotNo(Integer lotNo) {
		this.lotNo = lotNo;
	}

	public Integer getColdNo() {
		return coldNo;
	}

	public void setColdNo(Integer coldNo) {
		this.coldNo = coldNo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public InwardStock getInwardStock() {
		return inwardStock;
	}

	public void setInwardStock(InwardStock inwardStock) {
		this.inwardStock = inwardStock;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getGadiNo() {
		return gadiNo;
	}

	public void setGadiNo(String gadiNo) {
		this.gadiNo = gadiNo;
	}

}
