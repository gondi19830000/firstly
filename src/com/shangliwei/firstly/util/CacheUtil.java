package com.shangliwei.firstly.util;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.shangliwei.firstly.constant.ModelConstant;
import com.shangliwei.firstly.dao.IDao;
import com.shangliwei.firstly.dao.impl.DaoImpl;

public final class CacheUtil {

	private static IDao dao = new DaoImpl();
	private static Connection connection = null;
	private static List<Map<String, Object>> employeeList = null;
	private static List<Map<String, Object>> departmentList = null;
	private static List<Map<String, Object>> dictionaryList = null;
	
	public static void initialize(String model) throws Exception {
		if (model == null) {
			employeeList = dao.query(null, null, new String[] {"id","username"}, model, connection);
			departmentList = dao.query(null, null, new String[] {"id","name"}, model, connection);
			dictionaryList = dao.query(null, null, new String[] {"id","code","name","type"}, model, connection);
		} else if (ModelConstant.EMPLOYEE.equals(model)) {
			employeeList = dao.query(null, null, new String[] {"id","username"}, model, connection);
		} else if (ModelConstant.DEPARTMENT.equals(model)) {
			departmentList = dao.query(null, null, new String[] {"id","name"}, model, connection);
		} else if (ModelConstant.DICITIONARY.equals(model)) {
			dictionaryList = dao.query(null, null, new String[] {"id","code","name","type"}, model, connection);
		}
	}
	
	public static String getUsername(String employeeId) throws Exception {
		String username = "";
		if (employeeList == null) {
			CacheUtil.initialize(ModelConstant.EMPLOYEE);
		}
		if (employeeList != null && employeeList.size() > 0) {
			for (Map<String, Object> employee : employeeList) {
				if (employeeId.equals(employee.get("id"))) {
					username = (String) employee.get("username");
					break;
				}
			}
		}
		return username;
	}
	
	public static String getDepartment(String departmentId) throws Exception {
		String name = "";
		if (departmentList == null) {
			CacheUtil.initialize(ModelConstant.DEPARTMENT);
		}
		if (departmentList != null && departmentList.size() > 0) {
			for (Map<String, Object> department : departmentList) {
				if (departmentId.equals(department.get("id"))) {
					name = (String) department.get("name");
					break;
				}
			}
		}
		return name;
	}
	
	public static String getDicitionaryName(String code, String type) throws Exception {
		String name = "";
		if (dictionaryList == null) {
			CacheUtil.initialize(ModelConstant.DICITIONARY);
		}
		if (dictionaryList != null && dictionaryList.size() > 0) {
			for (Map<String, Object> dicitionary : dictionaryList) {
				if (code.equals(dicitionary.get("code")) && type.equals(dicitionary.get("type"))) {
					name = (String) dicitionary.get("name");
					break;
				}
			}
		}
		return name;
	}
	
	public static void putUsername(Map<String, Object> entitiy, String[] fields) throws Exception {
		if (entitiy != null && entitiy.size() > 0) {
			
		}
	}
	
	public static void putUsername(List<Map<String, Object>> entitiy, String[] fields) throws Exception {
		
	}
	
	public static void putDepartmentName(Map<String, Object> entitiy, String[] fields) throws Exception {
		
	}
	
	public static void putDepartmentName(List<Map<String, Object>> entitiy, String[] fields) throws Exception {
		
	}
	
	public static void putDicitionaryName(Map<String, Object> entitiy, String[] fields) throws Exception {
		
	}
	
	public static void putDicitionaryName(List<Map<String, Object>> entitiy, String[] fields) throws Exception {
		
	}
	
}
