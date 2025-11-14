package com.restaurant.dto;

public class OrderItemDTO {

    private Long menuItemId;
    private String menuItemName;
    private int price;
    private int quantity;
    public Long getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	private int totalPrice;

    public OrderItemDTO(Long menuItemId, String menuItemName, int price, int quantity, int totalPrice) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // getters and setters
}
