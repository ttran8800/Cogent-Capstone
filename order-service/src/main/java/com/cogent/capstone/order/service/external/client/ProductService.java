package com.cogent.capstone.order.service.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/api/products")
public interface ProductService {

    @PutMapping("/reduceQuantity/{productId}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("productId") Long productId, @RequestParam Long quantity);
}
