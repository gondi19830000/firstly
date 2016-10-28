package com.shangliwei.firstly.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class JDBCTemplate {

	public void executeUpdate(String sql, List<Object> parameters, Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			for (int i=0; i<parameters.size(); i++) {
				preparedStatement.setObject(i+1, parameters.get(i));
			}
			Log.write("Execute SQL: " + sql, Log.MODE_DEBUG);
			preparedStatement.executeUpdate();
		} finally {
			DBUtil.relase(preparedStatement);
		}
	}
	
	public List<Map<String, Object>> executeQuery(String sql, List<Object> parameters, Connection connection) throws Exception {
		List<Map<String, Object>> entitiyList = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			if (parameters != null) {
				for (int i=0; i<parameters.size(); i++) {
					preparedStatement.setObject(i+1, parameters.get(i));
				}
			}
			Log.write("Execute SQL: " + sql, Log.MODE_DEBUG);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Map<String, Object> entitiy = new HashMap<>();
				ResultSetMetaData metaData = resultSet.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i=0; i<columnCount; i++) {
					String columnName = metaData.getColumnName(i+1);
					Object colunmValue = this.columnValueOracleToJavaType(resultSet.getObject(columnName));
					entitiy.put(columnName.toLowerCase(), colunmValue);
				}
				entitiyList.add(entitiy);
			}
		} finally {
			DBUtil.relase(preparedStatement);
			DBUtil.relase(resultSet);
		}
		return entitiyList;
	}
	
	private Object columnValueOracleToJavaType(Object columnValue) throws Exception {
		if (columnValue != null) {
			if ("oracle.sql.TIMESTAMP".equals(columnValue.getClass().getName())) {
				Log.write("Column type convert: oracle.sql.TIMESTAMP to java.sql.Timestamp", Log.MODE_DEBUG);
				columnValue = ((oracle.sql.TIMESTAMP) columnValue).timestampValue();
			} else if ("java.math.BigDecimal".equals(columnValue.getClass().getName())) {
				Log.write("Column type convert: java.math.BigDecimal to int", Log.MODE_DEBUG);
				columnValue = ((java.math.BigDecimal) columnValue).intValue();
			}
		}
		return columnValue;
	}
}
