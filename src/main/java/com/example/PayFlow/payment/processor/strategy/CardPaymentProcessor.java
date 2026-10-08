package com.example.PayFlow.payment.processor.strategy;

import com.example.PayFlow.payment.processor.PaymentProcessor;
import com.example.PayFlow.payment.processor.dto.request.PaymentProcessorRequest;
import com.example.PayFlow.payment.processor.dto.responce.PaymentProcessorResponce;

public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponce charge(PaymentProcessorRequest request) {
        return null;
    }
}
