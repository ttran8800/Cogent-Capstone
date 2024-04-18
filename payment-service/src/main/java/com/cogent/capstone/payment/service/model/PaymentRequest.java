package com.cogent.capstone.payment.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Long orderId;
    private Long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;
}
