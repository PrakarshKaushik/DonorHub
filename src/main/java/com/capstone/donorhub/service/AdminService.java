package com.capstone.donorhub.service;

import com.capstone.donorhub.entity.User;

import java.util.List;

public interface AdminService {
	List<User> getAllUsers();

	User saveUser(User user);

	User getSingleUser(int id);

	void deleteUser(int id);

	User updatUser(User user);

	List<User> getUserByName(String Name);

	User updateUserAccountStatus(int userId, String accountStatus);
}
