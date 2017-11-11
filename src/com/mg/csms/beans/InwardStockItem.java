package com.mg.csms.beans;

import java.io.Serializable;

/**
 * @author Mohak Gupta
 *
 */
public class InwardStockItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer stockId;
	private Integer lotNo;
	private Integer coldNo;
	private String item;
	private Integer quantity;
	private Float rate;

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

}
