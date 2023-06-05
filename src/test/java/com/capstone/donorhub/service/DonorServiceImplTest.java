package com.capstone.donorhub.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capstone.donorhub.dto.ItemDTO;
import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.User;
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
	    public void testDonorItem() {
	        List<Items> expectedItems = Arrays.asList(new Items(), new Items());

	        when(itemRepository.donorItems(anyInt())).thenReturn(expectedItems);

	        
	        int id = 123;
	        List<Items> actualItems = donorService.donorItem(id);

	      
	        assertEquals(expectedItems, actualItems);

	    
	        verify(itemRepository, times(1)).donorItems(id);
	    }

	// Test case for saveItem(Items itemEntity)
	 @Test
	    public void testSaveItem() {
	       
	        ItemDTO itemDTO = new ItemDTO();
	        itemDTO.setUserId(123);
	        itemDTO.setItemName("Test Item");
	        itemDTO.setQuantity(5);

	       
	        Items savedItem = new Items();
	        savedItem.setItemId(1);
	        savedItem.setItemName("Test Item");
	        savedItem.setQuantity(5);

	       
	        when(itemRepository.save(any(Items.class))).thenReturn(savedItem);

	        
	        Items result = donorService.saveItem(itemDTO);

	        
	        assertEquals(savedItem, result);

	      
	        verify(itemRepository, times(1)).save(any(Items.class));
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
	        
	      
	        Items item = new Items();
	        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
	        
	       
	        String result = donorService.deleteItem(itemId);
	        
	      
	        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(itemId);
	        
	       
	        assertEquals("item deleted", result, "The deleteItem method should return 'item deleted'.");
	    }
	    
	// Test case for deleteItem(int id) - when item not present
	    @Test
	    void testDeleteItem_ItemNotPresent() {
	        int itemId = 4;
	        
	        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());
	        
	        String result = donorService.deleteItem(itemId);
	    
	        Mockito.verify(itemRepository, Mockito.never()).deleteById(anyInt());
	    
	        assertEquals("No such item is present", result, "The deleteItem method should return 'No such item is present'.");
	    }
	
	// Test case for updateItem(Items item)
	    @Test
	    public void testUpdateItem_ItemFoundAndBelongsToUser() {
	        int itemId = 1;
	        int userId = 1;

	       
	        ItemDTO itemDTO = new ItemDTO();
	        itemDTO.setItemName("Updated Item");
	        itemDTO.setQuantity(10);
	     

	      
	        Items oldItem = new Items();
	        oldItem.setItemId(itemId);
	        oldItem.setItemName("Old Item");
	        oldItem.setQuantity(5);
	        User user = new User();
	        user.setUserId(userId);
	        oldItem.setUser(user);
	       
	        Optional<Items> optionalOldItem = Optional.of(oldItem);

	       
	        when(itemRepository.findById(itemId)).thenReturn(optionalOldItem);

	        
	        String result = donorService.updateItem(itemId, itemDTO, userId);

	       
	        verify(itemRepository, times(1)).findById(itemId);

	        
	        verify(itemRepository, times(1)).save(oldItem);

	    
	        assertEquals("Item updated", result);
	        assertEquals(itemDTO.getItemName(), oldItem.getItemName());
	        assertEquals(itemDTO.getQuantity(), oldItem.getQuantity());
	    
	    }

	    @Test
	    public void testUpdateItem_ItemNotFoundOrDoesNotBelongToUser() {
	        int itemId = 1;
	        int userId = 2;

	     
	        ItemDTO itemDTO = new ItemDTO();
	        itemDTO.setItemName("Updated Item");
	        itemDTO.setQuantity(10);
	        
	        Optional<Items> optionalOldItem = Optional.empty();

	      
	        when(itemRepository.findById(itemId)).thenReturn(optionalOldItem);

	      
	        String result = donorService.updateItem(itemId, itemDTO, userId);

	        
	        verify(itemRepository, times(1)).findById(itemId);

	       
	        verify(itemRepository, never()).save(any(Items.class));

	       
	        assertEquals("Item not found or does not belong to the user", result);
	    }
	// Test case for getItemsByName(String name)
	   @Test
	    void testGetItemsByName_ItemsFound() {
	        String itemName = "item";

	     
	        List<Items> itemList = new ArrayList<>();
	        itemList.add(new Items());
	        when(itemRepository.findByItemName(itemName)).thenReturn(itemList);

	       
	        List<Items> result = donorService.getItemsByName(itemName);

	        verify(itemRepository, times(1)).findByItemName(itemName);

	        assertEquals(itemList, result, "The getItemsByName method should return the list of items.");
	    }

	    @Test
	    void testGetItemsByName_ItemsNotFound() {
	        String itemName = "item";

	        when(itemRepository.findByItemName(itemName)).thenReturn(new ArrayList<>());

	        List<Items> result = donorService.getItemsByName(itemName);

	        verify(itemRepository, times(1)).findByItemName(itemName);

	        assertEquals(new ArrayList<>(), result, "The getItemsByName method should return an empty list.");
	    }
	}


