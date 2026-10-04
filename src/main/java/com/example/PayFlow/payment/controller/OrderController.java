package com.example.PayFlow.payment.controller;

import com.example.PayFlow.payment.dto.request.CreateOrderRequest;
import com.example.PayFlow.payment.dto.response.OrderResponce;
import com.example.PayFlow.payment.service.OrderService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    UUID merchantId = UUID.fromString("c5306ed1-8afe-4663-b65c-37608a2e1001"); // todo : Replace with merchant context
    @PostMapping
    private ResponseEntity<OrderResponce> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return ResponseEntity.status((HttpStatus.CREATED))
                .body(orderService.create(merchantId, request));
    }
}
