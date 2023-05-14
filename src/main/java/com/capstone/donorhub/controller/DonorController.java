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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.service.AdminServiceImpl;
import com.capstone.donorhub.service.DonorServiceImpl;

import jakarta.validation.Valid;

@RestController
public class DonorController {
	
	@Autowired
	private DonorServiceImpl donorServiceImpl;

	@GetMapping("/allItems")
	public List<Items> getAllItem() {
		return donorServiceImpl.getAllItem();
	}
	
	@GetMapping("/items/{id}")
	public Items getItemById(@PathVariable int id) {
		return donorServiceImpl.getSingleItem(id);
	}
	
	@PostMapping("/Saveitems")
	public String saveItem(@Valid @RequestBody Items item) {
		 donorServiceImpl.saveItem(item);
		 return "item added";
		 
	}
	
	@DeleteMapping("/Deleteitems")
	public String itemDeleted(@RequestParam int itemId) {
		 donorServiceImpl.deleteItem(itemId);
		 return "item deleted";
	}
	
	@PutMapping("/UpdateItems/{id}")
	public String updateItem(@PathVariable int id, @RequestBody Items item) {
		item.setItemId(id);
	 donorServiceImpl.saveItem(item);
	 return "Item update";
	}
	
	@GetMapping("/items/filterByName")
	public ResponseEntity<List<Items>> getItemByCategory(@RequestParam String name){
		return new ResponseEntity<List<Items>>(donorServiceImpl.getItemsByName(name), HttpStatus.OK);
	}
	
	@PostMapping("/OrderHistory")
	public Items saveItemOrder(@Valid @RequestBody Items item) {
		return donorServiceImpl.saveItem(item);
	}
	

}
