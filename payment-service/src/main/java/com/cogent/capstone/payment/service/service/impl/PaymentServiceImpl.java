package com.cogent.capstone.payment.service.service.impl;

import com.cogent.capstone.payment.service.entity.TransactionDetail;
import com.cogent.capstone.payment.service.model.PaymentRequest;
import com.cogent.capstone.payment.service.repository.PaymentRepository;
import com.cogent.capstone.payment.service.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("record payment details: {}", paymentRequest);

        TransactionDetail transactionDetail = TransactionDetail.builder()
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .transactionDate(Instant.now())
                .amount(paymentRequest.getAmount())
                .build();
        paymentRepository.save(transactionDetail);
        return transactionDetail.getTransactionId();
    }
}
