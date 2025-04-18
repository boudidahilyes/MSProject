package com.msproject.order.controller;

import com.msproject.order.config.StripeConfig;
import com.msproject.order.entity.Orders;
import com.msproject.order.request.ConfirmPaymentRequest;
import com.msproject.order.response.OrderResponse;
import com.msproject.order.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StripeConfig stripeConfig;


    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable("id") int id
    ) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @GetMapping()
    public ResponseEntity<List<Orders>>getAll() {
        List<Orders> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("{cartId}")
    public ResponseEntity<Orders> createOrder(
            @RequestBody Orders payload,
            @PathVariable int cartId,
            @RequestParam double totalAmount
    ) {
        return ResponseEntity.ok(orderService.createOrder(cartId,totalAmount,payload));
    }

//    @PostMapping("/confirm-payment-and-create-order")
//    public ResponseEntity<?> confirmPaymentAndCreateOrder(@RequestBody ConfirmPaymentRequest request) throws StripeException {
//        stripeConfig.initializeStripe();
//
//        // 1. Retrieve the PaymentIntent
//        PaymentIntent intent = PaymentIntent.retrieve(request.getPaymentIntentId());
//
//        // 2. Confirm it (if not already confirmed)
//        if (!"succeeded".equals(intent.getStatus())) {
//            intent = intent.confirm(); // Optional if auto-confirmed during creation
//        }
//
//        // 3. Check payment status
//        if ("succeeded".equals(intent.getStatus())) {
//            // 4. Create and save the order
//            Orders savedOrder = createOrder(request.getTotalPrice(), request.getOrderData());
//            return ResponseEntity.ok(savedOrder);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment not successful");
//        }
//    }

}
