package com.mg.csms;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestMain {

	public static void main(String[] args) {

		Date date = new Date(Integer.parseInt("2017"), Integer.parseInt("11"), Integer.parseInt("21"));

		LocalDate localDate = date.toLocalDate();
		String s = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		java.util.Date date1 = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date1 = sdf1.parse(s);
		} catch (ParseException e) {
		}
		System.out.println(date);

		System.out.println(LocalDate.now());
	}
}
