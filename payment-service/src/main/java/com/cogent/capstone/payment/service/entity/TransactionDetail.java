package com.cogent.capstone.payment.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction_details")
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false, name = "order_id")
    private Long orderId;

    private Long amount;

    @Column(nullable = false, name = "payment_mode")
    private String paymentMode;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    @Column(nullable = false, name = "payment_status")
    private String paymentStatus;
}
