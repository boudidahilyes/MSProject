package com.msproject.order.service;

import com.msproject.order.config.StripeConfig;
import com.msproject.order.request.StripePaymentRequest;
import com.msproject.order.response.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StripeService {

    private final StripeConfig stripeConfig;

    public String createPaymentMethod() throws StripeException {
        stripeConfig.initializeStripe();

        Map<String, Object> params = new HashMap<>();
        params.put("type", "card");

        Map<String, Object> card = new HashMap<>();
        card.put("number", "4242424242424242");
        card.put("exp_month", 12);
        card.put("exp_year", 2025);
        card.put("cvc", "123");
        params.put("card", card);

        PaymentMethod paymentMethod = PaymentMethod.create(params);

        return paymentMethod.getId();
    }

    public StripeResponse create(
            StripePaymentRequest paymentInentRequest
    ) throws StripeException {
        stripeConfig.initializeStripe();
        String id =createPaymentMethod();
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInentRequest.amount());
        params.put("currency", paymentInentRequest.currency());

        List<String> paymentMethodTypes = List.of("card");

        params.put("payment_method_types", paymentMethodTypes);
        params.put("payment_method", id);
        params.put("capture_method", "manual");

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return new StripeResponse(paymentIntent.getClientSecret());
    }
public StripeResponse createPaymentIntent(StripePaymentRequest paymentIntentRequest) throws StripeException {
    stripeConfig.initializeStripe();

    Map<String, Object> params = new HashMap<>();
    params.put("amount", paymentIntentRequest.amount());
    params.put("currency", paymentIntentRequest.currency());

    PaymentIntent paymentIntent = PaymentIntent.create(params);

    return new StripeResponse(paymentIntent.getClientSecret());
}

    public void confirmPayment(String clientSecret, String paymentMethodId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(clientSecret);
        paymentIntent.confirm(Map.of("payment_method", paymentMethodId));
    }
}
