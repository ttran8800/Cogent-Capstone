package com.cogentcapstone.product.service.service;

import com.cogentcapstone.product.service.entity.Product;
import com.cogentcapstone.product.service.model.ProductRequest;
import com.cogentcapstone.product.service.model.ProductResponse;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);
    ProductResponse getProductById(Long productId);
    void reduceQuantity(Long productId, Long quantity);
}
