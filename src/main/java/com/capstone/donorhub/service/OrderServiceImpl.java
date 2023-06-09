package com.capstone.donorhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.entity.CustomUserDetail;
import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.OrderRepository;
import com.capstone.donorhub.respository.UserRepository;

@Service
public class OrderServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	// GetAllOrders
	public List<Orders> getAllNgoOrders(Authentication authentication) {
		CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
		System.out.println(userDetail.getUser().getName());
		System.out.println(userDetail.getUser().getUserId());

		return orderRepository.findAllOrdersByNgoId(userDetail.getUser().getUserId());
	}

//AllOrders
	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	// Place Order
	public Orders placeOrder(Items item, int l) {
		Orders order = new Orders();

		order.setQuantity(item.getQuantity());
		order.setItem(item);
		Optional<User> user = userRepository.findById((int) l);
		if (user.isPresent()) {
			order.setUser(user.get());
		}

		orderRepository.save(order);

		return order;
	}

}
