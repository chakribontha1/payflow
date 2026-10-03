package com.example.PayFlow.payment.service;

import com.example.PayFlow.payment.dto.response.OrderResponce;

import java.util.UUID;

public interface OrderService {
    OrderResponce create(UUID merchantId, OrderResponce request);
}
