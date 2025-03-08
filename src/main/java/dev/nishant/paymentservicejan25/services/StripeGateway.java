package dev.nishant.paymentservicejan25.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripeGateway implements PaymentService {
    @Override
    public String generatePaymentLink(Long orderId) throws StripeException {
        Stripe.apiKey = "sk_test_51R0GaJE0BEYsJwNdUKvJXKci575b6v1AoZSWh8kDsZ5tnTJhrlxF6KqdNg6AdvkuXll85maMMt6iJb9i3pKosC1d003crOgh7x";

        // Create a Price Object
        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(10000L)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = Price.create(priceParams);

        // Create a Payment Link
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId()) // Use dynamically created price ID
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.getUrl(); // Return the generated payment link
    }
}
