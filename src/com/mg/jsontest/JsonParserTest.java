package com.mg.jsontest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.mg.jsonhandler.JSONParser;

/**
 * @author Mohak Gupta
 *
 */
public class JsonParserTest {
	@Test
	public void f() {
		Map<Integer, Object> coldMap = new HashMap<>();
		try {
			coldMap = new JSONParser().getObjectFromJsonFile("ColdStorage");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(coldMap.size());
	}
}
