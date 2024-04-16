package com.cogentcapstone.product.service.service.impl;

import com.cogentcapstone.product.service.entity.Product;
import com.cogentcapstone.product.service.model.ProductRequest;
import com.cogentcapstone.product.service.model.ProductResponse;
import com.cogentcapstone.product.service.repository.ProductRepository;
import com.cogentcapstone.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product newProduct = Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        newProduct = productRepository.save(newProduct);
        ProductResponse productResponse = new ProductResponse();
        copyProperties(newProduct, productResponse);
        return productResponse;
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product, productResponse);
        return productResponse;
    }
}
