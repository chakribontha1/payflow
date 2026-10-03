package com.example.PayFlow.merchant.dto.response;

import com.example.PayFlow.common.enums.Enviroment;

import java.util.UUID;

public record ApiKeyCreateResponse (
        UUID id,
        String keyId,
        String keySecret,
        Enviroment enviroment
) {
}
