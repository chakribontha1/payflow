package com.example.PayFlow.merchant.mapper;

import com.example.PayFlow.merchant.dto.response.ApiKeyCreateResponse;
import com.example.PayFlow.merchant.dto.response.ApiKeyResponse;
import com.example.PayFlow.merchant.entity.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {

    ApiKeyCreateResponse toApiKeyCreateResponse(ApiKey apiKey);
    List<ApiKeyResponse> toResponseList(List<ApiKey> apiKeyList);
}
