package com.restaurant.controller;

import com.restaurant.model.MenuItem;
import com.restaurant.service.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/menu")
@CrossOrigin(origins = "*")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<MenuItem> getAll() {
        return service.getAll();
    }

    @PostMapping
    public MenuItem create(@RequestBody Map<String, Object> body) {
        MenuItem m = new MenuItem();
        m.setName((String) body.get("name"));
        m.setDescription((String) body.get("description"));
        m.setPrice(Double.parseDouble(body.get("price").toString()));
        m.setImageUrl((String) body.get("imageUrl"));
        m.setAvailable(true);

        Long categoryId = Long.valueOf(body.get("categoryId").toString());

        return service.create(m, categoryId);
    }

    @PutMapping("/{id}")
    public MenuItem update(@PathVariable Long id, @RequestBody Map<String, Object> body) {

        MenuItem m = new MenuItem();
        m.setName((String) body.get("name"));
        m.setPrice(Double.valueOf(body.get("price").toString()));

        Long categoryId = Long.valueOf(body.get("categoryId").toString());

        if (body.get("imageUrl") != null) {
            m.setImageUrl((String) body.get("imageUrl"));
        }

        // handle availability safely
        Boolean available = body.containsKey("available") ? (Boolean) body.get("available") : null;
        if (available != null) {
            m.setAvailable(available);
        }

        return service.update(id, m, categoryId);
    }

    
    @PatchMapping("/{id}/availability")
    public MenuItem toggleAvailability(@PathVariable Long id) {
        return service.toggleAvailability(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
