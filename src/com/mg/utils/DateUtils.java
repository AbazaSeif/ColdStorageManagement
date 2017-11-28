package com.mg.utils;

import java.sql.Date;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;

public class DateUtils {

	private static final String DATEFORMAT = "dd/MM/yyyy";

	private DateUtils(){}

	public static Date makeDate(DatePicker date) {
		return Date.valueOf(date.getValue());
	}

	public static void initializeDate(DatePicker date)	{
		date.setPromptText(DATEFORMAT);
		date.setValue(LocalDate.now());
	}
}
