package com.capstone.donorhub.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.OrderRepository;
import com.capstone.donorhub.respository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderServiceImpl orderService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	// Test - GetAllOrders
	@Test
	public void testGetAllOrders() {

		List<Orders> expectedOrders = new ArrayList<>();
		expectedOrders.add(new Orders());
		when(orderRepository.findAll()).thenReturn(expectedOrders);

		List<Orders> actualOrders = orderService.getAllOrders();

		assertEquals(expectedOrders, actualOrders);
		verify(orderRepository, times(1)).findAll();
	}

	// Test - Place Order When User Exists
	@Test
	public void testPlaceOrder_WhenUserExists() {

		int userId = 1;
		Items item = new Items();
		item.setQuantity(5);
		User user = new User();
		user.setUserId(userId);

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		Orders order = orderService.placeOrder(item, userId);

		verify(orderRepository).save(order);

		assertEquals(item.getQuantity(), order.getQuantity());
		assertEquals(item, order.getItem());
		assertEquals(user, order.getUser());
	}

	// Test - Place Order When User does not Exists
	@Test
	public void testPlaceOrder_WhenUserDoesNotExist() {

		int userId = 1;
		Items item = new Items();
		item.setQuantity(5);

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		Orders order = orderService.placeOrder(item, userId);

		verify(orderRepository).save(order);

		assertEquals(item.getQuantity(), order.getQuantity());
		assertEquals(item, order.getItem());
		assertEquals(null, order.getUser());
	}
}
