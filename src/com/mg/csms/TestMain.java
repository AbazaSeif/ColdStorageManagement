package com.mg.csms;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.csms.beans.ColdStorage;

public class TestMain {

	public static void main(String[] args) {
		ColdStorage coldObject = makeColdObject();
		ColdStorage coldObject1 = makeColdObject1();
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("D:\\cold.json");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
			}

		try {
			Map<Integer, ColdStorage> coldMap = mapper.readValue(file, new TypeReference<Map<Integer, ColdStorage>>(){});
			coldMap.put(coldObject.getColdId(), coldObject);
			coldMap.put(coldObject1.getColdId(), coldObject1);
			mapper.writeValue(file, coldMap);

			coldMap = mapper.readValue(file, new TypeReference<Map<Integer, ColdStorage>>(){});

			System.out.println(coldMap.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private static ColdStorage makeColdObject1() {
		ColdStorage cold = new ColdStorage();
		cold.setColdId(4);
		cold.setColdName("Ambey Cold");
		cold.setAddress("Kundli");
		cold.setDate(Date.valueOf(LocalDate.now()));
		cold.setPhoneNo(8985878785L);

		return cold;
	}
}
