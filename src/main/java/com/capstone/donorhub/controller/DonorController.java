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

import com.capstone.donorhub.dto.ItemDTO;
import com.capstone.donorhub.entity.CustomUserDetail;
import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.service.DonorServiceImpl;
import com.capstone.donorhub.service.OrderServiceImpl;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/donor")
public class DonorController {

	@Autowired
	private DonorServiceImpl donorServiceImpl;

	@Autowired
	private OrderServiceImpl orderServiceImpl;

	// Endpoint - get all items
	@GetMapping("/allItems")
	public List<Items> getAllItem(org.springframework.security.core.Authentication authentication) {
		CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

		return donorServiceImpl.donorItem(userDetail.getUser().getUserId());
	}

	// Endpoit - get item by id
	@GetMapping("/items/{id}")
	public Items getItemById(@PathVariable int id) {
		return donorServiceImpl.getSingleItem(id);
	}

	// Endpoint - Add an item
	@PostMapping("/addItem")
	public String saveItem(@Valid @RequestBody ItemDTO itemDTO) {
		System.out.println(itemDTO.getUserId());
		donorServiceImpl.saveItem(itemDTO);
		return "item added";

	}

	// endpoint - delete an item
	@DeleteMapping("/deleteItem")
	public String itemDeleted(@RequestParam int itemId) {
		return donorServiceImpl.deleteItem(itemId);
	}

	// Endpoint - update an item listed
	@Transactional
	@PutMapping("/updateItem/{id}")
	public String updateItem(@PathVariable int id, @RequestBody ItemDTO itemDTO,
			org.springframework.security.core.Authentication authentication) {
		CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

		int userId = userDetail.getUser().getUserId();
		return donorServiceImpl.updateItem(id, itemDTO, userId);
	}

	// Endpoint - find item by name
	@GetMapping("/items/filterByName")
	public ResponseEntity<List<Items>> getItemByCategory(@RequestParam String name) {
		return new ResponseEntity<List<Items>>(donorServiceImpl.getItemsByName(name), HttpStatus.OK);
	}

	// Endpoint - Save order history of item
	@GetMapping("/orderHistory")
	public List<Orders> orderHistory() {
		return orderServiceImpl.getAllOrders();
	}

}
