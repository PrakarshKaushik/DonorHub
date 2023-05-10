package com.capstone.donorhub.service;

import com.capstone.donorhub.entity.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUsers();
    User saveUserEntity(User user);
    User getSingleUser(int id);
    void deleteUser(int id);
    User updatUserEntity (User user);

    List<User> getUserByName(String Name);
}
