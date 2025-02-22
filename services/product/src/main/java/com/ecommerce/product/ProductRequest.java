package com.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        @Positive(message = "Available quantity must be greater than 0")
        Double availableQuantity,
        @NotNull
        @Positive(message = "Price must be greater than 0")
        BigDecimal price,
        @NotNull
        Integer categoryId
) {
}
