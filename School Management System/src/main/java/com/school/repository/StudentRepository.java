package com.school.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.school.db.DbConnection;
import com.school.to.UserTo;

public class StudentRepository {

	Connection connection = DbConnection.getConnection();

	public List<UserTo> findAllStudents() {

		List<UserTo> users = new ArrayList<>();

		try {

			// display all students

			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where role='student'");

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

	public int changeStudent(UserTo user) {
		int status = 0;

		// update details
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update users set completeName=?,password=?,accountStatus=?,userClass=? where email=?");
			preparedStatement.setString(1, user.getCompleteName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getAccountStatus());
			preparedStatement.setString(4, user.getUserClass());
			preparedStatement.setString(5, user.getUserEmail());

			status = preparedStatement.executeUpdate();

			return status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
//by PRAKARSH KAUSHIK (2536930)