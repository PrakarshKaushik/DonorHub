package com.capstone.donorhub.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.service.DonorServiceImpl;

@ExtendWith(MockitoExtension.class)
class DonorServiceImplTest {

	@Mock
	private ItemRepository itemRepository;
	
	@InjectMocks
	private DonorServiceImpl donorService;

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

	

	// Test case for deleteItem(int id) - when item present
	 @Test
	    void testDeleteItem_ItemPresent() {
	        int itemId = 4;
	        
	        // Mocking the behavior of itemRepository.findById
	        Items item = new Items();
	        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
	        
	        // Perform the deleteItem operation
	        String result = donorService.deleteItem(itemId);
	        
	        // Verify that deleteById method is called once with the correct itemId
	        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(itemId);
	        
	        // Add assertions to verify the return value
	        assertEquals("item deleted", result, "The deleteItem method should return 'item deleted'.");
	    }
	    
	// Test case for deleteItem(int id) - when item not present
	    @Test
	    void testDeleteItem_ItemNotPresent() {
	        int itemId = 4;
	        
	        // Mocking the behavior of itemRepository.findById
	        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());
	        
	        // Perform the deleteItem operation
	        String result = donorService.deleteItem(itemId);
	        
	        // Verify that deleteById method is not called
	        Mockito.verify(itemRepository, Mockito.never()).deleteById(anyInt());
	        
	        // Add assertions to verify the return value
	        assertEquals("No such item is present", result, "The deleteItem method should return 'No such item is present'.");
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
	    void testGetItemsByName_ItemsFound() {
	        String itemName = "item";

	        // Mocking the behavior of itemRepository.findByItemName
	        List<Items> itemList = new ArrayList<>();
	        itemList.add(new Items());
	        when(itemRepository.findByItemName(itemName)).thenReturn(itemList);

	        // Perform the getItemsByName operation
	        List<Items> result = donorService.getItemsByName(itemName);

	        // Verify that findByItemName method is called once with the correct itemName
	        verify(itemRepository, times(1)).findByItemName(itemName);

	        // Add assertions to verify the result
	        assertEquals(itemList, result, "The getItemsByName method should return the list of items.");
	    }

	    @Test
	    void testGetItemsByName_ItemsNotFound() {
	        String itemName = "item";

	        // Mocking the behavior of itemRepository.findByItemName
	        when(itemRepository.findByItemName(itemName)).thenReturn(new ArrayList<>());

	        // Perform the getItemsByName operation
	        List<Items> result = donorService.getItemsByName(itemName);

	        // Verify that findByItemName method is called once with the correct itemName
	        verify(itemRepository, times(1)).findByItemName(itemName);

	        // Add assertions to verify the result
	        assertEquals(new ArrayList<>(), result, "The getItemsByName method should return an empty list.");
	    }
	}


