package com.capstone.donorhub.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.donorhub.dto.UserDTO;
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
       
        AdminServiceImpl adminService = mock(AdminServiceImpl.class);
        UserDTO userDTO = new UserDTO();
        User expectedUser = new User();
        when(adminService.saveUser(userDTO)).thenReturn(expectedUser);
        AdminController controller = new AdminController();
        setField(controller, "adminServiceImpl", adminService);

      
        User result = controller.saveUser(userDTO);

      
        assertEquals(expectedUser, result);
        verify(adminService, times(1)).saveUser(userDTO);
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
    
    
 
    
    //updateUser
    @Test
    public void testUpdateUser() {
        
        int userId = 1;
        UserDTO userDTO = new UserDTO();
        AdminServiceImpl adminService = mock(AdminServiceImpl.class);
        String expectedResponse = "User updated successfully.";
        when(adminService.updateUser(userId, userDTO)).thenReturn(expectedResponse);
        AdminController controller = new AdminController();
        setField(controller, "adminServiceImpl", adminService);

        
        String result = controller.updateUser(userId, userDTO);

      
        assertEquals(expectedResponse, result);
        verify(adminService, times(1)).updateUser(userId, userDTO);
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
    
    private void setField(Object targetObject, String fieldName, Object fieldValue) {
        try {
            Field field = targetObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(targetObject, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}
