package com.aaytugozkaya.ecommerce.customer;

import lombok.Builder;
import org.springframework.stereotype.Service;


public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
