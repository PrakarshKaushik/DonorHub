package com.capstone.donorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

 
    private String email;
   private String password;
    private String role;
    private String name;
    private String address;
    private long contact;
    private String account_status = "inactive";
    
}

