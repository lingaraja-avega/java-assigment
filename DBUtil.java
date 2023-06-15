package com.avega.training.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBUtil {

	private static final String DB_DRIVER_CLASS = "org.postgresql.Driver";
	private static final String DB_USERNAME = "postgres";
	private static final String DB_PASSWORD = "root";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/jdbc";
	private static Connection connection = null;
	private static Logger logger = Logger.getLogger(DBUtil.class.getName());

	static {
		try {
			Class.forName(DB_DRIVER_CLASS);
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		logger.info("Successfully connected to database");
		return connection;
	}

}
