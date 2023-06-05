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
import org.springframework.security.core.Authentication;
import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.OrderRepository;
import com.capstone.donorhub.service.NgoServiceImpl;
import com.capstone.donorhub.entity.CustomUserDetail;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;

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

	// Test - GetAllItems
	@Test
	public void testGetAllItem() {
		List<Items> items = new ArrayList<>();
		items.add(testItem);

		when(ngoService.getAllItems()).thenReturn(items);

		List<Items> result = ngoController.getAllItem();

		assertEquals(items, result);
	}

	// Test - GetAllOrder

	@Test
	public void testGetAllOrders() {

		NgoServiceImpl ngoService = mock(NgoServiceImpl.class);
		Authentication authentication = mock(Authentication.class);
		CustomUserDetail userDetail = mock(CustomUserDetail.class);
		List<Orders> expectedOrders = new ArrayList<>();
		when(authentication.getPrincipal()).thenReturn(userDetail);
		when(userDetail.getUser()).thenReturn(mock(User.class));
		when(ngoService.getAllOrders(anyInt())).thenReturn(expectedOrders);
		NgoController controller = new NgoController();
		setField(controller, "ngoServiceImpl", ngoService);

		List<Orders> result = controller.getAllOrders(authentication);

		assertEquals(expectedOrders, result);
		verify(ngoService, times(1)).getAllOrders(anyInt());
	}

	// Test - GetItemById
	@Test
	public void testGetItemById() {
		int itemId = 1;

		when(ngoService.getSingleItem(itemId)).thenReturn(testItem);

		Items result = ngoController.getItemById(itemId);

		assertEquals(testItem, result);
	}

	// Test - Book Item
	@Test
	public void testBookItem() {

		NgoServiceImpl ngoService = mock(NgoServiceImpl.class);
		int itemId = 123;
		int quantity = 2;
		int userId = 456;
		String expectedResponse = "Success";
		Authentication authentication = mock(Authentication.class);
		CustomUserDetail userDetail = mock(CustomUserDetail.class);
		User user = mock(User.class);
		when(authentication.getPrincipal()).thenReturn(userDetail);
		when(userDetail.getUser()).thenReturn(user);
		when(user.getUserId()).thenReturn(userId);
		when(ngoService.bookItem(itemId, quantity, userId)).thenReturn(expectedResponse);
		NgoController controller = new NgoController();
		setField(controller, "ngoServiceImpl", ngoService);

		String result = controller.bookItem(itemId, quantity, authentication);

		assertEquals(expectedResponse, result);
		verify(ngoService, times(1)).bookItem(itemId, quantity, userId);
	}

	// Test - GetItemByCategory
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

	// Test - CancelOrder
	@Test
	public void testCancelOrder() {
		int orderId = 1;
		String expectedResponse = "Order canceled";

		when(ngoService.cancelOrder(orderId)).thenReturn(expectedResponse);

		String result = ngoController.cancelOrder(orderId);

		assertEquals(expectedResponse, result);
	}

	private void setField(Object targetObject, String fieldName, Object fieldValue) {
		try {
			Field field = targetObject.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(targetObject, fieldValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
