package com.example.PayFlow.merchant.dto.request;

import com.example.PayFlow.common.enums.Enviroment;

public record  CreateApiKeyRequest (
        Enviroment enviroment
) {
}
