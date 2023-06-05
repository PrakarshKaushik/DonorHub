package com.capstone.donorhub.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemDTOTest {

    @Test
    void testItemDTO() {
        
        ItemDTO itemDTO = new ItemDTO();

    
        String expectedItemName = "Test Item";
        String expectedCategory = "Test Category";
        int expectedQuantity = 10;
        int expectedUserId = 123;
        String expectedDeliveryMode = "Test Delivery Mode";

        
        itemDTO.setItemName(expectedItemName);
        itemDTO.setCategory(expectedCategory);
        itemDTO.setQuantity(expectedQuantity);
        itemDTO.setUserId(expectedUserId);
        itemDTO.setDeliveryMode(expectedDeliveryMode);

        assertEquals(expectedItemName, itemDTO.getItemName());
        assertEquals(expectedCategory, itemDTO.getCategory());
        assertEquals(expectedQuantity, itemDTO.getQuantity());
        assertEquals(expectedUserId, itemDTO.getUserId());
        assertEquals(expectedDeliveryMode, itemDTO.getDeliveryMode());

}
}
