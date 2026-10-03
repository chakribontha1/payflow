package com.example.PayFlow.merchant.service;

import com.example.PayFlow.merchant.dto.request.CreateApiKeyRequest;
import com.example.PayFlow.merchant.dto.response.ApiKeyCreateResponse;
import com.example.PayFlow.merchant.dto.response.ApiKeyResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService  {
    ApiKeyCreateResponse create(UUID merchantId, @Valid CreateApiKeyRequest request);

    List<ApiKeyResponse> listByMerchant(UUID merchantId);

    void revoke(UUID merchantId, UUID apiKeyId);

    ApiKeyCreateResponse rotateKey(UUID merchantId, UUID apiKeyId);
}
