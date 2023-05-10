package com.capstone.donorhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.service.AdminServiceImpl;
import com.capstone.donorhub.service.HomeServiceImpl;

import jakarta.validation.Valid;

public class HomeController {
	
	@Autowired
	HomeServiceImpl homeServiceImpl;

	@GetMapping("/donor")
	public ResponseEntity<String> donorUser() {
		return ResponseEntity.ok("I'm a donor");
	}

	@GetMapping("/ngo")
	public ResponseEntity<String> ngoUser() {
		return ResponseEntity.ok("I'm a NGO");
	}

	@GetMapping("/admin")
	public ResponseEntity<String> adminUser() {
		return ResponseEntity.ok("I'm an admin");
	}

	@GetMapping("/guest")
	public ResponseEntity<String> guestUser() {
		return ResponseEntity.ok("I'm a guest");
	}
	
	@PostMapping("/register")
	public User registerUser(@Valid @RequestBody User user) {
		return homeServiceImpl.registerUser(user);
	}

}
