package com.example.PayFlow.merchant.mapper;

import com.example.PayFlow.merchant.dto.request.MerchantSignupRequest;
import com.example.PayFlow.merchant.dto.response.MerchantResponse;
import com.example.PayFlow.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {
    Merchant toEntityFromSignupRequest(MerchantSignupRequest request);
    MerchantResponse toResponse(Merchant merchant);
}
