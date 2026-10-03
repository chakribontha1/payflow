package com.example.PayFlow.merchant.service.impl;

import com.example.PayFlow.common.exception.ResourceNotFoundException;
import com.example.PayFlow.common.util.RandomUtil;
import com.example.PayFlow.merchant.dto.request.CreateApiKeyRequest;
import com.example.PayFlow.merchant.dto.response.ApiKeyCreateResponse;
import com.example.PayFlow.merchant.dto.response.ApiKeyResponse;
import com.example.PayFlow.merchant.entity.ApiKey;
import com.example.PayFlow.merchant.entity.Merchant;
import com.example.PayFlow.merchant.repository.ApiKeyRepository;
import com.example.PayFlow.merchant.repository.MerchantRepository;
import com.example.PayFlow.merchant.service.ApiKeyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {
    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;
    @Transactional
    @Override
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(()->new ResourceNotFoundException("Merchant not found",merchantId));

       String keyId = "rzp_" + request.enviroment().name().toLowerCase() +" " + RandomUtil.randomBase64(24);
       String rawSecret = RandomUtil.randomBase64(40);

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyid(keyId)
                .keySecretHash(rawSecret)
                .enironment(request.enviroment())
                .build();
         apiKey = apiKeyRepository.save(apiKey);


       return new ApiKeyCreateResponse(apiKey.getId(),keyId,rawSecret,request.enviroment());

    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyRepository.findByMerchant_id(merchantId).stream()
                .map(apiKey -> new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getKeyid(),
                        apiKey.getEnironment(),
                        apiKey.isEnabled(),
                        apiKey.getLastUsedAt(),
                        null))
                .toList();
    }
    @Transactional
    @Override
    public void revoke(UUID merchantId, UUID apiKeyId) {
        ApiKey key = apiKeyRepository.findById(apiKeyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found",apiKeyId));
        key.setEnabled(false);
        apiKeyRepository.save(key);
    }

    @Override
    @Transactional
    public ApiKeyCreateResponse rotateKey(UUID merchantId, UUID apiKeyId) {
        ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found",apiKeyId));
        String newRawSecret = RandomUtil.randomBase64(40);
        apiKey.setPrevioskeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret); //TODO
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiredAt(LocalDateTime.now().plusHours(24));
        apiKey = apiKeyRepository.save(apiKey);
        return new ApiKeyCreateResponse(apiKey.getId(), apiKey.getKeyid(), newRawSecret,apiKey.getEnironment());
    }
}
