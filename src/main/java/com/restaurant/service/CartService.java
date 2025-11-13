package com.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.model.CartItem;
import com.restaurant.model.MenuItem;
import com.restaurant.model.User;
import com.restaurant.repository.CartItemRepository;
import com.restaurant.repository.MenuItemRepository;
import com.restaurant.repository.UserRepository;

@Service
public class CartService {

    private final CartItemRepository cartRepo;
    private final MenuItemRepository menuRepo;
    private final UserRepository userRepo;

    public CartService(CartItemRepository cartRepo, MenuItemRepository menuRepo, UserRepository userRepo) {
        this.cartRepo = cartRepo;
        this.menuRepo = menuRepo;
        this.userRepo = userRepo;
    }

    public List<CartItem> getUserCart(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return cartRepo.findByUser(user);
    }

    public CartItem addToCart(Long userId, Long menuItemId, int qty) {
        User user = userRepo.findById(userId).orElseThrow();
        MenuItem item = menuRepo.findById(menuItemId).orElseThrow();

        // check if already exists in cart
        List<CartItem> existing = cartRepo.findByUser(user);
        for (CartItem c : existing) {
            if (c.getMenuItem().getId().equals(menuItemId)) {
                c.setQuantity(c.getQuantity() + qty);
                return cartRepo.save(c);
            }
        }

        CartItem cartItem = new CartItem(user, item, qty);
        return cartRepo.save(cartItem);
    }

    public void removeItem(Long id) {
        cartRepo.deleteById(id);
    }

    public void clearCart(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        cartRepo.deleteByUser(user);
    }
}
