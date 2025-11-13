package com.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.model.User;
import com.restaurant.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
    private AdminService adminService;

    @GetMapping("/customers")
    public List<User> getAllCustomers() {
        return adminService.getAllCustomers();
    }

}
