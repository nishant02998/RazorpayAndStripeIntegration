package dev.nishant.paymentservicejan25.services;

import com.razorpay.RazorpayClient;
import com.razorpay.PaymentLink;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayGateway implements PaymentService {
    private RazorpayClient razorPayClient;

    public RazorPayGateway(RazorpayClient instance) {
        this.razorPayClient = instance;
    }

    @Override
    public String generatePaymentLink(Long orderId) {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", 1000); // Amount in paise (₹10.00)
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("expire_by", (System.currentTimeMillis() / 1000) + 600); // Expiry in 10 mins
            paymentLinkRequest.put("reference_id", orderId.toString());
            paymentLinkRequest.put("description", "Nishant's Testing Payment Integration");

            JSONObject customer = new JSONObject();
            customer.put("name", "Nishant Srivastava");
            customer.put("contact", "9123456789"); // Valid 10-digit number
            customer.put("email", "nishant02998@gmail.com");
            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("callback_url", "https://www.google.com/");
            paymentLinkRequest.put("callback_method", "get");
            long expireBy = (System.currentTimeMillis() / 1000) + 10000; // 15+ minutes from now
            paymentLinkRequest.put("expire_by", expireBy);

// Debugging logs
            System.out.println("Expire By Timestamp: " + expireBy);
            System.out.println("Current Time (seconds): " + (System.currentTimeMillis() / 1000));
            // 15 minutes from now


            // ✅ Correct Razorpay API call
            PaymentLink payment = razorPayClient.paymentLink.create(paymentLinkRequest);

            return payment.toString(); // Return the payment link details
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating payment link: " + e.getMessage();
        }
    }
}
