package com.school.service;

import com.school.repository.UserRepository;
import com.school.to.UserTo;

public class UserService {

	UserRepository userRepository = new UserRepository();

	// checks email and password of user for login
	public UserTo login(UserTo user) {

		return userRepository.findUserByEmailAndPassword(user);
	}

	// saves user filled data
	public int register(UserTo user) {
		return userRepository.saveUser(user);
	}

	// check for already existing user by email
	public boolean checkUser(String email) {

		return userRepository.checkUserAlreadyExists(email);
	}

	// searches user by email
	public UserTo getOneUserByEmail(String email) {

		return userRepository.findOneUser(email);
	}

	// changes user data
	public int updateUser(UserTo user) {
		return userRepository.changeUser(user);
	}

	// deletes user data
	public void delete(String email) {
		userRepository.removeUser(email);
	}

}
//by PRAKARSH KAUSHIK (2536930)