package com.mg.controller;

import com.mg.csms.beans.InwardStockItem;

public class BillingController  extends StockInHandController{

	@Override
	protected boolean filterStockItemCondition(InwardStockItem element) {
		return element.getBalance() == 0;
	}
}
