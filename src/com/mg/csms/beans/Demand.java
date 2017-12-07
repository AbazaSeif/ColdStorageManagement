package com.mg.csms.beans;

import java.time.LocalDate;

public class Demand {

	private LocalDate demandDate;
	private Integer demandId;
	private Integer quantity;
	private Integer coldNo;

	public LocalDate getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(LocalDate demandDate) {
		this.demandDate = demandDate;
	}

	public Integer getDemandId() {
		return demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getColdNo() {
		return coldNo;
	}

	public void setColdNo(Integer coldNo) {
		this.coldNo = coldNo;
	}

}
