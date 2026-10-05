package com.example.PayFlow.payment.gateway;

import com.example.PayFlow.payment.gateway.dto.PaymentRequest;

public interface PaymentAdapter {
    public void initiate(PaymentRequest request);
}
