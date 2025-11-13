package com.restaurant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.model.Category;
import com.restaurant.model.MenuItem;
import com.restaurant.repository.CategoryRepository;
import com.restaurant.repository.MenuItemRepository;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuPublicController {

    private final MenuItemRepository menuRepo;
    private final CategoryRepository catRepo;

    public MenuPublicController(MenuItemRepository menuRepo, CategoryRepository catRepo) {
        this.menuRepo = menuRepo;
        this.catRepo = catRepo;
    }

    // ✅ 1️⃣ — Get available items (optionally by category)
    @GetMapping
    public List<MenuItem> getAvailableItems(@RequestParam(required = false) Long categoryId) {
        if (categoryId != null) {
            return menuRepo.findByCategoryIdAndAvailableTrue(categoryId);
        } else {
            return menuRepo.findByAvailableTrue();
        }
    }

    // ✅ 2️⃣ — Get all categories for customer page
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return catRepo.findAll();
    }
}
