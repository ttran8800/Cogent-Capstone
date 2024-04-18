package com.cogentcapstone.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
    @PostMapping("/orderServiceFallBack")
    public String orderServiceFallBack() {
        return "Order Service is down";
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBack() {
        return "Product Service is down";
    }

    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBack() {
        return "Payment Service is down";
    }
}
