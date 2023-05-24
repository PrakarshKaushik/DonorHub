package com.capstone.donorhub.controller;

import java.util.List;
import java.util.Optional;

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
import com.capstone.donorhub.respository.ItemRepository;
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
	
	@Autowired
	private ItemRepository itemRepository;
	

	// Endpoint - get all items
	@GetMapping("/allItems")
	public List<Items> getAllItem() {
		return donorServiceImpl.getAllItem();
	}

	// Endpoit - get item by id
	@GetMapping("/items/{id}")
	public Items getItemById(@PathVariable int id) {
		return donorServiceImpl.getSingleItem(id);
	}

	// Endpoint - Add an item
	@PostMapping("/addItem")
	public String saveItem(@Valid @RequestBody Items item) {
		donorServiceImpl.saveItem(item);
		return "item added";

	}

	// endpoint - delete an item
	@DeleteMapping("/deleteItem")
	public String itemDeleted(@RequestParam int itemId) {
		donorServiceImpl.deleteItem(itemId);
		return "item deleted";
	}

	// Endpoint - update an item listed
	@Transactional
	@PutMapping("/updateItem/{id}")
	public String updateItem(@PathVariable int id, @RequestBody Items item) {
		
		return donorServiceImpl.updateItem(id, item);
//		return "Item updated";
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
