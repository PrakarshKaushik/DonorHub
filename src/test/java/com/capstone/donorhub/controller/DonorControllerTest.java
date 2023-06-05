package com.capstone.donorhub.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

	/*
	 * // Test -Get All Items
	 * 
	 * @Test void testGetAllItem() { CustomUserDetail userDetail =
	 * mock(CustomUserDetail.class);
	 * when(userDetail.getUser().getUserId()).thenReturn(1);
	 * 
	 * List<Items> itemList = new ArrayList<>();
	 * when(donorService.donorItem(1)).thenReturn(itemList);
	 * 
	 * donorController.getAllItem((Authentication) userDetail);
	 * 
	 * verify(donorService).donorItem(1); }
	 */

	// Test - GetItemsById
	@Test
	void testGetItemById() {

		Items item = new Items();
		when(donorService.getSingleItem(1)).thenReturn(item);

		donorController.getItemById(1);

		verify(donorService).getSingleItem(1);
	}

	// Test - AddItem
	@Test
	void testSaveItem() {

		ItemDTO itemDTO = mock(ItemDTO.class);

		donorController.saveItem(itemDTO);

		verify(donorService).saveItem(itemDTO);
	}

	// Test - DeleteItem
	@Test
	void testItemDeleted() {

		donorController.itemDeleted(1);

		verify(donorService).deleteItem(1);
	}

	/*
	 * // Test - UpdateItemDetails
	 * 
	 * @Test void testUpdateItem() {
	 * 
	 * CustomUserDetail userDetail = mock(CustomUserDetail.class);
	 * when(userDetail.getUser().getUserId()).thenReturn(1);
	 * 
	 * ItemDTO itemDTO = mock(ItemDTO.class);
	 * 
	 * donorController.updateItem(1, itemDTO, (Authentication) userDetail);
	 * 
	 * verify(donorService).updateItem(1, itemDTO, 1); }
	 */

	// Test - FindItemByCategory
	@Test
	void testGetItemByCategory() {

		List<Items> itemList = new ArrayList<>();
		when(donorService.getItemsByName("item_name")).thenReturn(itemList);

		donorController.getItemByCategory("item_name");

		verify(donorService).getItemsByName("item_name");
	}

	// Test - ViewOrderHistory
	@Test
	void testOrderHistory() {

		List<Orders> orderList = new ArrayList<>();
		when(orderService.getAllOrders()).thenReturn(orderList);

		donorController.orderHistory();

		verify(orderService).getAllOrders();
	}
}
