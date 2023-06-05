package com.capstone.donorhub.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.respository.OrderRepository;


@SpringBootTest
@ExtendWith({ MockitoExtension.class, SpringExtension.class })
public class OrderRepositoryTest {

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void testFindAllOrdersByUser() {
       
        int ngoId = 1;
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        List<Orders> mockOrderList = new ArrayList<>();
        mockOrderList.add(order1);
        mockOrderList.add(order2);

       
        when(orderRepository.findAllByUser(anyInt())).thenReturn(mockOrderList);

        
        List<Orders> result = orderRepository.findAllByUser(ngoId);

        
        assertEquals(2, result.size());
        assertEquals(order1, result.get(0));
        assertEquals(order2, result.get(1));
    }
}

