package com.capstone.donorhub.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.service.HomeServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @Mock
    private HomeServiceImpl homeService;
    
    @InjectMocks
    private HomeController homeController;
    
    private User testUser;
    
    @BeforeEach
    public void setup() {
        testUser = new User();
        testUser.setUserId(1);
        testUser.setName("testuser");
        testUser.setEmail("test@example.com");
    }
    
    @Test
    public void testLogin() {
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Login");
        
        ResponseEntity<String> result = homeController.login();
        
        assertEquals(expectedResponse, result);
    }
    
    @Test
    public void testDonorUser() {
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("I'm a donor");
        
        ResponseEntity<String> result = homeController.donorUser();
        
        assertEquals(expectedResponse, result);
    }
    
    @Test
    public void testNgoUser() {
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("I'm a NGO");
        
        ResponseEntity<String> result = homeController.ngoUser();
        
        assertEquals(expectedResponse, result);
    }
    
    @Test
    public void testAdminUser() {
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("I'm an admin");
        
        ResponseEntity<String> result = homeController.adminUser();
        
        assertEquals(expectedResponse, result);
    }
    
    @Test
    public void testGuestUser() {
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("I'm a guest");
        
        ResponseEntity<String> result = homeController.guestUser();
        
        assertEquals(expectedResponse, result);
    }
    
    @Test
    public void testRegisterUser() {
        when(homeService.registerUser(any(User.class))).thenReturn(testUser);
        
        User result = homeController.registerUser(testUser);
        
        assertEquals(testUser, result);
    }
}
