package com.restaurant.model;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
    
}
