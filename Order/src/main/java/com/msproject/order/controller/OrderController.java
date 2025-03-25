package com.msproject.order.controller;

import com.msproject.order.entity.Order;
import com.msproject.order.response.OrderResponse;
import com.msproject.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PatchMapping("")
    public ResponseEntity<?> updateOrder(
            @RequestBody Order order
    ) {
        orderService.updateOrder(order);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable("id") int id
    ) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("")
    public ResponseEntity<Order> createOrder(
            @RequestBody Order order
    ) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }
}
