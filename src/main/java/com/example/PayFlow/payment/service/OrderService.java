package com.example.PayFlow.payment.service;

import com.example.PayFlow.payment.dto.request.CreateOrderRequest;
import com.example.PayFlow.payment.dto.response.OrderResponce;
import com.example.PayFlow.payment.dto.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponce create(UUID merchantId, CreateOrderRequest request);
    OrderResponce getById(UUID merchantId, UUID orderId);
    OrderResponce cancel(UUID merchantId, UUID orderId);
    List<PaymentResponse> listPayments(UUID merchantId, UUID orderId);
}
