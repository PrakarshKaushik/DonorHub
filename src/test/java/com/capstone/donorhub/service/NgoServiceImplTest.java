package com.capstone.donorhub.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.respository.OrderRepository;
import com.capstone.donorhub.respository.UserRepository;

@ExtendWith(MockitoExtension.class)
class NgoServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private ItemRepository itemRepository;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private OrderServiceImpl orderServiceImpl;

	@InjectMocks
	private NgoServiceImpl ngoService;

	// GetAllItems
	@Test
	void testGetAllItems() {

		List<Items> items = new ArrayList<>();
		items.add(new Items());
		items.add(new Items());
		when(itemRepository.findAll()).thenReturn(items);

		List<Items> result = ngoService.getAllItems();

		assertEquals(2, result.size());
		verify(itemRepository, times(1)).findAll();
	}

	// GetAllOrders
	@Test
	void testGetAllOrders() {

		int ngoId = 1;
		List<Orders> orders = new ArrayList<>();
		orders.add(new Orders());
		orders.add(new Orders());
		when(orderRepository.findAllByUser(ngoId)).thenReturn(orders);

		List<Orders> result = ngoService.getAllOrders(ngoId);

		assertEquals(2, result.size());
		verify(orderRepository, times(1)).findAllByUser(ngoId);
	}

//GetSingleItem - Item Present
	@Test
	void testGetSingleItem() {

		int itemId = 1;
		Items item = new Items();
		when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

		Items result = ngoService.getSingleItem(itemId);

		assertNotNull(result);
		assertSame(item, result);
		verify(itemRepository, times(1)).findById(itemId);
	}

	// BookItem - Quantity Available
	@Test
	void testBookItem_QuantityAvailable() {

		Items item = new Items();
		item.setItemId(1);
		item.setQuantity(5);
		when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));

		when(orderServiceImpl.placeOrder(any(Items.class), anyInt())).thenReturn(new Orders());

		String result = ngoService.bookItem(1, 2, 1);

		assertEquals("Item booked and will be delivered on time", result);
		assertEquals(2, item.getQuantity());
		verify(itemRepository, times(2)).findById(anyInt()); // Adjusted verification
		verify(itemRepository, times(1)).save(any(Items.class));
		verify(orderServiceImpl, times(1)).placeOrder(any(Items.class), anyInt());
	}
	


	// GetSingleItem - Item Not Present
	@Test
	    void testGetSingleItem_ItemNotFound() {
	        
	        when(itemRepository.findById(anyInt())).thenReturn(Optional.empty());

	        
	        Items result = ngoService.getSingleItem(1);

	     
	        assertNull(result);
	        verify(itemRepository, times(1)).findById(anyInt());
	    }

	// GetItemByName - Item Not Found
	@Test
	void testGetItemByName_ItemNotFound() {
		// Prepare test data
		String name = "item";
		List<Items> items = new ArrayList<>();
		when(itemRepository.findByItemName(name)).thenReturn(items);

		List<Items> result = ngoService.getItemByName(name);

		assertTrue(result.isEmpty());
		verify(itemRepository, times(1)).findByItemName(name);
	}

//CancelOrder- Order Exists
	@Test
	void testCancelOrder_OrderExists() {

		int orderId = 1;
		Orders order = new Orders();
		Items item = new Items();
		item.setQuantity(5);
		order.setItem(item);
		order.setQuantity(3);
		Optional<Orders> orderOptional = Optional.of(order);
		when(orderRepository.findById(orderId)).thenReturn(orderOptional);
		when(itemRepository.findById(item.getItemId())).thenReturn(Optional.of(item));

		String result = ngoService.cancelOrder(orderId);

		assertEquals("Deleted", result);
		verify(orderRepository, times(1)).findById(orderId);
		verify(itemRepository, times(1)).findById(item.getItemId());
		verify(itemRepository, times(1)).save(item);
		verify(orderRepository, times(1)).deleteById(orderId);
	}

//CancelOrder - Order Does Not Exists
	@Test
	void testCancelOrder_OrderNotExists() {

		int orderId = 1;
		Optional<Orders> orderOptional = Optional.empty();
		when(orderRepository.findById(orderId)).thenReturn(orderOptional);

		String result = ngoService.cancelOrder(orderId);

		assertEquals("order not present", result);
		verify(orderRepository, times(1)).findById(orderId);
		verify(itemRepository, never()).findById(anyInt());
		verify(itemRepository, never()).save(any());
		verify(orderRepository, never()).deleteById(anyInt());
	}

}
