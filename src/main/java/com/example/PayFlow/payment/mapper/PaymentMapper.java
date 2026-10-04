package com.example.PayFlow.payment.mapper;

import com.example.PayFlow.common.enums.PaymentMethod;
import com.example.PayFlow.payment.dto.response.PaymentResponse;
import com.example.PayFlow.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    @Mapping(target = "orderId", source = "order.Id")
//    @Mapping(target = "merchantId", source = "merchantId")
    PaymentResponse toResponse(Payment payment);

    List<PaymentResponse> toResponseList(List<Payment> paymentList);
}
