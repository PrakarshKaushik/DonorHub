package com.capstone.donorhub.service;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.UserRepository;

@Service
public class AdminServiceImpl {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User getSingleUser(int id) {
		java.util.Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return userOptional.get();
		}
		throw new RuntimeException("User not found for id: " + id);

	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);

	}

	public User updateUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public List<User> getUserByName(String name) {

		return userRepository.findByName(name);
	}
	
}
