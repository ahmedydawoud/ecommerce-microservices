package com.dawoud.ecommerce.payment;

import com.dawoud.ecommerce.customer.CustomerResponse;
import com.dawoud.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
