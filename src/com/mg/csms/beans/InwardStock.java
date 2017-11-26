package com.mg.csms.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author Mohak Gupta
 *
 */
public class InwardStock implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer stockId;
	private Date date;
	private Integer coldId;
	private Integer vyaapariId;
	private Integer qty;
	private String gadiNo;
	private List<InwardStockItem> inwardStockList;

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getColdId() {
		return coldId;
	}

	public void setColdId(Integer coldId) {
		this.coldId = coldId;
	}

	public Integer getVyaapariId() {
		return vyaapariId;
	}

	public void setVyaapariId(Integer vyaapariId) {
		this.vyaapariId = vyaapariId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getGadiNo() {
		return gadiNo;
	}

	public void setGadiNo(String gadiNo) {
		this.gadiNo = gadiNo;
	}

	public List<InwardStockItem> getInwardStockList() {
		return inwardStockList;
	}

	public void setInwardStockList(List<InwardStockItem> inwardStockList) {
		this.inwardStockList = inwardStockList;
	}

}
