package com.msproject.order.controller;

import com.msproject.order.config.StripeConfig;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.net.Webhook;
import com.stripe.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StripeWebhookController {

    private final StripeConfig config;


    @PostMapping("/webhook")
    public String handleStripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader
    ) {
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, config.secretKey);
        } catch (SignatureVerificationException e) {
            return "Invalid signature";
        } catch (StripeException e) {
            return "Stripe error";
        }

        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded":

                break;
            case "payment_intent.payment_failed":

                break;
            default:

                break;
        }

        return "Event received";
    }

}
