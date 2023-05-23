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
import com.capstone.donorhub.service.DonorServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class DonorControllerTest {

    @Mock
    private DonorServiceImpl donorService;
    
    @InjectMocks
    private DonorController donorController;
    
    private Items testItem;
    
    @BeforeEach
    public void setup() {
        testItem = new Items();
        testItem.setItemId(1);
        testItem.setItemName("Item 1");
    }
    
    //GetAllItems
    @Test
    public void testGetAllItem() {
        List<Items> items = new ArrayList<>();
        items.add(testItem);
        
        when(donorService.getAllItem()).thenReturn(items);
        
        List<Items> result = donorController.getAllItem();
        
        assertEquals(items, result);
    }
    
    //GetItemByItem
    @Test
    public void testGetItemById() {
        int itemId = 1;
        
        when(donorService.getSingleItem(itemId)).thenReturn(testItem);
        
        Items result = donorController.getItemById(itemId);
        
        assertEquals(testItem, result);
    }
    
    //AddItem
    @Test
    public void testSaveItem() {
        when(donorService.saveItem(any(Items.class))).thenReturn(testItem);
        
        String result = donorController.saveItem(testItem);
        
        assertEquals("item added", result);
    }
    
    //DeleteItem
    @Test
    public void testItemDeleted() {
        int itemId = 1;
        String expectedResponse = "Item deleted";
        
        when(donorService.deleteItem(itemId)).thenReturn(expectedResponse);
        
        String result = donorController.itemDeleted(itemId);
        
        assertEquals(expectedResponse, result);
    }
    
    //UpdateItem
    @Test
    public void testUpdateItem() {
        int itemId = 1;
        
        when(donorService.saveItem(any(Items.class))).thenReturn(testItem);
        
        String result = donorController.updateItem(itemId, testItem);
        
        assertEquals("Item update", result);
        assertEquals(itemId, testItem.getItemId());
    }
    
    
    //GetItemByCategory
    @Test
    public void testGetItemByCategory() {
        String name = "Item 1";
        List<Items> items = new ArrayList<>();
        items.add(testItem);
        
        when(donorService.getItemsByName(name)).thenReturn(items);
        
        ResponseEntity<List<Items>> response = donorController.getItemByCategory(name);
        
        assertEquals(items, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    //Save Item Order
    @Test
    public void testSaveItemOrder() {
        when(donorService.saveItem(any(Items.class))).thenReturn(testItem);
        
        Items result = donorController.saveItemOrder(testItem);
        
        assertEquals(testItem, result);
    }
}
