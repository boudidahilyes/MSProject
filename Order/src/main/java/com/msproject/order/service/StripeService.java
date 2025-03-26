package com.msproject.order.service;

import com.msproject.order.config.StripeConfig;
import com.msproject.order.request.StripePaymentRequest;
import com.msproject.order.response.StripeResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StripeService {

    private final StripeConfig stripeConfig;

    public StripeResponse createPaymentIntent(
            StripePaymentRequest paymentInentRequest
    ) throws StripeException {
        stripeConfig.initializeStripe();

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInentRequest.amount());
        params.put("currency", paymentInentRequest.currency());

        List<String> paymentMethodTypes = List.of("card");
        params.put("payment_method_types", paymentMethodTypes);
        params.put("payment_method", paymentInentRequest.paymentMethodId());
        params.put("capture_method", "manual");

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return new StripeResponse(paymentIntent.getClientSecret());
    }

}
