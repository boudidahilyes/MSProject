package com.msproject.order.request;

public record orderDto(
         Double totalPrice,

         String fullName,

         int zipCode,

         String street,

         String city,

         String phoneNumber,

         String email
) {
}
