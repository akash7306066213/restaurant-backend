package com.restaurant.service;

import com.restaurant.dto.AdminOrderDTO;
import com.restaurant.dto.AdminOrderItemDTO;
import com.restaurant.dto.OrderItemDTO;
import com.restaurant.dto.OrderSummaryDTO;
import com.restaurant.model.Category;
import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.model.OrderItem;
import com.restaurant.model.User;
import com.restaurant.repository.CategoryRepository;
import com.restaurant.repository.MenuItemRepository;
import com.restaurant.repository.OrderRepository;
import com.restaurant.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final MenuItemRepository menuItemRepo;
    private final CategoryRepository categoryRepo;

    public OrderService(OrderRepository orderRepo,
                        UserRepository userRepo,
                        MenuItemRepository menuItemRepo,
                        CategoryRepository categoryRepo) {

        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.menuItemRepo = menuItemRepo;
        this.categoryRepo = categoryRepo;
    }

    // ✅ Create pending order (before payment)
    public Order createPendingOrder(int amount, Map<String, Object> payload) {
        try {
            // 🧩 Step 1: Extract userId
            Object userIdObj = payload.get("userId");
            if (userIdObj == null) {
                throw new IllegalArgumentException("userId is missing in request payload");
            }
            Long userId = Long.parseLong(userIdObj.toString());

            // 🧩 Step 2: Create Order entity
            Order order = new Order();
            order.setUserId(userId);
            order.setTotalAmount(amount);
            order.setPaymentStatus("PENDING");

            // 🧩 Step 3: Add Order Items
            if (payload.containsKey("items")) {
                List<Map<String, Object>> itemsData = (List<Map<String, Object>>) payload.get("items");

                for (Map<String, Object> i : itemsData) {
                    OrderItem item = new OrderItem();

                    Long menuItemId = Long.parseLong(i.get("menuItemId").toString());
                    String name = i.get("name").toString();
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

            // 🧩 Step 4: Save order
            return orderRepo.save(order);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to create pending order: " + e.getMessage());
        }
    }

    // ✅ Mark order as PAID
    public Order markOrderPaid(Long orderId, String paymentId, String razorOrderId) {
        Order o = orderRepo.findById(orderId).orElseThrow();
        o.setPaymentStatus("PAID");
        o.setRazorpayPaymentId(paymentId);
        o.setRazorpayOrderId(razorOrderId);
        return orderRepo.save(o);
    }

    // ================================
    // ⭐ Convert Entity → DTO
    // ================================
    private OrderSummaryDTO convertToDTO(Order order) {

        List<OrderItemDTO> itemDTOs = order.getItems()
                .stream()
                .map(item -> new OrderItemDTO(
                        item.getMenuItemId(),
                        item.getMenuItemName(),
                        item.getPrice(),
                        item.getQuantity(),
                        item.getTotalPrice()
                ))
                .toList();

        return new OrderSummaryDTO(
                order.getId(),
                order.getTotalAmount(),
                order.getPaymentStatus(),
                order.getRazorpayOrderId(),
                order.getRazorpayPaymentId(),
                itemDTOs
        );
    }

    // ================================
    // ⭐ Get Orders of a Specific User
    // ================================
    public List<OrderSummaryDTO> getOrdersByUser(Long userId) {
        List<Order> orders = orderRepo.findByUserId(userId);

        return orders.stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    public List<AdminOrderDTO> getAllOrdersForAdmin() {

        List<Order> orders = orderRepo.findAll();

        return orders.stream()
                .map(order -> {

                    // Fetch user
                    User user = userRepo.findById(order.getUserId()).orElse(null);

                    // Convert order items
                    List<AdminOrderItemDTO> items = order.getItems().stream()
                            .map(item -> {

                                // Load MenuItem and Category
                                MenuItem menu = menuItemRepo.findById(item.getMenuItemId()).orElse(null);
                                Category category = (menu != null) ? menu.getCategory() : null;

                                return new AdminOrderItemDTO(
                                        item.getMenuItemId(),
                                        item.getMenuItemName(),
                                        (menu != null) ? menu.getImageUrl() : null, // Image
                                        item.getPrice(),
                                        item.getQuantity(),
                                        item.getTotalPrice(),
                                        (category != null) ? category.getName() : "Unknown"
                                );
                            })
                            .collect(Collectors.toList());

                    return new AdminOrderDTO(
                            order.getId(),
                            (user != null) ? user.getName() : "Unknown",
                            (user != null) ? user.getEmail() : "Unknown",
                            order.getTotalAmount(),
                            order.getPaymentStatus(),
                            items
                    );
                })
                .collect(Collectors.toList());
    }

    
    
    
}
