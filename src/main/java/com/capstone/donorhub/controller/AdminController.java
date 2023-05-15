package com.capstone.donorhub.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.service.AdminServiceImpl;
import com.capstone.donorhub.service.OrderServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;

	//Endpoint - get all users
	@GetMapping("/allUsers")
	public List<User> getAllUsers() {
		return adminServiceImpl.getAllUsers();
	}
	
	//Endpoint - get all orders
	@GetMapping("/allOrders")
	public List<Orders> getAllOrders() {
		return orderServiceImpl.getAllOrders();
	}
	
	//Endpoint - get all items
	@GetMapping("/allItems")
	public List<Items> getAllItem() {
		return adminServiceImpl.getAllItem();
	}
	
	//Endpoint - get a user by id
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id) {
		return adminServiceImpl.getSingleUser(id);
	}
	
	//Endpoint - add a user
	@PostMapping("/saveUsers")
	public User saveUser(@Valid @RequestBody User user) {
		return adminServiceImpl.saveUser(user);
	}
	
	//Endpoint - delete a user
	@DeleteMapping("/deleteUser")
	public String userDeleted(@RequestParam int id) {
		 adminServiceImpl.deleteUser(id);
		 return "User deleted";
	}
	
	//Endpoint - delete an item
	@DeleteMapping("/deleteItem")
	public void itemDeleted(@RequestParam int id) {
		 adminServiceImpl.deleteItem(id);
	}
	
	//Endpoint - Update user
	@PutMapping("/updateUser/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		user.setUserId(id);
		return adminServiceImpl.saveUser(user);
	}
	
	//Endpoint - Find user by name
	@GetMapping("/users/filterByName")
	public ResponseEntity<List<User>> getUserByName(@RequestParam String name){
		return new ResponseEntity<List<User>>(adminServiceImpl.getUserByName(name), HttpStatus.OK);
	}
	
}
