package com.restaurant.controller;

import com.restaurant.dto.OrderSummaryDTO;
import com.restaurant.model.Order;
import com.restaurant.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    

    // ==========================================================
    // 3️⃣ GET ALL ORDERS OF SPECIFIC USER
    // ==========================================================
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderSummaryDTO>> getUserOrders(
            @PathVariable Long userId
    ) {
        List<OrderSummaryDTO> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }
    
    
    

    
}
