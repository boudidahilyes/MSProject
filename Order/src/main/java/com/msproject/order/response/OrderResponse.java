package com.msproject.order.response;


import lombok.Builder;
@Builder
public record OrderResponse (
                              int orderId,
                              Double totalPrice,
                              String email,
                              String fullName){
}
