package com.school.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.school.db.DbConnection;
import com.school.to.UserTo;

public class UserRepository {

	Connection connection = DbConnection.getConnection();

	public int saveUser(UserTo user) {
		int status = 0;
		try {
			// add user info to database from form
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into users (completeName, email, password, role, accountStatus, userClass) values (?,?,?,?,?,?)");

			preparedStatement.setString(1, user.getCompleteName());
			preparedStatement.setString(2, user.getUserEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getAccountStatus());
			preparedStatement.setString(6, user.getUserClass());

			status = preparedStatement.executeUpdate();

			return status;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public UserTo findUserByEmailAndPassword(UserTo user) {
		UserTo authorizedUser = null;
		try { // checking user id and pass then displaying info
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where email=? and password=?");

			preparedStatement.setString(1, user.getUserEmail());
			preparedStatement.setString(2, user.getPassword());

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String completeName = resultSet.getString("completeName");
				String role = resultSet.getString("role");
				String accountStatus = resultSet.getString("accountStatus");
				String userClass = resultSet.getString("userClass");
				authorizedUser = new UserTo(userId, completeName, user.getUserEmail(), user.getPassword(), role,
						accountStatus, userClass);

			}

			return authorizedUser;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return authorizedUser;
	}

	public boolean checkUserAlreadyExists(String email) {
		boolean status = false;
		try { // displaying user with specific email id if it already exists
			PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email=?");

			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				status = true;
			}

			return status;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public UserTo findOneUser(String email) {
		UserTo user = null;

		try {
//finding user by email
			PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email=?");

			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("userId");
				String name = resultSet.getString("completeName");
				String userEmail = resultSet.getString("email");
				String role = resultSet.getString("role");
				String accountStatus = resultSet.getString("accountStatus");
				String userClass = resultSet.getString("userClass");
				String userPassword = resultSet.getString("password");

				user = new UserTo(id, name, email, userPassword, role, accountStatus, userClass);
			}

			return user;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public int changeUser(UserTo user) {
		int status = 0;
		try { // updating user info
			PreparedStatement preparedStatement = connection.prepareStatement(
					"update users set completeName=?, email=?, password=?, role=?, accountStatus=?, userClass=? where userId=?");
			preparedStatement.setString(1, user.getCompleteName());
			preparedStatement.setString(2, user.getUserEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getAccountStatus());
			preparedStatement.setString(6, user.getUserClass());
			preparedStatement.setInt(7, user.getUserId());

			status = preparedStatement.executeUpdate();

			return status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public void removeUser(String email) {
		try { // delete statement
			PreparedStatement preparedStatement = connection.prepareStatement("delete from users where email=?");
			preparedStatement.setString(1, email);

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
//by PRAKARSH KAUSHIK (2536930)