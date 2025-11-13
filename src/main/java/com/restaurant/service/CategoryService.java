package com.restaurant.service;

import com.restaurant.model.Category;
import com.restaurant.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Category create(Category c) {
        return repo.save(c);
    }

    public Category update(Long id, Category updated) {
        Category existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setName(updated.getName());

        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
