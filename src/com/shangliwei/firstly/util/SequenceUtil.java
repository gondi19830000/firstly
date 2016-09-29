package com.shangliwei.firstly.util;

import java.util.UUID;

public final class SequenceUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
