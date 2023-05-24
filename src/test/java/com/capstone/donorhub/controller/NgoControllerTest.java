package com.capstone.donorhub.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.respository.OrderRepository;
import com.capstone.donorhub.service.NgoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NgoControllerTest {

	@Mock
	private NgoServiceImpl ngoService;

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private NgoController ngoController;

	private Items testItem;
	private Orders testOrder;

	@BeforeEach
	public void setup() {
		testItem = new Items();
		testItem.setItemId(1);
		testItem.setItemName("Item 1");

		testOrder = new Orders();
		testOrder.setOrderId(1);
		testOrder.setItem(testItem);
		testOrder.setQuantity(5);
	}

	// GetAllItems
	@Test
	public void testGetAllItem() {
		List<Items> items = new ArrayList<>();
		items.add(testItem);

		when(ngoService.getAllItems()).thenReturn(items);

		List<Items> result = ngoController.getAllItem();

		assertEquals(items, result);
	}

	// GetAllOrder
	@Test
	public void testGetAllOrders() {
		int ngoId = 1;
		List<Orders> orders = new ArrayList<>();
		orders.add(testOrder);

		when(ngoService.getAllOrders(ngoId)).thenReturn(orders);

		List<Orders> result = ngoController.getAllOrders(ngoId);

		assertEquals(orders, result);
	}

	// GetItemById
	@Test
	public void testGetItemById() {
		int itemId = 1;

		when(ngoService.getSingleItem(itemId)).thenReturn(testItem);

		Items result = ngoController.getItemById(itemId);

		assertEquals(testItem, result);
	}

	// Book Item
	@Test
	public void testBookItem() {
		int itemId = 1;
		int quantity = 5;
		int ngoId = 1;
		String expectedResponse = "Item booked";

		when(ngoService.bookItem(itemId, quantity, ngoId)).thenReturn(expectedResponse);

		String result = ngoController.bookItem(itemId, quantity, ngoId);

		assertEquals(expectedResponse, result);
	}

	// GetItemByCategory
	@Test
	public void testGetItemByCategory() {
		String name = "Item 1";
		List<Items> items = new ArrayList<>();
		items.add(testItem);

		when(ngoService.getItemByName(name)).thenReturn(items);

		ResponseEntity<List<Items>> response = ngoController.getItemByCategory(name);

		assertEquals(items, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	// CancelOrder
	@Test
	public void testCancelOrder() {
		int orderId = 1;
		String expectedResponse = "Order canceled";

		when(ngoService.cancelOrder(orderId)).thenReturn(expectedResponse);

		String result = ngoController.cancelOrder(orderId);

		assertEquals(expectedResponse, result);
	}
}
