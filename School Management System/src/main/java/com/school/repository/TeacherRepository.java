package com.school.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.school.db.DbConnection;
import com.school.to.UserTo;

public class TeacherRepository {
	Connection connection = DbConnection.getConnection();

	public int changeTeacher(UserTo user) {
		int status = 0;
		try { // update student details
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

	public UserTo findTeacherbyId(int id) {
		UserTo user = null;
		try { // finding teacher by id
			PreparedStatement preparedStatement = connection.prepareStatement("select * from users where userId=?");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String name = resultSet.getString("completeName");
				String email = resultSet.getString("email");
				String role = resultSet.getString("role");
				String accountStatus = resultSet.getString("accountStatus");
				String userClass = resultSet.getString("userClass");
				String password = resultSet.getString("password");
				int teacherId = resultSet.getInt("userId");

				user = new UserTo(teacherId, name, email, password, role, accountStatus, userClass);
			}

			return user;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
//by PRAKARSH KAUSHIK (2536930)