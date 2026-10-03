package com.example.PayFlow.common.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Money {

    private Double amountUnits;
    private String currency;

    public Money add(Money other)  {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                    "Cannot add money with different currencies"
            );
        }

        return new Money(
                this.amountUnits + other.amountUnits,
                this.currency
        );
    }

    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                    "Cannot subtract money with different currencies"
            );
        }

        return new Money(
                this.amountUnits - other.amountUnits,
                this.currency
        );
    }

    public static Money of(Double amountUnits) {
        return new Money(amountUnits, "INR");
    }

    public static Money inr(Double amountUnits) {
        return new Money(amountUnits, "INR");
    }
}