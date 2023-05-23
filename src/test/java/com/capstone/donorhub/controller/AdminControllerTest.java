package com.capstone.donorhub.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.service.AdminServiceImpl;
import com.capstone.donorhub.service.OrderServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
  
    @Mock
    private AdminServiceImpl adminService;
    
    @Mock
    private OrderServiceImpl orderService;
    
    @InjectMocks
    private AdminController adminController;
    
    private User testUser;
    private Orders testOrder;
    private Items testItem;
    
    @BeforeEach
    public void setup() {
        testUser = new User();
        testUser.setUserId(1);
        testUser.setName("John Doe");
        
        testOrder = new Orders();
        testOrder.setOrderId(1);
        testOrder.setUser(testUser);
        
        testItem = new Items();
        testItem.setItemId(1);
        testItem.setItemName("Item 1");
    }
    
    //GetAllUsers
    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(testUser);
        
        when(adminService.getAllUsers()).thenReturn(users);
        
        List<User> result = adminController.getAllUsers();
        
        assertEquals(users, result);
    }
    
    //GetAllOrders
    @Test
    public void testGetAllOrders() {
        List<Orders> orders = new ArrayList<>();
        orders.add(testOrder);
        
        when(orderService.getAllOrders()).thenReturn(orders);
        
        List<Orders> result = adminController.getAllOrders();
        
        assertEquals(orders, result);
    }
    
    //GetAllItems
    @Test
    public void testGetAllItems() {
        List<Items> items = new ArrayList<>();
        items.add(testItem);
        
        when(adminService.getAllItem()).thenReturn(items);
        
        List<Items> result = adminController.getAllItem();
        
        assertEquals(items, result);
    }
    
    //GetUserById
    @Test
    public void testGetUserById() {
        int userId = 1;
        
        when(adminService.getSingleUser(userId)).thenReturn(testUser);
        
        User result = adminController.getUserById(userId);
        
        assertEquals(testUser, result);
    }
    
    //AddUser
    @Test
    public void testSaveUser() {
        when(adminService.saveUser(any(User.class))).thenReturn(testUser);
        
        User result = adminController.saveUser(testUser);
        
        assertEquals(testUser, result);
    }
    
    //DeleteUser
    @Test
    public void testUserDeleted() {
        int userId = 1;
        String expectedResponse = "User deleted";
        
        when(adminService.deleteUser(userId)).thenReturn(expectedResponse);
        
        String result = adminController.userDeleted(userId);
        
        assertEquals(expectedResponse, result);
    }
    
    
    //DeleteItem
    @Test
    public void testItemDeleted() {
        int itemId = 1;
        
        assertDoesNotThrow(() -> adminController.itemDeleted(itemId));
        
        verify(adminService, times(1)).deleteItem(itemId);
    }
    
    //updateUser
    @Test
    public void testUpdateUser() {
        int userId = 1;
        
        when(adminService.saveUser(any(User.class))).thenReturn(testUser);
        
        User result = adminController.updateUser(userId, testUser);
        
        assertEquals(testUser, result);
        assertEquals(userId, testUser.getUserId());
    }
    
    //GetUserByName
    @Test
    public void testGetUserByName() {
        String name = "John Doe";
        List<User> users = new ArrayList<>();
        users.add(testUser);
        
        when(adminService.getUserByName(name)).thenReturn(users);
        
        ResponseEntity<List<User>> response = adminController.getUserByName(name);
        
        assertEquals(users, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    //UpdateAccountStatus
    @Test
    public void testUpdateAccountStatus() {
        int userId = 1;
        String accountStatus = "ACTIVE";
        
        when(adminService.updateUserAccountStatus(userId, accountStatus)).thenReturn(testUser);
        
        ResponseEntity<User> response = adminController.updateAccountStatus(userId, accountStatus);
        
        assertEquals(testUser, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}