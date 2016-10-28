package com.shangliwei.firstly.util;

public final class Log {

	public static final String MODE_DEBUG = "DEBUG";
	
	public static void write(String message, String mode) {
		System.out.println(DateTimeUtil.format(DateTimeUtil.getTimestamp()) + " " + mode + ": " + message);
	}
}
