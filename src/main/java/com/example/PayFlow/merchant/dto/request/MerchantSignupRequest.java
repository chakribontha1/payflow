package com.example.PayFlow.merchant.dto.request;

import com.example.PayFlow.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest (

        @NotNull(message = "Name should be provided")
        @Size(max = 50, message = "name should not be more tah 50 character long")
        String name,

        @Email
        @NotNull(message = "email is require")
        String email,

        @NotNull(message = "password is required")
        @Size(min = 8, message = "password should be atlest 8 charcters long")
        String password,

        @Size(max = 50, message = "business name should not be more than 50 charaters")
        String businessName,
        BusinessType businessType
) {

}
