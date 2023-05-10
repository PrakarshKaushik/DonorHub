package com.capstone.donorhub.controller;

import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.service.AdminService;
import com.capstone.donorhub.service.AdminServiceImpl;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
	@Autowired
	private AdminServiceImpl adminServiceImpl;

	@GetMapping("/allUsers")
	public List<User> getAllUsers() {
		return adminServiceImpl.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id) {
		return adminServiceImpl.getSingleUser(id);
	}
	
	@PostMapping("/users")
	public User saveUser(@Valid @RequestBody User user) {
		return adminServiceImpl.saveUser(user);
	}
	
	@DeleteMapping("/users")
	public void userDeleted(@RequestParam int id) {
		 adminServiceImpl.deleteUser(id);
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		user.setUserId(id);
		return adminServiceImpl.saveUser(user);
	}
	
	@GetMapping("/users/filterByName")
	public ResponseEntity<List<User>> getUserByName(@RequestParam String name){
		return new ResponseEntity<List<User>>(adminServiceImpl.getUserByName(name), HttpStatus.OK);
	}
}