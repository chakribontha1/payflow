package com.example.PayFlow.payment.dto.response;

import com.example.PayFlow.common.entity.Money;
import com.example.PayFlow.common.enums.PaymentMethod;
import com.example.PayFlow.common.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Map;

public record PaymentResponse(
        String id,
        String orderId,
        String merchantId,
        Money amount,
        PaymentStatus status,
        PaymentMethod method,
        Map<String, Object> methodDetails,

        String errorCode,
        String errorDescription,

        LocalDateTime capureAt,
        LocalDateTime createdAt


) {
}
