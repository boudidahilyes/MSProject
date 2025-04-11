package com.msproject.order.request;

import com.msproject.order.entity.Orders;
import lombok.Data;

@Data
public class ConfirmPaymentRequest {
    private String paymentIntentId;
    private double totalPrice;
    private Orders orderData;
}
