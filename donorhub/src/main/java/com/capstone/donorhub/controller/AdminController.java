package com.capstone.donorhub.controller;

import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.service.AdminService;
import com.capstone.donorhub.service.AdminServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
@Autowired
private AdminServiceImpl adminServiceImpl;
    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return adminServiceImpl.getAllUsers();
    }
}