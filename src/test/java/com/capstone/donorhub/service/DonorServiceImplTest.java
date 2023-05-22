package com.capstone.donorhub.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.service.DonorServiceImpl;

class DonorServiceImplTest {

	@Mock
	private ItemRepository itemRepository;

	@InjectMocks
	private DonorServiceImpl donorService;

	@BeforeEach
	void setUp() {

	}

	// Test GetAllItems
	@Test
	void testGetAllItem() {

		List<Items> items = new ArrayList<>();
		items.add(new Items());
		when(itemRepository.findAll()).thenReturn(items);

		List<Items> result = donorService.getAllItem();

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(itemRepository).findAll();
	}

	// Test case for saveItem(Items itemEntity)
	@Test
	void testSaveItem() {

		Items item = new Items();
		when(itemRepository.save(item)).thenReturn(item);

		Items result = donorService.saveItem(item);

		assertNotNull(result);
		assertEquals(item, result);
		verify(itemRepository).save(item);
	}

	// Test case for getSingleItem(int id)
	@Test
	void testGetSingleItem_ExistingId() {

		int itemId = 1;
		Items item = new Items();
		Optional<Items> itemOptional = Optional.of(item);
		when(itemRepository.findById(itemId)).thenReturn(itemOptional);

		Items result = donorService.getSingleItem(itemId);
		assertNotNull(result);
		assertEquals(item, result);
		verify(itemRepository).findById(itemId);
	}

	// Test case for getSingleItem(int id) - when id does not exists
	@Test
	void testGetSingleItem_NonExistingId() {

		int itemId = 1;
		when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> donorService.getSingleItem(itemId));
		verify(itemRepository).findById(itemId);
	}

	// Test case for deleteItem(int id)
	@Test
	void testDeleteItem() {

		int itemId = 1;

		donorService.deleteItem(itemId);

		verify(itemRepository).deleteById(itemId);
	}

	// Test case for updateItem(Items item)
	@Test
	void testUpdateItem() {

		Items item = new Items();
		when(itemRepository.save(item)).thenReturn(item);

		Items result = donorService.updateItem(item);

		assertNotNull(result);
		assertEquals(item, result);
		verify(itemRepository).save(item);
	}

	// Test case for getItemsByName(String name)
	@Test
	void testGetItemsByName() {

		String itemName = "ItemName";
		List<Items> items = new ArrayList<>();
		items.add(new Items());
		when(itemRepository.findByItemName(itemName)).thenReturn(items);

		List<Items> result = donorService.getItemsByName(itemName);

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(itemRepository).findByItemName(itemName);
	}

}
