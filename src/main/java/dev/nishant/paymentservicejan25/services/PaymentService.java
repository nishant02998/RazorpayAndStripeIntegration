package dev.nishant.paymentservicejan25.services;

public interface PaymentService {
    //Why interface because can have multiple implementation

    public String generatePaymentLink(Long orderId);
}
