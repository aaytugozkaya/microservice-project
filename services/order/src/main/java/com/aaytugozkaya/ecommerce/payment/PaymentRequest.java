package com.aaytugozkaya.ecommerce.payment;

import com.aaytugozkaya.ecommerce.customer.CustomerResponse;
import com.aaytugozkaya.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
