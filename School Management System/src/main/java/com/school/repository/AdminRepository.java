package com.school.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DbConnection;
import com.school.to.UserTo;

public class AdminRepository {

	Connection connection = DbConnection.getConnection();

	public List<UserTo> findAllStudentsAndTeachers() {
		List<UserTo> users = new ArrayList<>();
		// finding all users (student and teacher)
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where role='student' or role='teacher'");

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("userId");
				String name = resultSet.getString("completeName");
				String email = resultSet.getString("email");
				String role = resultSet.getString("role");
				String accountStatus = resultSet.getString("accountStatus");
				String userClass = resultSet.getString("userClass");

				UserTo user = new UserTo(id, name, email, null, role, accountStatus, userClass);
				users.add(user);
			}

			return users;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

}
//by PRAKARSH KAUSHIK (2536930)