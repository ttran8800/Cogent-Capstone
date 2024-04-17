package com.cogentcapstone.product.service.service.impl;

import com.cogentcapstone.product.service.entity.Product;
import com.cogentcapstone.product.service.exceptions.InsufficientQuantityException;
import com.cogentcapstone.product.service.exceptions.ResourceNotFoundException;
import com.cogentcapstone.product.service.model.ProductRequest;
import com.cogentcapstone.product.service.model.ProductResponse;
import com.cogentcapstone.product.service.repository.ProductRepository;
import com.cogentcapstone.product.service.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Log4j2
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
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", productId));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product, productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(Long productId, Long quantity) {
        log.info("reduce quantity {} for id: {}", productId, quantity);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", productId));
        if (product.getQuantity() <= quantity) {
            throw new InsufficientQuantityException(product.getProductName(),product.getQuantity(),quantity);
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("product quantity updated successfully");
    }
}
