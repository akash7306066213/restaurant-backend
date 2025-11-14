package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	
}
