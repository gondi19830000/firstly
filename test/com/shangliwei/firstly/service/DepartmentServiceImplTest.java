package com.shangliwei.firstly.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shangliwei.firstly.service.impl.DepartmentServiceImpl;

public class DepartmentServiceImplTest {

	private IService service = new DepartmentServiceImpl();
	
	@Test
	public void testAdd() throws Exception {
		Map<String, Object> form = new HashMap<>();
		form.put("name", "≤‚ ‘≤ø√≈");
		form.put("parent_id", "185185");
		form.put("type", "01");
		form.put("manager", "admin");
		form.put("create_date", "2016-10-19");
		form.put("description", "xxxxx");
		service.add(form, "admin");
	}

	@Test
	public void testUpdate() throws Exception {
		Map<String, Object> form = service.queryDetail("910d03cd749c4004a6bff7e88ed2fbd9");
		form.put("state", "02");
		form.put("close_date", "2016-10-30");
		service.update(form, "admin");
	}

	@Test
	public void testDelete() throws Exception {
		service.delete("6367a88984924da5bfab28d7a9b46ed8", "admin");
	}

	@Test
	public void testQueryDetail() throws Exception {
		System.out.println(service.queryDetail("910d03cd749c4004a6bff7e88ed2fbd9"));
	}

	@Test
	public void testQueryList() throws Exception {
		System.out.println(service.queryList(null, null));
	}

	@Test
	public void testQueryPop() throws Exception {
		System.out.println(service.queryPop(null, null));
	}

}
