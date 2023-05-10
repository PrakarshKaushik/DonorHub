package com.capstone.donorhub.service;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.UserRepository;

@Service
public class AdminServiceImpl {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

<<<<<<< HEAD
    public User saveUser(User userEntity) {
=======
	public User getSingleUser(int id) {
		java.util.Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return userOptional.get();
		}
		throw new RuntimeException("User not found for id: " + id);

	}
>>>>>>> 572be39fe4e3a85649a90dec4babb67e934808a2

	public void deleteUser(int id) {
		userRepository.deleteById(id);

<<<<<<< HEAD
    public User getSingleUser(int id) {
        java.util.Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new RuntimeException("User not found for id: "+id);
=======
	}

	public User updatUser(User user) {
		return userRepository.save(user);
	}
>>>>>>> 572be39fe4e3a85649a90dec4babb67e934808a2

	public List<User> getUserByName(String name) {

<<<<<<< HEAD
    public void deleteUser(int id) {
        userRepository.deleteById(id);

    }
 
    public User updatUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUserByName(String name) {

        return userRepository.findByName(name);
    }
=======
		return userRepository.findByName(name);
	}
	
>>>>>>> 572be39fe4e3a85649a90dec4babb67e934808a2
}
