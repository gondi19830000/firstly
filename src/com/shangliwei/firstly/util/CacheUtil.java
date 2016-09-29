package com.shangliwei.firstly.util;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.shangliwei.firstly.constant.ModellConstant;
import com.shangliwei.firstly.dao.IDao;
import com.shangliwei.firstly.dao.impl.DaoImpl;

public final class CacheUtil {

	private static IDao dao = new DaoImpl();
	
	public static void addUsername(List<Map<String, Object>> entitiyList, Connection connection, String ...fields) throws Exception {
		for (Map<String, Object> entitiy : entitiyList) {
			CacheUtil.addUsername(entitiy, connection, fields);
		}
	}
	
	public static void addUsername(Map<String, Object> entitiy, Connection connection, String ...fields) throws Exception {
		for (Entry<String, Object> entry : entitiy.entrySet()) {
			for (String field : fields) {
				if (field.equals(entry.getKey())) {
					Map<String, Object> employee = dao.query((String) entry.getValue(), Arrays.asList("username"), ModellConstant.EMPLOYEE, connection);
					String uername = null;
					if (employee != null) {
						uername = (String) employee.get("username");
					}
					entitiy.put(entry.getKey() + "_name", uername);
				}
			}
		}
	}
}
