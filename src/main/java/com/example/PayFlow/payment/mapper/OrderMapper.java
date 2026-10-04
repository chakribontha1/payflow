package com.example.PayFlow.payment.mapper;

import com.example.PayFlow.payment.dto.response.OrderResponce;
import com.example.PayFlow.payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
   OrderResponce toResponse(OrderRecord orderRecord);
}
