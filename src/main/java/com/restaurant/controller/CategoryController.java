package com.restaurant.controller;

import com.restaurant.model.Category;
import com.restaurant.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // Get all categories
    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    // Create category
    @PostMapping
    public Category create(@RequestBody Category c) {
        return service.create(c);
    }

    // Update category
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category c) {
        return service.update(id, c);
    }

    // Delete category
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
