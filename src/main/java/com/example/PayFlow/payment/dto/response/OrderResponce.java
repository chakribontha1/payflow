package com.example.PayFlow.payment.dto.response;

import com.example.PayFlow.common.entity.Money;
import com.example.PayFlow.common.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record OrderResponce(
        UUID id,
        String merchantId,
        String receipt,
        Money amount,
        OrderStatus status,
        Integer attempts,
        Map<String, Object> notes,
        LocalDateTime expireAt,
        LocalDateTime createdAt
) {

}
