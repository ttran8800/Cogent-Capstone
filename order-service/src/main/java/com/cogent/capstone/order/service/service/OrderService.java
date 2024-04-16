package com.cogent.capstone.order.service.service;


import com.cogent.capstone.order.service.model.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
