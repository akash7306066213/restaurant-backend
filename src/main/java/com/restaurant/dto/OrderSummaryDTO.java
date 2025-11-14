package com.restaurant.dto;

import java.util.List;

public class OrderSummaryDTO {

    private Long orderId;
    private int totalAmount;
    private String paymentStatus;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private List<OrderItemDTO> items;

    public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

	public OrderSummaryDTO(Long orderId, int totalAmount, String paymentStatus,
                           String razorpayOrderId, String razorpayPaymentId,
                           List<OrderItemDTO> items) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.razorpayOrderId = razorpayOrderId;
        this.razorpayPaymentId = razorpayPaymentId;
        this.items = items;
    }

    // getters and setters
}
