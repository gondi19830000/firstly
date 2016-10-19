package com.shangliwei.firstly.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangliwei.firstly.constant.ModelConstant;
import com.shangliwei.firstly.dao.IDao;
import com.shangliwei.firstly.dao.impl.DaoImpl;
import com.shangliwei.firstly.service.IService;
import com.shangliwei.firstly.util.DBUtil;
import com.shangliwei.firstly.util.DateTimeUtil;
import com.shangliwei.firstly.util.SequenceUtil;

public class EmployeeServiceImpl implements IService {
	
	private Connection connection = null;
	private IDao dao = new DaoImpl();
	
	@Override
	public void add(Map<String, Object> form, String currentUserId) throws Exception {
		try {
			connection = DBUtil.getConnection();
			Map<String, Object> employee = new HashMap<>();
			employee.put("id", SequenceUtil.getUUID());
			employee.put("sequence", this.getMaxSequence(connection));
			employee.put("username", form.get("username"));
			employee.put("password", form.get("password"));
			employee.put("department_id", form.get("department_id"));
			employee.put("phone", form.get("phone"));
			employee.put("email", form.get("email"));
			employee.put("state", "01");
			employee.put("creater", currentUserId);
			employee.put("creattime", DateTimeUtil.getTimestamp());
			dao.add(employee, ModelConstant.EMPLOYEE, connection);
		} finally {
			DBUtil.release(connection);
		}
	}

	@Override
	public void update(Map<String, Object> form, String currentUserId) throws Exception {
		try {
			connection = DBUtil.getConnection();
			Map<String, Object> employee = new HashMap<>();
			employee.put("id", form.get("id"));
			employee.put("username", form.get("username"));
			employee.put("password", form.get("password"));
			employee.put("department_id", form.get("department_id"));
			employee.put("phone", form.get("phone"));
			employee.put("email", form.get("email"));
			employee.put("state", form.get("state"));
			employee.put("editer", currentUserId);
			employee.put("edittime", DateTimeUtil.getTimestamp());
			dao.update(employee, ModelConstant.EMPLOYEE, connection);
		} finally {
			DBUtil.release(connection);
		}
	}

	@Override
	public void delete(String id, String currentUserId) throws Exception {
		try {
			connection = DBUtil.getConnection();
			dao.delete(id, ModelConstant.EMPLOYEE, connection);
		} finally {
			DBUtil.release(connection);
		}
		
	}

	@Override
	public Map<String, Object> queryDetail(String id) throws Exception {
		Map<String, Object> employee = null;
		try {
			connection = DBUtil.getConnection();
			String[] fields = new String[] {"id","sequence","username","password","department_id","phone","email","state","creater","creattime","editer","edittime"};
			employee = dao.query(id, fields, ModelConstant.EMPLOYEE, connection);
		} finally {
			DBUtil.release(connection);
		}
		return employee;
	}

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> condition, Map<String, Integer> pagination) throws Exception {
		List<Map<String, Object>> employeeList = null;
		try {
			connection = DBUtil.getConnection();
			String[] fields = new String[] {"id","sequence","username","department_id","phone","email","state"};
			employeeList = dao.query(condition, pagination, fields, ModelConstant.EMPLOYEE, connection);
		} finally {
			DBUtil.release(connection);
		}
		return employeeList;
	}

	@Override
	public List<Map<String, Object>> queryPop(Map<String, Object> condition, Map<String, Integer> pagination) throws Exception {
		List<Map<String, Object>> employeeList = null;
		try {
			connection = DBUtil.getConnection();
			String[] fields = new String[] {"id","sequence","username","department_id"};
			employeeList = dao.query(condition, pagination, fields, ModelConstant.EMPLOYEE, connection);
		} finally {
			DBUtil.release(connection);
		}
		return employeeList;
	}
	
	private int getMaxSequence(Connection connection) throws Exception {
		int maxSequenct = 0;
		List<Map<String, Object>> employeeList = dao.query(null, null, new String[] {"sequence"}, ModelConstant.EMPLOYEE, connection);
		if (employeeList != null && employeeList.size() > 0) {
			for (Map<String, Object> employee : employeeList) {
				int sequence = Integer.valueOf(String.valueOf(employee.get("sequence")));
				if (sequence > maxSequenct) {
					maxSequenct = sequence;
				}
			}
		}
		return maxSequenct + 1;
	}

}
