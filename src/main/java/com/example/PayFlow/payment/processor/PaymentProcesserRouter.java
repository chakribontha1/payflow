package com.example.PayFlow.payment.processor;

import com.example.PayFlow.common.enums.PaymentMethod;
import com.example.PayFlow.payment.processor.dto.request.PaymentProcessorRequest;
import com.example.PayFlow.payment.processor.dto.responce.PaymentProcessorResponce;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentProcesserRouter {
    private Map<PaymentMethod,PaymentProcessor> paymentProcessors;
    public PaymentProcessorResponce charge(PaymentProcessorRequest request) {
        PaymentProcessor processor = paymentProcessors.get(request.method());
        if (processor == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + request.method());
        }
        return processor.charge(request);
    }
}
