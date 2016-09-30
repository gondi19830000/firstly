package com.shangliwei.firstly.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface IDao {

	public void add(Map<String, Object> entitiy, String model, Connection connection) throws Exception;
	public void update(Map<String, Object> entitiy, String model, Connection connection) throws Exception;
	public void delete(String id, String model, Connection connection) throws Exception;
	public Map<String, Object> query(String id, String[] fields, String model, Connection connection) throws Exception;
	public List<Map<String, Object>> query(Map<String, Object> condition, Map<String, Integer> pagination, String[] fields, String model, Connection connection) throws Exception;
	
}
