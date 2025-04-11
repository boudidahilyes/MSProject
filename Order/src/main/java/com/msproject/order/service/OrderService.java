package com.msproject.order.service;

import com.msproject.order.entity.Orders;
import com.msproject.order.repository.OrderRespository;
import com.msproject.order.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRespository orderRespository;

    public List<Orders> getAll() {
        List<Orders> orders= orderRespository.findAll();

        List<OrderResponse> responses = new ArrayList<>();

        for ( Orders order :orders){
            OrderResponse response = buildOrderResponse(order);
            responses.add(response);
        }
        return orders;

    }

    public OrderResponse getOrderById(int id) {
        var foundOrder = orderRespository.findById(id).orElseThrow(()->new RuntimeException("order not found"));


        return buildOrderResponse(foundOrder);
    }
    public void updateOrder(String id,Orders order) {
//        Order foundOrder = this.orderRespository.findById(id).orElseThrow(
//                ()=> New Run)

        orderRespository.save(order);
    }

    public Orders createOrder(double totalPrice, Orders payload) {
        Orders newOrder= Orders.builder()
                .totalPrice(totalPrice)
                .city(payload.getCity())
                .street(payload.getStreet())
                .email(payload.getEmail())
                .zipCode(payload.getZipCode())
                .phoneNumber(payload.getPhoneNumber())
                .fullName(payload.getFullName())
                .build();

        return orderRespository.save(newOrder);

    }

    private OrderResponse buildOrderResponse(Orders response) {
        return OrderResponse.builder()
                .orderId(response.getOrderId())
                .totalPrice(response.getTotalPrice())
                .email(response.getEmail())
                .fullName(response.getFullName())
                .build();
    }
}
