package com.example.PayFlow.payment.gateway;

import com.example.PayFlow.payment.gateway.dto.PaymentRequest;
import com.example.PayFlow.payment.gateway.dto.PaymentResult;

public interface PaymentAdapter {
    public PaymentResult initiate(PaymentRequest request);
}
