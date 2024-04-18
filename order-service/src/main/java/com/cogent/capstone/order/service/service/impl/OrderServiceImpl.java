package com.cogent.capstone.order.service.service.impl;

import com.cogent.capstone.order.service.entity.Order;
import com.cogent.capstone.order.service.external.client.PaymentService;
import com.cogent.capstone.order.service.external.client.ProductService;
import com.cogent.capstone.order.service.model.OrderRequest;
import com.cogent.capstone.order.service.model.PaymentMode;
import com.cogent.capstone.order.service.model.PaymentRequest;
import com.cogent.capstone.order.service.repository.OrderRepository;
import com.cogent.capstone.order.service.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Transactional
@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        String orderStatus = null;


        log.info("Placing order request: {}", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        orderStatus = "CREATED";
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus(orderStatus)
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .orderDate((Instant.now()))
                .build();
        order = orderRepository.save(order);

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getOrderId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .referenceNumber("1234")
                .build();

        log.info("Processing payment: {}", paymentRequest);

        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment processed successfully");
            orderStatus = "PLACED";
            order = orderRepository.save(order);
            log.info("order placed successfully with Order Id: {}", order.getOrderId());
        } catch (Exception e) {
            log.info("error occurred in payment, changing order status");
            orderStatus = "PAYMENT FAILED";
        }
        order.setOrderStatus(orderStatus);

        log.info("Order was not created, order status: {}", orderStatus);
        return order.getOrderId();
    }
}
