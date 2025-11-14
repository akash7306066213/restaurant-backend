package com.restaurant.dto;

import java.util.List;

public class AdminOrderDTO {

    private Long orderId;
    private String userName;
    private String userEmail;

    private int totalAmount;
    private String paymentStatus;

    private List<AdminOrderItemDTO> items;

    public AdminOrderDTO(Long orderId, String userName, String userEmail,
                         int totalAmount, String paymentStatus,
                         List<AdminOrderItemDTO> items) {
        this.orderId = orderId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.items = items;
    }

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<AdminOrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<AdminOrderItemDTO> items) {
		this.items = items;
	}
    
    // getters + setters
}
