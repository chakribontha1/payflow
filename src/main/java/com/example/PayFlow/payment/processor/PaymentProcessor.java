package com.example.PayFlow.payment.processor;

import com.example.PayFlow.payment.processor.dto.request.PaymentProcessorRequest;
import com.example.PayFlow.payment.processor.dto.responce.PaymentProcessorResponce;

public interface PaymentProcessor { ;
    public PaymentProcessorResponce charge(PaymentProcessorRequest request);
}
