package com.example.PayFlow.payment.processor.dto.responce;

public sealed interface PaymentProcessorResponce permits PaymentProcessorResponce.Pending, PaymentProcessorResponce.Success, PaymentProcessorResponce.Failed {
    record Pending(String processorReference) implements PaymentProcessorResponce{}
    record Success(String processorReference,String bankReference) implements PaymentProcessorResponce{}
    record Failed(String errorCode,String errorDescription) implements PaymentProcessorResponce{}
}
