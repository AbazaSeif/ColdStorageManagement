package com.mg.csms;

import java.sql.Date;
import java.time.LocalDate;

import com.mg.csms.beans.ColdStorage;

public class TestMain {

	public static void main(String[] args) {
		ColdStorage coldObject = makeColdObject();

	}

	private static ColdStorage makeColdObject() {
		ColdStorage cold = new ColdStorage();
		cold.setColdId(1);
		cold.setColdName("Ambey Cold");
		cold.setAddress("Kundli");
		cold.setDate(Date.valueOf(LocalDate.now()));
		cold.setPhoneNo(8985878545L);

		return cold;
	}
}
