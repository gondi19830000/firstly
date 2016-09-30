package com.shangliwei.firstly.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shangliwei.firstly.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {

	IEmployeeService service = new EmployeeServiceImpl();
	
	@Test
	public void testAdd() throws Exception {
		Map<String, Object> form = new HashMap<>();
		form.put("username", "shangliwei10000");
		form.put("password", "123456");
		form.put("department_id", "1850010000");
		form.put("phone", "13700000000");
		form.put("email", "shanglwiei10000@icloud.com");
		service.add(form, "admin");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryDetail() throws Exception {
		System.out.println(service.queryDetail("5dd058bb56b54e8d8eceee800394dd52"));
	}

	@Test
	public void testQueryList() throws Exception {
		System.out.println(service.queryList(null, null));
	}

	@Test
	public void testQueryPop() throws Exception {
		System.out.println(service.queryList(null, null));
	}

}
