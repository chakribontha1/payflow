package com.example.PayFlow.payment.config;

import com.example.PayFlow.common.enums.PaymentMethod;
import com.example.PayFlow.payment.processor.PaymentProcessor;
import com.example.PayFlow.payment.processor.strategy.CardPaymentProcessor;
import com.example.PayFlow.payment.processor.strategy.NetBankingPaymentProcessor;
import com.example.PayFlow.payment.processor.strategy.UpiPaymentProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentProcesserConfig {
    Map<PaymentMethod, PaymentProcessor> paymentProcessorsMap(){
        return Map.of(
                PaymentMethod.CARD, new CardPaymentProcessor(),
                PaymentMethod.UPI, new UpiPaymentProcessor(),
                PaymentMethod.NETBANKING, new NetBankingPaymentProcessor()
        );
    }
}
