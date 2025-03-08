package dev.nishant.paymentservicejan25.controllers;

import dev.nishant.paymentservicejan25.dtos.GeneratePaymentLinkRequestDTO;
import dev.nishant.paymentservicejan25.services.PaymentService;
import dev.nishant.paymentservicejan25.services.RazorPayGateway;
import dev.nishant.paymentservicejan25.services.StripeGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private RazorPayGateway razorPayGateway;
    private StripeGateway stripeGateway;
//    private int counter;
    public PaymentController(RazorPayGateway razorPayGateway, StripeGateway stripeGateway) {
        this.razorPayGateway = razorPayGateway;
        this.stripeGateway = stripeGateway;
    }

    @PostMapping("/payments")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDTO generatePaymentLinkRequestDTO) {
//        if(counter % 2 ==0) {
//            counter++;
//            return razorPayGateway.generatePaymentLink(generatePaymentLinkRequestDTO.getOrderId());
//        }else {
//            counter++;
//            return stripeGateway.generatePaymentLink(generatePaymentLinkRequestDTO.getOrderId());
//        }
        return razorPayGateway.generatePaymentLink(generatePaymentLinkRequestDTO.getOrderId());
    }
}
