package com.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.dto.AdminOrderDTO;
import com.restaurant.model.User;
import com.restaurant.service.AdminService;
import com.restaurant.service.OrderService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
    private AdminService adminService;
	@Autowired
	private OrderService orderService;

    @GetMapping("/customers")
    public List<User> getAllCustomers() {
        return adminService.getAllCustomers();
    }
    
    @GetMapping("/orders")
    public ResponseEntity<List<AdminOrderDTO>> getAllOrdersForAdmin() {
        return ResponseEntity.ok(orderService.getAllOrdersForAdmin());
    }


}
