package com.example.PayFlow.payment.processor.strategy;

import com.example.PayFlow.payment.processor.PaymentProcessor;
import com.example.PayFlow.payment.processor.dto.request.PaymentProcessorRequest;
import com.example.PayFlow.payment.processor.dto.responce.PaymentProcessorResponce;

public class NetBankingPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponce charge(PaymentProcessorRequest request) {
        // calls the third aparty payment gateway to process the payment and returns the response
        return null;
    }
}
