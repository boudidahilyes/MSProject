package com.msproject.order.request;

public record StripePaymentRequest(
         String paymentMethodId,

         long amount,

         String currency
) {
}
