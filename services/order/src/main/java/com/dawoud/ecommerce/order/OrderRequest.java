package com.dawoud.ecommerce.order;

import com.dawoud.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,

        @Positive(message = "order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should not be precised")
        PaymentMethod paymentMethod,
        @NotNull(message ="Customer should be present")
        @NotEmpty(message ="Customer should be present")
        @NotBlank(message ="Customer should be present")
        String customerId,
        @NotEmpty(message ="Should have at least one product")
        List<PurchaseRequest> products
) {
}
