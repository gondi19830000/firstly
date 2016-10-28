package com.shangliwei.firstly.util;

import org.junit.Test;

public class DBUtilTest {

	@Test
	public void testGetConnection() throws Exception {
		System.out.println(DBUtil.getConnection());
	}
	
	@Test
	public void testReleaseConnection() throws Exception {
		DBUtil.release(DBUtil.getConnection());
	}

}
