package com.example.PayFlow.payment.gateway.dto;

public sealed interface PaymentResult permits  PaymentResult.Pending,PaymentResult.Failure{

    record Failure(String errorCode, String errorDescription) implements PaymentResult {}

    record Pending(String registrationRef) implements PaymentResult {}
}
