package com.example.PayFlow.payment.processor.dto.request;

import com.example.PayFlow.common.entity.Money;
import com.example.PayFlow.common.enums.PaymentMethod;

import java.util.Map;

public record PaymentProcessorRequest(
      PaymentMethod method,
      Money amount,

      Map<String, Object> methodDetails
) {
}
