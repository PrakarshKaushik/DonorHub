package com.capstone.donorhub.service;

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

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.respository.OrderRepository;
import com.capstone.donorhub.respository.UserRepository;


class OrderServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
       
    }

    // Test case for getAllOrders()
    @Test
    void testGetAllOrders() {
     
        List<Orders> orders = new ArrayList<>();
        orders.add(new Orders());
        when(orderRepository.findAll()).thenReturn(orders);

        
        List<Orders> result = orderService.getAllOrders();

      
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository).findAll();
    }

    // Test case for placeOrder(Items item, int ngoId)
    @Test
    void testPlaceOrder() {
        Mockito.mock(User.class);
        Mockito.mock(Items.class);
        Mockito.mock(OrderServiceImpl.class);
    	
        Items item = new Items();
        int ngoId = 1;
        User user = new User();
        item.setUser(user);
        Optional<User> userOptional = Optional.of(user);
        Orders order = new Orders();
        when(itemRepository.save(item)).thenReturn(item);
        when(userRepository.findById(ngoId)).thenReturn(userOptional);
        when(orderRepository.save(order)).thenReturn(order);


        Orders result = orderService.placeOrder(item, ngoId);

     
        assertNotNull(result);
        assertEquals(order, result);
        assertEquals(user, result.getUser());
        assertEquals(item, result.getItem());
        verify(itemRepository).save(item);
        verify(userRepository).findById(ngoId);
        verify(orderRepository).save(order);
    }



}
