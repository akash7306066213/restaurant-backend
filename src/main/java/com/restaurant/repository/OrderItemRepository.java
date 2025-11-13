package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
