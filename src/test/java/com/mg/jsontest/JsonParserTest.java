package com.mg.jsontest;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.mg.utils.JSONParser;

public class JsonParserTest {
	@Test
	public void f() {
		Map<Integer, Object> coldMap = new HashMap<>();
		coldMap = JSONParser.getObjectFromJsonFile("ColdStorage");
		System.out.println(coldMap.size());
	}
}
