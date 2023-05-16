package com.capstone.donorhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.respository.OrderRepository;
import com.capstone.donorhub.respository.UserRepository;

@Service
public class NgoServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderServiceImpl orderServiceImpl;

	public List<Items> getAllItems() {
		return itemRepository.findAll();
	}

	public List<Orders> getAllOrders(int ngoId) {

		return orderRepository.findAllOrdersByUser(ngoId);
	}

	public User register(User user) {

		return userRepository.save(user);
	}

	public Items getSingleItem(int id) {
		java.util.Optional<Items> itemOptional = itemRepository.findById(id);
		if (itemOptional.isPresent()) {
			return itemOptional.get();
		}
		throw new RuntimeException("Item not found for id: " + id);

	}

//	    public void deleteUser(int id) {
//	        userRepository.deleteById(id);
//
//	    }

	public Items bookItem(int itemId, int quantity, int ngoId) {
		itemRepository.findById(itemId);
		Optional<Items> itemOptional = itemRepository.findById(itemId);
		Items bookedItem = null;
		Items orederedItem = null;

		if (itemOptional.isPresent()) {
			bookedItem = itemOptional.get();
			int oldQuantity = bookedItem.getQuantity();

			bookedItem.setQuantity(quantity);
			orderServiceImpl.placeOrder(bookedItem, ngoId);
			bookedItem.setQuantity(oldQuantity - quantity);
			itemRepository.save(bookedItem);

			bookedItem.setQuantity(quantity);

		}
		return bookedItem;

	}

	public List<Items> getItemByName(String name) {

		return itemRepository.findByItemName(name);
	}

}
