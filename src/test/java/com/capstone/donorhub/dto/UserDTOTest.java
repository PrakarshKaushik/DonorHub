package com.capstone.donorhub.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.capstone.donorhub.dto.UserDTO;

class UserDTOTest {

    @Test
    void testUserDTO() {
       
        UserDTO userDTO = new UserDTO();

     
        String expectedEmail = "test@example.com";
        String expectedPassword = "password";
        String expectedRole = "user";
        String expectedName = "John Doe";
        String expectedAddress = "123 Main St";
        long expectedContact = 1234567890;
        String expectedAccountStatus = "inactive";

       
        userDTO.setEmail(expectedEmail);
        userDTO.setPassword(expectedPassword);
        userDTO.setRole(expectedRole);
        userDTO.setName(expectedName);
        userDTO.setAddress(expectedAddress);
        userDTO.setContact(expectedContact);
        userDTO.setAccount_status(expectedAccountStatus);

       
        assertEquals(expectedEmail, userDTO.getEmail());
        assertEquals(expectedPassword, userDTO.getPassword());
        assertEquals(expectedRole, userDTO.getRole());
        assertEquals(expectedName, userDTO.getName());
        assertEquals(expectedAddress, userDTO.getAddress());
        assertEquals(expectedContact, userDTO.getContact());
        assertEquals(expectedAccountStatus, userDTO.getAccount_status());
    }
}
