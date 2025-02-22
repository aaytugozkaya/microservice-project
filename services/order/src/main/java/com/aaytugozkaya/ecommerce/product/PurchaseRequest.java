package com.aaytugozkaya.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product id should not be null")
        Integer productId,
        @Positive(message = "Quantity should be positive")
        Double quantity
) {
}
