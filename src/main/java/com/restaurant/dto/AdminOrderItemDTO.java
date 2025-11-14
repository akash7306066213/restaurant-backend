package com.restaurant.dto;

public class AdminOrderItemDTO {

    private Long menuItemId;
    private String menuItemName;
    private String imageUrl;
    private int price;
    private int quantity;
    private int totalPrice;
    private String categoryName;

    public AdminOrderItemDTO(Long menuItemId, String menuItemName, String imageUrl,
                             int price, int quantity, int totalPrice, String categoryName) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.categoryName = categoryName;
    }

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
    
    // getters + setters
}
