package com.example.PayFlow.payment.config;

import com.example.PayFlow.common.enums.PaymentMethod;
import com.example.PayFlow.payment.gateway.Adapter.CardPaymentAdapter;
import com.example.PayFlow.payment.gateway.Adapter.NetBankingAdapter;
import com.example.PayFlow.payment.gateway.Adapter.UpiPaymentAdapter;
import com.example.PayFlow.payment.gateway.PaymentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentAdapterConfig {
    @Bean
    public Map<PaymentMethod, PaymentAdapter> paymentAdapterMap() {
        return Map.of(
                PaymentMethod.CARD, new CardPaymentAdapter(),
                PaymentMethod.UPI, new UpiPaymentAdapter(),
                PaymentMethod.NETBANKING,new NetBankingAdapter()
        );
    }
}
