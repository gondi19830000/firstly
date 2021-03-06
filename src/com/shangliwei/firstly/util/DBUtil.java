package com.shangliwei.firstly.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shangliwei.firstly.constant.DBConstant;

public class DBUtil {

	public static Connection getConnection() throws Exception {
		Class.forName(DBConstant.DB_ORACLE_DRIVER);
		Log.write("Get Connection...", Log.MODE_DEBUG);
		Connection connection = DriverManager.getConnection(DBConstant.DB_URL, DBConstant.DB_USER, DBConstant.DB_PASSWORD);
		return connection;
	}
	
	public static void release(Connection connection) throws Exception {
		if (connection != null) {
			Log.write("Release Connection...", Log.MODE_DEBUG);
			connection.close();
		}
	}
	
	public static void relase(PreparedStatement preparedStatement) throws Exception {
		if (preparedStatement != null) {
			Log.write("Release PreparedStatement...", Log.MODE_DEBUG);
			preparedStatement.close();
		}
	}
	
	public static void relase(ResultSet resultSet) throws Exception {
		if (resultSet != null) {
			Log.write("Release ResultSet...", Log.MODE_DEBUG);
			resultSet.close();
		}
	}
}
