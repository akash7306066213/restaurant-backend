package com.restaurant.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.model.CartItem;
import com.restaurant.model.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
}
