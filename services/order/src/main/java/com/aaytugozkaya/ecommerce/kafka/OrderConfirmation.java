package com.aaytugozkaya.ecommerce.kafka;

import com.aaytugozkaya.ecommerce.customer.CustomerResponse;
import com.aaytugozkaya.ecommerce.order.PaymentMethod;
import com.aaytugozkaya.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
