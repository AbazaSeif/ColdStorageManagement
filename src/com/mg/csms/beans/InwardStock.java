package com.mg.csms.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Mohak Gupta
 *
 */
public class InwardStock extends InwardStockItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer stockId;
	private Date date;
	private String coldName;
	private String vyaapariName;
	private Integer qty;
	private String gadiNo;
	private List<InwardStockItem> inwardStockList;

	@Override
	public Integer getStockId() {
		return stockId;
	}

	@Override
	public void setStockId(Integer stockId) {
		this.stockId = stockId;
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

	public String getVyaapariName() {
		return vyaapariName;
	}

	public void setVyaapariName(String vyaapariName) {
		this.vyaapariName = vyaapariName;
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
