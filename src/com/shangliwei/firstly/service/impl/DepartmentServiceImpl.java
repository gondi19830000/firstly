package com.shangliwei.firstly.service.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
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

public class DepartmentServiceImpl implements IService {

	private Connection connection = null;
	private IDao dao = new DaoImpl();
	
	@Override
	public void add(Map<String, Object> form, String currentUserId) throws Exception {
		try {
			connection = DBUtil.getConnection();
			Map<String, Object> department = new HashMap<>();
			department.put("id", SequenceUtil.getUUID());
			department.put("name", form.get("name"));
			department.put("parent_id", form.get("parent_id"));
			department.put("type", form.get("type"));
			department.put("manager", form.get("manager"));
			department.put("state", "01");
			department.put("create_date", DateTimeUtil.parse((String) form.get("create_date")));
			department.put("description", form.get("description"));
			department.put("creater", currentUserId);
			department.put("creattime", DateTimeUtil.getDate());
			dao.add(department, ModelConstant.DEPARTMENT, connection);
		} finally {
			DBUtil.release(connection);
		}
	}

	@Override
	public void update(Map<String, Object> form, String currentUserId) throws Exception {
		try {
			connection = DBUtil.getConnection();
			Map<String, Object> department = new HashMap<>();
			department.put("id", form.get("id"));
			department.put("name", form.get("name"));
			department.put("parent_id", form.get("parent_id"));
			department.put("type", form.get("type"));
			department.put("manager", form.get("manager"));
			department.put("state", form.get("state"));
			department.put("create_date", DateTimeUtil.parse((String) form.get("create_date")));
			department.put("close_date", DateTimeUtil.parse((String) form.get("close_date")));
			department.put("description", form.get("description"));
			department.put("editer", currentUserId);
			department.put("edittime", DateTimeUtil.getDate());
			dao.update(department, ModelConstant.DEPARTMENT, connection);
		} finally {
			DBUtil.release(connection);
		}
	}

	@Override
	public void delete(String id, String currentUserId) throws Exception {
		try {
			connection = DBUtil.getConnection();
			dao.delete(id, ModelConstant.DEPARTMENT, connection);
		} finally {
			DBUtil.release(connection);
		}
	}

	@Override
	public Map<String, Object> queryDetail(String id) throws Exception {
		Map<String, Object> department = null;
		try {
			connection = DBUtil.getConnection();
			String[] fields = new String[] {"id","name","parent_id","type","manager","state","create_date","close_date","description","creater","creattime","editer","edittime"};
			department = dao.query(id, fields, ModelConstant.DEPARTMENT, connection);
		} finally {
			DBUtil.release(connection);
		}
		department.put("create_date", DateTimeUtil.format((Date) department.get("create_date")));
		department.put("close_date", DateTimeUtil.format((Date) department.get("close_date")));
		department.put("creattime", DateTimeUtil.format((Timestamp) department.get("creattime")));
		department.put("edittime", DateTimeUtil.format((Timestamp) department.get("edittime")));
		return department;
	}

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> condition, Map<String, Integer> pagination) throws Exception {
		List<Map<String, Object>> departmentList = null;
		try {
			connection = DBUtil.getConnection();
			String[] fields = new String[] {"id","name","parent_id","type","manager","state"};
			departmentList = dao.query(condition, pagination, fields, ModelConstant.DEPARTMENT, connection);
		} finally {
			DBUtil.release(connection);
		}
		return departmentList;
	}

	@Override
	public List<Map<String, Object>> queryPop(Map<String, Object> condition, Map<String, Integer> pagination) throws Exception {
		List<Map<String, Object>> departmentPop = null;
		try {
			connection = DBUtil.getConnection();
			String[] fields = new String[] {"id","name","parent_id","manager"};
			departmentPop = dao.query(condition, pagination, fields, ModelConstant.DEPARTMENT, connection);
		} finally {
			DBUtil.release(connection);
		}
		return departmentPop;
	}

}
