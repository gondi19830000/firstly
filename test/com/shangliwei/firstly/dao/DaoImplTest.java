package com.shangliwei.firstly.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.shangliwei.firstly.constant.ModelConstant;
import com.shangliwei.firstly.dao.impl.DaoImpl;
import com.shangliwei.firstly.util.DBUtil;
import com.shangliwei.firstly.util.DateTimeUtil;
import com.shangliwei.firstly.util.SequenceUtil;

public class DaoImplTest {

	private Connection connection;
	private IDao dao;
	
	@Before
	public void setUp() throws Exception {
		connection = DBUtil.getConnection();
		dao = new DaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		DBUtil.release(connection);
	}

	@Test
	public void testAdd() throws Exception {
		Map<String, Object> employee = new HashMap<>();
		employee.put("id", SequenceUtil.getUUID());
		employee.put("sequence", 1);
		employee.put("username", "shangliwei");
		employee.put("password", "123456");
		employee.put("department_id", "18500");
		employee.put("phone", "13700000000");
		employee.put("email", "shangliwei@icloud.com");
		employee.put("state", "01");
		employee.put("creater", "admin");
		employee.put("creattime", DateTimeUtil.getTimestamp());
		dao.add(employee, ModelConstant.EMPLOYEE, connection);
	}

	@Test
	public void testUpdate() throws Exception {
		String[] fields = new String[] {"id","sequence","username","password","department_id","phone","email","state","creater","creattime","editer","edittime"};
		Map<String, Object> employee = dao.query("a6da59e49fc243f4b3009ba899e8280d", fields, ModelConstant.EMPLOYEE, connection);
		employee.put("state", "02");
		employee.put("editer", "admin");
		employee.put("edittime", DateTimeUtil.getTimestamp());
		dao.update(employee, ModelConstant.EMPLOYEE, connection);
	}

	@Test
	public void testDelete() throws Exception {
		dao.delete("ce1943b6d9e54bf182cf5d5e58b92ea4", ModelConstant.EMPLOYEE, connection);
	}

	@Test
	public void testQueryStringListOfStringStringConnection() throws Exception {
		String[] fields = new String[] {"id","sequence","username","department_id","phone","email","state"};
		System.out.println(dao.query("a6da59e49fc243f4b3009ba899e8280d", fields, ModelConstant.EMPLOYEE, connection));
	}

	@Test
	public void testQueryMapOfStringObjectMapOfStringIntegerListOfStringStringConnection() throws Exception {
		String[] fields = new String[] {"id","sequence","username","department_id"};
		System.out.println(dao.query(null, null, fields, ModelConstant.EMPLOYEE, connection));
	}

}
