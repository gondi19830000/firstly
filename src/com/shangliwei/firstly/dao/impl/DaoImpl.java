package com.shangliwei.firstly.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.shangliwei.firstly.dao.IDao;
import com.shangliwei.firstly.util.JDBCTemplate;

public class DaoImpl extends JDBCTemplate implements IDao {

	@Override
	public void add(Map<String, Object> entitiy, String model, Connection connection) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<Object> parameters = new ArrayList<>();
		sql.append("INSERT INTO " + model);
		int count = 0;
		sql.append("(");
		for (Entry<String, Object> entry : entitiy.entrySet()) {
			sql.append(entry.getKey().toUpperCase());
			if (count < entitiy.size()-1) {
				sql.append(",");
			}
			parameters.add(entry.getValue());
			++count;
		}
		sql.append(") ");
		sql.append("VALUES(");
		for (int i=0; i<parameters.size(); i++) {
			sql.append("?");
			if (i < parameters.size()-1) {
				sql.append(",");
			}
		}
		sql.append(")");
		this.executeUpdate(sql.toString(), parameters, connection);
	}

	@Override
	public void update(Map<String, Object> entitiy, String model, Connection connection) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<Object> parameters = new ArrayList<>();
		sql.append("UPDATE " + model + " SET ");
		int count = 0;
		for (Entry<String, Object> entry : entitiy.entrySet()) {
			if ("id".equals(entry.getKey())) {
				continue;
			} else {
				sql.append(entry.getKey().toUpperCase() + "=?");
				if (count < entitiy.size()-2) {
					sql.append(",");
				} else {
					sql.append(" ");
				}
				parameters.add(entry.getValue());
				++count;				
			}
		}
		sql.append("WHERE ID=?");
		parameters.add(entitiy.get("id"));
		this.executeUpdate(sql.toString(), parameters, connection);
	}

	@Override
	public void delete(String id, String model, Connection connection) throws Exception {
		String sql = "DELETE FROM " + model + " WHERE ID=?";
		this.executeUpdate(sql, Arrays.asList(id), connection);
	}

	@Override
	public Map<String, Object> query(String id, List<String> fields, String model, Connection connection) throws Exception {
		Map<String, Object> entitiy = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		for (int i=0; i<fields.size(); i++) {
			sql.append(fields.get(i).toUpperCase());
			if (i < fields.size()-1) {
				sql.append(",");
			} else {
				sql.append(" ");
			}
		}
		sql.append("FROM " + model + " WHERE ID=?");
		List<Map<String, Object>> result = this.executeQuery(sql.toString(), Arrays.asList(id), connection);
		if (result != null && result.size() > 0) {
			entitiy = result.get(0);
		}
		return entitiy;
	}

	@Override
	public List<Map<String, Object>> query(Map<String, Object> condition, Map<String, Integer> pagination, List<String> fields, String model, Connection connection) throws Exception {
		List<Map<String, Object>> entitiyList = null;
		StringBuffer sql = new StringBuffer();
		List<Object> parameters = new ArrayList<>();
		sql.append("SELECT ");
		for (int i=0; i<fields.size(); i++) {
			sql.append(fields.get(i));
			if (i < fields.size()-1) {
				sql.append(",");
			} else {
				sql.append(" ");
			}
		}
		sql.append("FROM " + model + " WHERE 1=1 ");
		if (condition != null && condition.size() > 0) {
			for (Entry<String, Object> entry : condition.entrySet()) {
				sql.append("AND " + entry.getKey() + " LIKE ? ");
				parameters.add("'%" + entry.getValue() + "%'");
			}
		}
		if (pagination != null && pagination.size() > 0) {
			sql.append("AND ROWNUM BETWEEN ? AND ? ");
			parameters.add(pagination.get("beginRow"));
			parameters.add(pagination.get("endRow"));
		}
		List<Map<String, Object>> result = this.executeQuery(sql.toString(), parameters, connection);
		if (result != null && result.size() > 0) {
			entitiyList = result;
		}
		return entitiyList;
	}

}
