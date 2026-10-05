package com.example.PayFlow.payment.gateway.dto;

import com.example.PayFlow.common.entity.Money;
import com.example.PayFlow.common.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public record PaymentRequest(
        UUID paymentId,
        UUID userId,
        UUID merchantId,
        Money amount,
        PaymentMethod method,
        Map<String, Object> methodDetails
) {
}
