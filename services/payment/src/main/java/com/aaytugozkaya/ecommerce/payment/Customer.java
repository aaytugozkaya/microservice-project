package com.aaytugozkaya.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "Firstname cannot be null")
        String firstname,
        @NotNull(message = "Lastname cannot be null")
        String lastname,
        @NotNull(message = "Email cannot be null")
        @Email(message = "Email should be valid")
        String email
) {
}
