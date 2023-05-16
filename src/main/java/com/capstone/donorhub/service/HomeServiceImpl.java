package com.capstone.donorhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.UserRepository;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}