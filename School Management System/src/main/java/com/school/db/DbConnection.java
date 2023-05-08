package com.school.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public static Connection getConnection() {

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "Root");

			return connection;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return connection;
	}
}
//by PRAKARSH KAUSHIK (2536930)