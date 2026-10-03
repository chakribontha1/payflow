package com.example.PayFlow.payment.service.impl;

import com.example.PayFlow.common.enums.OrderStatus;
import com.example.PayFlow.common.exception.DuplicateResourceException;
import com.example.PayFlow.payment.dto.response.OrderResponce;
import com.example.PayFlow.payment.entity.OrderRecord;
import com.example.PayFlow.payment.repository.OrderRepository;
import com.example.PayFlow.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Value("${payment.order.default-order-expiry-minutes:30}")
    private int defaultExpireInMinutes;

    @Override
    public OrderResponce create(UUID merchantId, OrderResponce request) {
        //first we will check that recpit send from merchant already exits in over database of not
        if (request.receipt()!=null && orderRepository.existsByMerchantIdAndReceipt(merchantId,request.receipt())) {
            throw new DuplicateResourceException("ORDER_RECEIPT_ALREADY_EXISTS","Order with receipt already exists: " + request.receipt());
        }
        OrderRecord order = OrderRecord.builder()
                .receipt(request.receipt())
                .amount(request.amount())
                .notes(request.notes())
                .merchantId(merchantId)
                .orderStatus(OrderStatus.CREATED)
                .expireAt(request.expireAt() != null ? request.expireAt() : LocalDateTime.now().plusMinutes(defaultExpireInMinutes))
                .build();
        order = orderRepository.save(order);

        // send kafaka event that order is created
        return new OrderResponce(
                order.getId(),
                order.getMerchantId().toString(),
                order.getReceipt(),
                order.getAmount(),

                order.getOrderStatus(),
                order.getAttempts(),
                order.getNotes(),
                order.getExpireAt(),
               null
        );
    }
}
