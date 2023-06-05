package com.capstone.donorhub.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.capstone.donorhub.dto.ItemDTO;

class ItemDTOTest {

    @Test
    void testItemDTO() {
        // Create a new ItemDTO object
        ItemDTO itemDTO = new ItemDTO();

        // Set up the expected values
        String expectedItemName = "Test Item";
        String expectedCategory = "Test Category";
        int expectedQuantity = 10;
        int expectedUserId = 123;
        String expectedDeliveryMode = "Test Delivery Mode";

        // Set the values using the setters
        itemDTO.setItemName(expectedItemName);
        itemDTO.setCategory(expectedCategory);
        itemDTO.setQuantity(expectedQuantity);
        itemDTO.setUserId(expectedUserId);
        itemDTO.setDeliveryMode(expectedDeliveryMode);

        // Verify that the values are set correctly
        assertEquals(expectedItemName, itemDTO.getItemName());
        assertEquals(expectedCategory, itemDTO.getCategory());
        assertEquals(expectedQuantity, itemDTO.getQuantity());
        assertEquals(expectedUserId, itemDTO.getUserId());
        assertEquals(expectedDeliveryMode, itemDTO.getDeliveryMode());

}
}
