package com.example.PayFlow.merchant.dto.response;

import com.example.PayFlow.common.enums.BusinessType;
import com.example.PayFlow.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse(
        UUID id,
        String name,
        String email,
        String businessname,
        BusinessType businessType,
        MerchantStatus merchantStatus
) {
}

