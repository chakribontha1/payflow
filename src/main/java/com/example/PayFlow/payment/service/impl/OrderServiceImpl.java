package com.example.PayFlow.payment.service.impl;

import com.example.PayFlow.common.enums.OrderStatus;
import com.example.PayFlow.common.exception.BussinessRulesViolationException;
import com.example.PayFlow.common.exception.DuplicateResourceException;
import com.example.PayFlow.common.exception.ResourceNotFoundException;
import com.example.PayFlow.payment.dto.request.CreateOrderRequest;
import com.example.PayFlow.payment.dto.response.OrderResponce;
import com.example.PayFlow.payment.dto.response.PaymentResponse;
import com.example.PayFlow.payment.entity.OrderRecord;
import com.example.PayFlow.payment.entity.Payment;
import com.example.PayFlow.payment.mapper.OrderMapper;
import com.example.PayFlow.payment.mapper.PaymentMapper;
import com.example.PayFlow.payment.repository.OrderRepository;
import com.example.PayFlow.payment.repository.PaymentRepository;
import com.example.PayFlow.payment.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;

    private final PaymentMapper paymentMapper;

    private final OrderMapper orderMapper;

    @Value("${payment.order.default-order-expiry-minutes:30}")
    private int defaultExpireInMinutes;

    @Override
    public OrderResponce create(UUID merchantId, CreateOrderRequest request) {
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
//        return new OrderResponce(
//                order.getId(),
//                order.getMerchantId().toString(),
//                order.getReceipt(),
//                order.getAmount(),
//
//                order.getOrderStatus(),
//                order.getAttempts(),
//                order.getNotes(),
//                order.getExpireAt(),
//               null
//        );

        return orderMapper.toResponse(order);

    }

    @Override
    public OrderResponce getById(UUID orderId, UUID merchantId) {
        OrderRecord orderRecord =  orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("ORDER_NOT_FOUND", orderId));

        return orderMapper.toResponse(orderRecord);
    }


    @Override
    @Transactional
    public OrderResponce cancel(UUID orderId, UUID merchantId) {
        OrderRecord order =  orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("ORDER_NOT_FOUND", orderId));
        if(order.getOrderStatus() == OrderStatus.CANCELED || order.getOrderStatus() == OrderStatus.PAID){
            throw new BussinessRulesViolationException("ORDER_CANNOT_BE_CANCELED", order.getOrderStatus().name());
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        order = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

    @Override
    public List<PaymentResponse> listPayments(UUID orderId, UUID merchantId) {
        OrderRecord order =  orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("ORDER_NOT_FOUND", orderId));

        List<Payment> paymentList = paymentRepository.findByOrder(order);


//        return paymentList.stream().map(
//                payment -> paymentMapper.toResponse(payment)
//        ).collect(Collectors.toList());
        return paymentMapper.toResponseList(paymentList);

    }
}
