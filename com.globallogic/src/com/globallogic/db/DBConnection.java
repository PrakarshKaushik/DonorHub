package com.globallogic.db;

import java.sql.*;

public class DBConnection {

	public static Connection getConnection() {

		Connection connection = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gl", "root", "Root");
		}

		catch (Exception e) {
			System.out.println("inside getConnection catch");
			e.printStackTrace();
		}
		return connection;
	}

}
