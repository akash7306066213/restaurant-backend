package com.restaurant.service;

import com.restaurant.model.Category;
import com.restaurant.model.MenuItem;
import com.restaurant.repository.CategoryRepository;
import com.restaurant.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuRepo;
    private final CategoryRepository catRepo;

    public MenuItemService(MenuItemRepository menuRepo, CategoryRepository catRepo) {
        this.menuRepo = menuRepo;
        this.catRepo = catRepo;
    }

    public List<MenuItem> getAll() {
        return menuRepo.findAll();
    }

    public MenuItem create(MenuItem m, Long categoryId) {
        Category c = catRepo.findById(categoryId).orElseThrow();
        m.setCategory(c);
        return menuRepo.save(m);
    }

    public MenuItem update(Long id, MenuItem m, Long categoryId) {
        MenuItem old = menuRepo.findById(id).orElseThrow();
        Category c = catRepo.findById(categoryId).orElseThrow();

        old.setName(m.getName());
        old.setDescription(m.getDescription());
        old.setPrice(m.getPrice());
        old.setImageUrl(m.getImageUrl());
        old.setAvailable(m.isAvailable());
        old.setCategory(c);

        return menuRepo.save(old);
    }

    public void delete(Long id) {
        menuRepo.deleteById(id);
    }
    
    public MenuItem toggleAvailability(Long id) {
        MenuItem item = menuRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setAvailable(!item.isAvailable());
        return menuRepo.save(item);
    }
}
