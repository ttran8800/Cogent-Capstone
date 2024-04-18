package com.cogent.capstone.payment.service.service;

import com.cogent.capstone.payment.service.model.PaymentRequest;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);
}
