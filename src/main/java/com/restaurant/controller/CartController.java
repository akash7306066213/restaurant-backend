package com.restaurant.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.restaurant.model.CartItem;
import com.restaurant.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    // ✅ 1️⃣ Add to cart
    @PostMapping("/add")
    public CartItem addToCart(
        @RequestParam Long userId,
        @RequestParam Long itemId,
        @RequestParam int qty
    ) {
        return service.addToCart(userId, itemId, qty);
    }

    // ✅ 2️⃣ View user cart
    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return service.getUserCart(userId);
    }

    // ✅ 3️⃣ Remove item from cart
    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable Long id) {
        service.removeItem(id);
    }

    // ✅ 4️⃣ Clear all items
    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        service.clearCart(userId);
    }
}
