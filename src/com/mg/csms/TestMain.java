package com.mg.csms;

import java.util.ArrayList;
import java.util.Optional;

public class TestMain {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<>();

		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		Optional<Integer> coldObject = list.stream()
				.filter(item -> item == 2).findFirst();

		System.out.println(coldObject.get());
	}
}
