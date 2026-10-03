package com.example.PayFlow.merchant.repository;

import com.example.PayFlow.merchant.dto.response.ApiKeyResponse;
import com.example.PayFlow.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {
    List<ApiKey> findByMerchant_id(UUID merchantId);
}
