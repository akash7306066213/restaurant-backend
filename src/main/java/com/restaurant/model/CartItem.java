package com.restaurant.model;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    private int quantity;

    private double totalPrice;

    public CartItem() {}

    public CartItem(User user, MenuItem menuItem, int quantity) {
        this.user = user;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.totalPrice = quantity * menuItem.getPrice();
    }

    // getters & setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public MenuItem getMenuItem() {
        return menuItem;
    }
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = quantity * menuItem.getPrice();
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
