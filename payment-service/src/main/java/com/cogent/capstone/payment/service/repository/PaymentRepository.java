package com.cogent.capstone.payment.service.repository;

import com.cogent.capstone.payment.service.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<TransactionDetail, Long> {
}
