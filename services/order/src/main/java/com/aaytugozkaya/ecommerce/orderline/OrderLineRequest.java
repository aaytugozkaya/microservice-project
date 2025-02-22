package com.aaytugozkaya.ecommerce.orderline;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        @NotNull(message = "Product id should not be null")
        Integer productId,
        @Positive(message = "Quantity should be positive")
        Double quantity
) {
}
