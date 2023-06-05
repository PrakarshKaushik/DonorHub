package com.capstone.donorhub.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.donorhub.entity.CustomUserDetail;
import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.respository.OrderRepository;
import com.capstone.donorhub.service.NgoServiceImpl;
import com.capstone.donorhub.service.OrderServiceImpl;

@RestController
@RequestMapping("/ngo")
public class NgoController {

	@Autowired
	private NgoServiceImpl ngoServiceImpl;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private OrderRepository orderRepo;

	
	//--------------------------------------------------------------
	// Endpoint - Get All item
	@GetMapping("/allItems")
	public List<Items> getAllItem() {
		return ngoServiceImpl.getAllItems();
	}
	
	//Endpoint - Get All Orders
	@GetMapping("/NgoAllOrders")
	public List<Orders> getAllOrders(org.springframework.security.core.Authentication authentication) {
		CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

		
		return ngoServiceImpl.getAllOrders(userDetail.getUser().getUserId());
	}

	// Endpoint - Get item by Id
	@GetMapping("/items/{id}")
	public Items getItemById(@PathVariable int id) {
		return ngoServiceImpl.getSingleItem(id);
	}
	
	//------------------------------------------------------------------
	
	// Endpoint - Buy Item
		@PutMapping("/bookItems")
		public String bookItem(@RequestParam int itemId, @RequestParam int quantity, org.springframework.security.core.Authentication authentication) {
			CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

			return ngoServiceImpl.bookItem(itemId, quantity, userDetail.getUser().getUserId());

		}


	
	//----------------------------------------------------------------

	// endpoint - Get Item by Name
	@GetMapping("/items/filterByName")
	public ResponseEntity<List<Items>> getItemByCategory(@RequestParam String name) {
		return new ResponseEntity<List<Items>>(ngoServiceImpl.getItemByName(name), HttpStatus.OK);
	}
	
	//--------------------------------------------------------------
	@DeleteMapping("/cancelOrder")
	public String cancelOrder(@RequestParam int orderId) {
		

		return ngoServiceImpl.cancelOrder(orderId);
			
		
	}
	
	//---------------------------------------------------------------

}
