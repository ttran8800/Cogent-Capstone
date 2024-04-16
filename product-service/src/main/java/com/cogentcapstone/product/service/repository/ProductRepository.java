package com.cogentcapstone.product.service.repository;

import com.cogentcapstone.product.service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
