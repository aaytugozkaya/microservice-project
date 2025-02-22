package com.aaytugozkaya.ecommerce.order;

import com.aaytugozkaya.ecommerce.customer.CustomerClient;
import com.aaytugozkaya.ecommerce.exception.BusinessException;
import com.aaytugozkaya.ecommerce.kafka.OrderConfirmation;
import com.aaytugozkaya.ecommerce.kafka.OrderProducer;
import com.aaytugozkaya.ecommerce.orderline.OrderLineRequest;
import com.aaytugozkaya.ecommerce.orderline.OrderLineService;
import com.aaytugozkaya.ecommerce.payment.PaymentClient;
import com.aaytugozkaya.ecommerce.payment.PaymentRequest;
import com.aaytugozkaya.ecommerce.product.ProductClient;
import com.aaytugozkaya.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {
        //check the customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order. Customer not found with id: " + request.customerId()));
        //purchase the products --> product-ms (RestTemplate)
        var purchasedProduct = productClient.purchaseProducts(request.products());
        //persist the order
        var order = this.orderRepository.save(mapper.toOrder(request));
        //persist the order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }
        //start the payment process

        paymentClient.requestOrderPayment(new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                request.reference(),
                customer
        ));

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProduct
                )
        );
        return order.getId();
        //send the email --> notification-ms

    }

    public List<OrderResponse> findAll() {
        return this.orderRepository.findAll().stream()
                .map(mapper::toOrderResponse)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
        return this.orderRepository.findById(orderId)
                .map(mapper::toOrderResponse)
                .orElseThrow(()-> new EntityNotFoundException("Order not found with id: " + orderId));
    }
}
