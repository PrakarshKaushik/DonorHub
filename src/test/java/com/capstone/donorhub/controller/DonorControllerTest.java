package com.capstone.donorhub.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.capstone.donorhub.dto.ItemDTO;
import com.capstone.donorhub.entity.CustomUserDetail;
import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.service.DonorServiceImpl;
import com.capstone.donorhub.service.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
class DonorControllerTest {

	@Mock
	private DonorServiceImpl donorService;

	@Mock
	private OrderServiceImpl orderService;

	@InjectMocks
	private DonorController donorController;

	@Test
	void testGetAllItem() {
		// Mock authentication object
		CustomUserDetail userDetail = mock(CustomUserDetail.class);
		when(userDetail.getUser().getUserId()).thenReturn(1);

		// Mock donorServiceImpl method
		List<Items> itemList = new ArrayList<>();
		when(donorService.donorItem(1)).thenReturn(itemList);

		// Call the controller method
		donorController.getAllItem((Authentication) userDetail);

		// Verify donorServiceImpl method was called
		verify(donorService).donorItem(1);
	}

	@Test
	void testGetItemById() {
		// Mock donorServiceImpl method
		Items item = new Items();
		when(donorService.getSingleItem(1)).thenReturn(item);

		// Call the controller method
		donorController.getItemById(1);

		// Verify donorServiceImpl method was called
		verify(donorService).getSingleItem(1);
	}

	@Test
	void testSaveItem() {
		// Mock request body
		ItemDTO itemDTO = mock(ItemDTO.class);

		// Call the controller method
		donorController.saveItem(itemDTO);

		// Verify donorServiceImpl method was called
		verify(donorService).saveItem(itemDTO);
	}

	@Test
	void testItemDeleted() {
		// Call the controller method
		donorController.itemDeleted(1);

		// Verify donorServiceImpl method was called
		verify(donorService).deleteItem(1);
	}

	@Test
	void testUpdateItem() {
		// Mock authentication object
		CustomUserDetail userDetail = mock(CustomUserDetail.class);
		when(userDetail.getUser().getUserId()).thenReturn(1);

		// Mock request body
		ItemDTO itemDTO = mock(ItemDTO.class);

		// Call the controller method
		donorController.updateItem(1, itemDTO, (Authentication) userDetail);

		// Verify donorServiceImpl method was called
		verify(donorService).updateItem(1, itemDTO, 1);
	}

	@Test
	void testGetItemByCategory() {
		// Mock response entity
		List<Items> itemList = new ArrayList<>();
		ResponseEntity<List<Items>> responseEntity = new ResponseEntity<>(itemList, HttpStatus.OK);

		// Mock donorServiceImpl method
		when(donorService.getItemsByName("item_name")).thenReturn(itemList);

		// Call the controller method
		donorController.getItemByCategory("item_name");

		// Verify donorServiceImpl method was called
		verify(donorService).getItemsByName("item_name");
	}

	@Test
	void testOrderHistory() {
		 // Mock orderService method
		List<Orders> orderList = new ArrayList<>();
		when(orderService.getAllOrders()).thenReturn(orderList);

		// Call the controller method
		donorController.orderHistory();

		// Verify orderService method was called
		verify(orderService).getAllOrders();
	}
}
