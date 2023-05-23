package com.capstone.donorhub.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.respository.ItemRepository;


@SpringBootTest
@ExtendWith({ MockitoExtension.class, SpringExtension.class })
public class ItemRepositoryTest {

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void testFindByItemName() {
        // Mock data
        String itemName = "Sample Item";
        Items item1 = new Items();
        item1.setItemName("Item1");
        Items item2 = new Items();
        item2.setItemName("Item2");
        List<Items> mockItemList = new ArrayList<>();
        mockItemList.add(item1);
        mockItemList.add(item2);

        
        when(itemRepository.findByItemName(anyString())).thenReturn(mockItemList);

        
        List<Items> result = itemRepository.findByItemName(itemName);

      
        assertEquals(2, result.size());
        assertEquals(item1, result.get(0));
        assertEquals(item2, result.get(1));
    }
}


