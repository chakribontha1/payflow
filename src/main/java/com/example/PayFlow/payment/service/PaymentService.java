package com.example.PayFlow.payment.service;

import com.example.PayFlow.payment.dto.request.PaymentInitRequest;
import com.example.PayFlow.payment.dto.response.PaymentResponse;

import java.util.UUID;

public interface PaymentService {
    PaymentResponse initiate(UUID merchantId, PaymentInitRequest request);
}
