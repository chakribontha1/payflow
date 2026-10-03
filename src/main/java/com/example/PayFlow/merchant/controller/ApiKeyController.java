package com.example.PayFlow.merchant.controller;

import com.example.PayFlow.merchant.dto.request.CreateApiKeyRequest;
import com.example.PayFlow.merchant.dto.response.ApiKeyCreateResponse;
import com.example.PayFlow.merchant.dto.response.ApiKeyResponse;
import com.example.PayFlow.merchant.service.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/merchants/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {
 private final ApiKeyService apiKeyService;

  @PostMapping
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId,
                                                       @Valid @RequestBody CreateApiKeyRequest request){
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(apiKeyService.create(merchantId,request));
  }

   @GetMapping
    public ResponseEntity<List<ApiKeyResponse>> listByMerchant(@PathVariable UUID merchantId){
     return ResponseEntity.ok(apiKeyService.listByMerchant(merchantId));
    }

//    @DeleteMapping("/keyId")
//    public ResponseEntity<Void> revoke(@PathVariable UUID merchantId,@PathVariable UUID apiKeyId){
//        apiKeyService.revoke(merchantId,apiKeyId);
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/{apiKeyId}")
    public ResponseEntity<Void> revoke(
            @PathVariable("merchantId") UUID merchantId,
            @PathVariable("apiKeyId") UUID apiKeyId) {

        apiKeyService.revoke(merchantId, apiKeyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{apiKeyId}/rotate")
    public ResponseEntity<ApiKeyCreateResponse> rotateKey(@PathVariable UUID merchantId,@PathVariable UUID apiKeyId){
       return ResponseEntity.ok(apiKeyService.rotateKey(merchantId,apiKeyId));
    }


}
