package com.cogentcapstone.product.service.controller;

import com.cogentcapstone.product.service.exceptions.InsufficientQuantityException;
import com.cogentcapstone.product.service.model.ProductRequest;
import com.cogentcapstone.product.service.model.ProductResponse;
import com.cogentcapstone.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.addProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Long productId) {
        ProductResponse productResponse = productService.getProductById(productId);
        if (productResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    //http://localhost:9002/api/products/reduceQuantity/1?quantity=5
    @PutMapping("/reduceQuantity/{productId}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("productId") Long productId, @RequestParam Long quantity) {
        try {
            productService.reduceQuantity(productId, quantity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InsufficientQuantityException e) {
            throw e;  // Rethrow to be handled by @ControllerAdvice
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
