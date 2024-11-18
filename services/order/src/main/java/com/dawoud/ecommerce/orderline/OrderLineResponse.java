package com.dawoud.ecommerce.orderline;


public record OrderLineResponse(
        Integer id,
        double quantity
) { }