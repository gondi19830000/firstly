package com.shangliwei.firstly.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shangliwei.firstly.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest {

	private IService service = new EmployeeServiceImpl();
	
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
	public void testUpdate() throws Exception {
		Map<String, Object> form = service.queryDetail("5dd058bb56b54e8d8eceee800394dd52");
		form.put("state", "02");
		service.update(form, "admin");
	}

	@Test
	public void testDelete() throws Exception {
		service.delete("0c2dba9e71e145e1b43e2e2a3b8853e5", "admin");
	}

	@Test
	public void testQueryDetail() throws Exception {
		System.out.println(service.queryDetail("11165853ca46440bbb7ddf8584e30376"));
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
