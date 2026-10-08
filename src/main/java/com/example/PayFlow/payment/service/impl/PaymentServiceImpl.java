package com.example.PayFlow.payment.service.impl;

import com.example.PayFlow.common.enums.OrderStatus;
import com.example.PayFlow.common.enums.PaymentStatus;
import com.example.PayFlow.common.exception.BussinessRulesViolationException;
import com.example.PayFlow.common.exception.ResourceNotFoundException;
import com.example.PayFlow.payment.dto.request.PaymentInitRequest;
import com.example.PayFlow.payment.dto.response.PaymentResponse;
import com.example.PayFlow.payment.entity.OrderRecord;
import com.example.PayFlow.payment.entity.Payment;
import com.example.PayFlow.payment.gateway.PaymentGatewayRouter;
import com.example.PayFlow.payment.gateway.dto.PaymentRequest;
import com.example.PayFlow.payment.gateway.dto.PaymentResult;
import com.example.PayFlow.payment.mapper.PaymentMapper;
import com.example.PayFlow.payment.repository.OrderRepository;
import com.example.PayFlow.payment.repository.PaymentRepository;
import com.example.PayFlow.payment.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayRouter paymentGatewayRouter;
    private final PaymentMapper paymentMapper;
    @Transactional
    @Override
    public PaymentResponse initiate(UUID merchantId, PaymentInitRequest request) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(request.orderId(),merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for the given orderId and merchantId",request.orderId()));

        if(order.getOrderStatus()  != OrderStatus.ATTEMPTED && order.getOrderStatus() != OrderStatus.CREATED){
            throw new BussinessRulesViolationException("Order not Payable","order cannot accepy paymet in status : " + order.getOrderStatus());
        }

        order.setOrderStatus(OrderStatus.ATTEMPTED);
        order.setAttempts(order.getAttempts() + 1);

        Payment payment = Payment.builder()
                .order(order)
                .merchantId(merchantId)
                .amount(order.getAmount())
                .status(PaymentStatus.CREATED)
                .method(request.method())
                .methodDetails(request.methodDetails())
                .build();
        payment = paymentRepository.save(payment);

        PaymentRequest paymentRequest = new PaymentRequest(
                payment.getId(),
                request.orderId(),
                merchantId,
                order.getAmount(),
                request.method(),
                request.methodDetails()
        );

        PaymentResult result = paymentGatewayRouter.initiate(paymentRequest);
        // Implement payment initiation logic here
        // below if case you can swutch with swatch case with time 1:51 class 10
        if(result instanceof PaymentResult.Pending pending){
          payment.setProcessorReference(pending.registrationRef());
        }else if(result instanceof PaymentResult.Failure failure){
             payment.setStatus(PaymentStatus.FAILED);
             payment.setErrorCode(failure.errorCode());
             payment.setErrorDescription(failure.errorDescription());
        }
        payment = paymentRepository.save(payment);
        orderRepository.save(order);

        return paymentMapper.toResponse(payment);
    }
}
