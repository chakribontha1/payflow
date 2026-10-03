package com.example.PayFlow.merchant.service;

import com.example.PayFlow.merchant.dto.request.MerchantSignupRequest;
import com.example.PayFlow.merchant.dto.response.MerchantResponse;

public interface AuthService {
     MerchantResponse signup(MerchantSignupRequest request);
}
