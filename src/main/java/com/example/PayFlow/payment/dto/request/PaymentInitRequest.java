package com.example.PayFlow.payment.dto.request;

import com.example.PayFlow.common.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record PaymentInitRequest(
        @NotNull(message = "Order ID is required")
        UUID orderId,
        @NotNull(message = "Payment method is required")
        PaymentMethod method,
        Map<String, Object> methodDetails
) {
}
