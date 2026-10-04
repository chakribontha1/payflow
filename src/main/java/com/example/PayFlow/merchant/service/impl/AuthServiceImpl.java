package com.example.PayFlow.merchant.service.impl;

import com.example.PayFlow.common.enums.MerchantStatus;
import com.example.PayFlow.common.enums.UserRole;
import com.example.PayFlow.common.exception.DuplicateResourceException;
import com.example.PayFlow.merchant.dto.request.MerchantSignupRequest;
import com.example.PayFlow.merchant.dto.response.MerchantResponse;
import com.example.PayFlow.merchant.entity.AppUser;
import com.example.PayFlow.merchant.entity.Merchant;
import com.example.PayFlow.merchant.mapper.MerchantMapper;
import com.example.PayFlow.merchant.repository.AppUserRepository;
import com.example.PayFlow.merchant.repository.MerchantRepository;
import com.example.PayFlow.merchant.service.AuthService;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;
    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL","Merchant email already exits: "+ request.email());
        }
        Merchant merchant = merchantMapper.toEntityFromSignupRequest(request);
        merchant.setStatus(MerchantStatus.PENDING_KYC);
        merchant = merchantRepository.save(merchant);


        AppUser appUser = AppUser.builder()
                        .email(request.email())
                        .merchant(merchant)
                        .passwordHash(request.password()) // TODO:enrypt
                        .role(UserRole.OWNER)
                        .build();
          appUserRepository.save(appUser);

        return merchantMapper.toResponse(merchant);
    }
}
