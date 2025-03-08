package dev.nishant.paymentservicejan25.services;

import com.stripe.exception.StripeException;

public interface PaymentService {
    //Why interface because can have multiple implementation

    public String generatePaymentLink(Long orderId) throws StripeException;
}
