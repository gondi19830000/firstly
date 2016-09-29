package com.shangliwei.firstly.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangliwei.firstly.constant.ModellConstant;
import com.shangliwei.firstly.dao.IDao;
import com.shangliwei.firstly.dao.impl.DaoImpl;
import com.shangliwei.firstly.util.CacheUtil;
import com.shangliwei.firstly.util.DBUtil;
import com.shangliwei.firstly.util.DateTimeUtil;
import com.shangliwei.firstly.util.SequenceUtil;

public class EmployeeServiceImpl implements IEmployeeService {
	
	private Connection connection = null;
	private IDao dao = new DaoImpl();
	
	@Override
	public void add(Map<String, Object> form, String currentUserId) throws Exception {
		try {
			connection = DBUtil.getConnection();
			Map<String, Object> employee = new HashMap<>();
			employee.put("id", SequenceUtil.getUUID());
			employee.put("username", form.get("username"));
			employee.put("password", form.get("password"));
			employee.put("department_id", form.get("department_id"));
			employee.put("phone", form.get("phone"));
			employee.put("email", form.get("email"));
			employee.put("state", form.get("state"));
			employee.put("creater", currentUserId);
			employee.put("creattime", DateTimeUtil.getTimestamp());
			dao.add(employee, ModellConstant.EMPLOYEE, connection);
		} finally {
			DBUtil.release(connection);
		}
	}

	@Override
	public void update(Map<String, Object> form, String currentUserId) throws Exception {
		try {
			connection = DBUtil.getConnection();
			Map<String, Object> employee = new HashMap<>();
			employee.put("id", SequenceUtil.getUUID());
			employee.put("username", form.get("username"));
			employee.put("password", form.get("password"));
			employee.put("department_id", form.get("department_id"));
			employee.put("phone", form.get("phone"));
			employee.put("email", form.get("email"));
			employee.put("state", form.get("state"));
			employee.put("editer", currentUserId);
			employee.put("edittime", DateTimeUtil.getTimestamp());
			dao.update(employee, ModellConstant.EMPLOYEE, connection);
		} finally {
			DBUtil.release(connection);
		}
	}

	@Override
	public void delete(String id, String currentUserId) throws Exception {
		try {
			connection = DBUtil.getConnection();
			dao.delete(id, ModellConstant.EMPLOYEE, connection);
		} finally {
			DBUtil.release(connection);
		}
		
	}

	@Override
	public Map<String, Object> queryDetail(String id) throws Exception {
		Map<String, Object> employee = null;
		try {
			connection = DBUtil.getConnection();
			List<String> fields = new ArrayList<>();
			fields.add("id");
			fields.add("sequence");
			fields.add("username");
			fields.add("password");
			fields.add("department_id");
			fields.add("phone");
			fields.add("email");
			fields.add("creater");
			fields.add("creattime");
			fields.add("editer");
			fields.add("edittime");
			employee = dao.query(id, fields, ModellConstant.EMPLOYEE, connection);
			if (employee != null) {
				CacheUtil.addUsername(employee, connection, new String[] {"creater", "editer"});
			}
		} finally {
			DBUtil.release(connection);
		}
		return employee;
	}

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> condition, Map<String, Integer> pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryPop(Map<String, Object> condition, Map<String, Integer> pagination) {
		// TODO Auto-generated method stub
		return null;
	}

}
