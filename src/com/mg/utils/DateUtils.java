package com.mg.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TextField;

public class DateUtils {
	
	private static final String DATEFORMAT = "dd/MM/yyyy";

	private DateUtils(){
		
	}
	
	public static Date makeDate(TextField date) {
		LocalDate localDate = LocalDate.of(Integer.parseInt(date.getText().substring(6, 10)), Integer.parseInt(date.getText().substring(3, 5)), Integer.parseInt(date.getText().substring(0, 2)));
		return Date.valueOf(localDate);
	}
	
	public static void initializeDate(TextField date)	{
		date.setPromptText(DATEFORMAT);
		date.setText(LocalDate.now().format(DateTimeFormatter.ofPattern(DATEFORMAT)));
	}
}
