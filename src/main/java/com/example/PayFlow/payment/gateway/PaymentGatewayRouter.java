package com.example.PayFlow.payment.gateway;

import com.example.PayFlow.common.enums.PaymentMethod;
import com.example.PayFlow.payment.gateway.dto.PaymentRequest;
import com.example.PayFlow.payment.gateway.dto.PaymentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {
    private final Map<PaymentMethod, PaymentAdapter> paymentMethods;

    public PaymentResult initiate(PaymentRequest request){
        PaymentAdapter adapter = paymentMethods.get(request.method());
        if(adapter == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + request.method());
        }
        return adapter.initiate(request);
    }
}
