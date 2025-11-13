package com.restaurant.controller;

import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import com.restaurant.model.Order; // your entity
import com.restaurant.service.OrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Value("${razorpay.key_id}")
    private String razorKeyId;

    @Value("${razorpay.key_secret}")
    private String razorKeySecret;

    private final OrderService orderService;

    public PaymentController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ✅ Step 1: Create Razorpay Order + App Order
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) {
        try {
            int amount = (int) data.get("amount");
            RazorpayClient client = new RazorpayClient(razorKeyId, razorKeySecret);

            // 🔹 Use full package name to avoid conflict
            com.razorpay.Order razorOrder;

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_" + System.currentTimeMillis());

            razorOrder = client.orders.create(orderRequest);

            // 🔹 Now create local app order
            Order appOrder = orderService.createPendingOrder(amount, data);

            Map<String, Object> response = new HashMap<>();
            response.put("razorOrderId", razorOrder.get("id"));
            response.put("appOrderId", appOrder.getId());
            response.put("amount", amount);
            response.put("currency", "INR");
            response.put("key", razorKeyId);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // ✅ Step 2: Verify Payment
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> data) {
        try {
            Utils.verifyPaymentSignature(new JSONObject(data), razorKeySecret);

            Long appOrderId = Long.valueOf(data.get("appOrderId"));
            String paymentId = data.get("razorpay_payment_id");
            String razorOrderId = data.get("razorpay_order_id");

            orderService.markOrderPaid(appOrderId, paymentId, razorOrderId);

            return ResponseEntity.ok("✅ Payment verified & order updated!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Invalid signature: " + e.getMessage());
        }
    }
}
