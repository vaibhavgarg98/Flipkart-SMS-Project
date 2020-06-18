package com.flipcard.dateandtime;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.log4j.Logger;

public class DateAndTime {
	private static Logger log = Logger.getLogger(DateAndTime.class);
	public static void logOut(String id, String role) {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		log.info(role+" with id "+id+" logged out on "+localDate+" "+localDate.getDayOfWeek()+" at "+localTime);
	}
	public static void logIn(String id, String role) {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		log.info(role+" with id "+id+" logged in on "+localDate+" "+localDate.getDayOfWeek()+" at "+localTime);
	}
}
