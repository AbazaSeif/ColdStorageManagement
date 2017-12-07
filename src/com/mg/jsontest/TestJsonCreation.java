package com.mg.jsontest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Vyaapari;
import com.mg.jsonhandler.JSONWriter;

/**
 * @author Mohak Gupta
 *
 */
public class TestJsonCreation {
	@Test
	public void f() {
		ColdStorage coldObject = makeColdObject();
		ColdStorage coldObject1 = makeColdObject1();
		Map<Integer, Object> coldMap = new HashMap<>();
		coldMap.put(coldObject.getColdId(), coldObject);
		coldMap.put(coldObject1.getColdId(), coldObject1);
		new JSONWriter().writeObjectToJson("ColdStorage", coldMap);

		Vyaapari vyaapari = new Vyaapari();
		vyaapari.setVyaapariId(1);
		vyaapari.setVyaapariName("AP");

		Map<Integer, Object> vyaapariMap = new HashMap<>();
		vyaapariMap.put(vyaapari.getVyaapariId(), vyaapari);

		new JSONWriter().writeObjectToJson("Vyaapari", vyaapariMap);

	}

	private static ColdStorage makeColdObject() {
		ColdStorage cold = new ColdStorage();
		cold.setColdId(1);
		cold.setColdName("Ambey Cold");
		cold.setAddress("Kundli");
		cold.setDate(LocalDate.now());
		cold.setPhoneNo(8985878545L);

		return cold;
	}

	private static ColdStorage makeColdObject1() {
		ColdStorage cold = new ColdStorage();
		cold.setColdId(4);
		cold.setColdName("Ambey Cold");
		cold.setAddress("Kundli");
		cold.setDate(LocalDate.now());
		cold.setPhoneNo(8985878785L);

		return cold;
	}
}
