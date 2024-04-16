package com.cogent.capstone.order.service.service.impl;

import com.cogent.capstone.order.service.entity.Order;
import com.cogent.capstone.order.service.model.OrderRequest;
import com.cogent.capstone.order.service.repository.OrderRepository;
import com.cogent.capstone.order.service.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .orderDate((Instant.now()))
                .build();
        order = orderRepository.save(order);
        log.info("order placed successfully with Order Id: {}", order.getOrderId());
        return order.getOrderId();
    }
}
