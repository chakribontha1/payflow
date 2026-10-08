package com.example.PayFlow.payment.gateway.Adapter;

import com.example.PayFlow.payment.gateway.PaymentAdapter;
import com.example.PayFlow.payment.gateway.dto.PaymentRequest;
import com.example.PayFlow.payment.gateway.dto.PaymentResult;

public class NetBankingAdapter implements PaymentAdapter {
    @Override
    public PaymentResult initiate(PaymentRequest request) {
        // Implement UPI payment initiation logic here
     return null;
    }
}
