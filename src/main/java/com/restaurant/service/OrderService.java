package com.restaurant.service;

import com.restaurant.model.Order;
import com.restaurant.model.OrderItem;
import com.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    // ✅ Create pending order (before payment)
    public Order createPendingOrder(int amount, Map<String, Object> payload) {
        try {
            // 🧩 Step 1: Safely extract userId
            Object userIdObj = payload.get("userId");
            if (userIdObj == null) {
                throw new IllegalArgumentException("userId is missing in request payload");
            }
            Long userId = Long.parseLong(userIdObj.toString());

            // 🧩 Step 2: Create order
            Order order = new Order();
            order.setUserId(userId);
            order.setTotalAmount(amount);
            order.setPaymentStatus("PENDING");

            // 🧩 Step 3: Add order items (if present)
            if (payload.containsKey("items")) {
                List<Map<String, Object>> itemsData = (List<Map<String, Object>>) payload.get("items");

                for (Map<String, Object> i : itemsData) {
                    OrderItem item = new OrderItem();

                    // ✅ Extract values safely
                    Long menuItemId = Long.parseLong(i.get("menuItemId").toString());
                    String name = (String) i.get("name");
                    int price = Integer.parseInt(i.get("price").toString());
                    int quantity = Integer.parseInt(i.get("quantity").toString());

                    item.setMenuItemId(menuItemId);
                    item.setMenuItemName(name);
                    item.setPrice(price);
                    item.setQuantity(quantity);
                    item.setTotalPrice(price * quantity);
                    item.setOrder(order);

                    order.getItems().add(item);
                }
            }

            // 🧩 Step 4: Save and return
            return orderRepo.save(order);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to create pending order: " + e.getMessage());
        }
    }

    // ✅ Mark order as PAID after successful Razorpay payment
    public Order markOrderPaid(Long orderId, String paymentId, String razorOrderId) {
        Order o = orderRepo.findById(orderId).orElseThrow();
        o.setPaymentStatus("PAID");
        o.setRazorpayPaymentId(paymentId);
        o.setRazorpayOrderId(razorOrderId);
        return orderRepo.save(o);
    }
}
