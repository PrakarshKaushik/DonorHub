package com.capstone.donorhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.service.NgoServiceImpl;

@RestController
public class NgoController {
	
	@Autowired
	private NgoServiceImpl ngoServiceImpl;

	@GetMapping("/allItemsNGO")
	public List<Items> getAllItem() {
		return ngoServiceImpl.getAllItems();
	}
	
	@GetMapping("/allOrdersNGO")
	public List<Orders> getAllOrders(@RequestParam int ngoId) {
		return ngoServiceImpl.getAllOrders(ngoId);
	}
	
	
	@GetMapping("/itemsNgo/{id}")
	public Items getItemById(@PathVariable int id) {
		return ngoServiceImpl.getSingleItem(id);
	}

	
	@PutMapping("/bookItems")
	public Items bookItem(@RequestParam int itemId, @RequestParam int quantity, @RequestParam int ngoId) {
		return ngoServiceImpl.bookItem(itemId, quantity,ngoId);
	}
	
	@GetMapping("/items/filterByNameNgo")
	public ResponseEntity<List<Items>> getItemByCategory(@RequestParam String name){
		return new ResponseEntity<List<Items>>(ngoServiceImpl.getItemByName(name), HttpStatus.OK);
	}
	

}
