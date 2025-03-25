package com.msproject.order.service;

import com.msproject.order.entity.Order;
import com.msproject.order.repository.OrderRespository;
import com.msproject.order.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRespository orderRespository;

    public List<Order> getAllCandidats() {
        return orderRespository.findAll();
    }

    public OrderResponse getOrderById(int id) {
        var order = orderRespository.findById(id).orElse(null);
        if (order == null)
            return null;

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .totalPrice(order.getTotalPrice())
                .email(order.getEmail())
                .build();
    }
    public void updateOrder(Order order) {
        orderRespository.save(order);
    }

    public Order createOrder(Order order) {
        return orderRespository.save(order);
    }
}
