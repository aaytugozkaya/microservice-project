package com.aaytugozkaya.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Firstname cannot be null")
        String firstname,
        @NotNull(message = "Lastname cannot be null")
        String lastname,
        @NotNull(message = "Email cannot be null")
        @Email(message = "Email should be valid")
        String email,
        Address address
) {
}
