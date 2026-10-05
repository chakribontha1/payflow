package com.example.PayFlow.payment.controller;

import com.example.PayFlow.payment.dto.request.PaymentInitRequest;
import com.example.PayFlow.payment.dto.response.PaymentResponse;
import com.example.PayFlow.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    UUID merchantId = UUID.fromString("c5306ed1-8afe-4663-b65c-37608a2e1001"); // this later will come from tokaen
    @PostMapping
    public ResponseEntity<PaymentResponse> initiate(@RequestBody PaymentInitRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.initiate(merchantId, request));
    }
}
