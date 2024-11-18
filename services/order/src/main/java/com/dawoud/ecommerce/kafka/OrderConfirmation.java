package com.dawoud.ecommerce.kafka;
import com.dawoud.ecommerce.customer.CustomerResponse;
import com.dawoud.ecommerce.order.PaymentMethod;
import com.dawoud.ecommerce.product.PurchaseResponse;
import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
