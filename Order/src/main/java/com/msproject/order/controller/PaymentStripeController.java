package com.msproject.order.controller;

import com.msproject.order.config.StripeConfig;
import com.msproject.order.entity.Orders;
import com.msproject.order.entity.PaymentStatus;
import com.msproject.order.request.ConfirmPaymentRequest;
import com.msproject.order.request.StripePaymentRequest;
import com.msproject.order.response.StripeResponse;
import com.msproject.order.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stripe")
@RequiredArgsConstructor
public class PaymentStripeController {

    private final StripeService stripeService;


    @PostMapping("paymentIntent")
    public ResponseEntity<StripeResponse> createPaymentIntent(
            @RequestBody StripePaymentRequest paymentIntent
    ) throws StripeException {
        StripeResponse paymentResponse = stripeService.createPaymentIntent(paymentIntent);
        return ResponseEntity.ok(paymentResponse);
    }
    @PostMapping("payment")
    public ResponseEntity<StripeResponse> create(
            @RequestBody StripePaymentRequest paymentIntent) throws StripeException {
        StripeResponse paymentResponse = stripeService.create(paymentIntent);
        return ResponseEntity.ok(paymentResponse);
    }

//    @PostMapping("/confirm-payment")
//    public ResponseEntity<?> confirmPayment(@RequestBody ConfirmPaymentRequest request) throws StripeException {
//        stripeService.confirmPayment(request.getClientSecret(), request.getPaymentMethodId());
//        return ResponseEntity.ok(new PaymentStatus("succeeded"));
//    }


}
