package com.shangliwei.firstly.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DateTimeUtil {

	public static Timestamp getTimestamp() {
		return new Timestamp(new java.util.Date().getTime());
	}
	
	public static Date getDate() {
		return new Date(new java.util.Date().getTime());
	}
	
	public static String format(Timestamp timestamp) {
		String result = "";
		if (timestamp != null) {
			result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(timestamp);
		}
		return result;
	}
	
	public static String format(Date date) {
		String result = "";
		if (date != null) {
			result = new SimpleDateFormat("yyyy-MM-dd").format(date);
		}
		return result;
	}
	
	public static Date parse(String date) throws ParseException {
		Date dateObject = null;
		if (date != null && !"".equals(date)) {
			dateObject = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
		}
		return dateObject;
	}
	
}
