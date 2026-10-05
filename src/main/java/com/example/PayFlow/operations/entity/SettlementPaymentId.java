package com.example.PayFlow.operations.entity;

import com.example.PayFlow.common.entity.BaseEntity;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SettlementPaymentId  extends BaseEntity {
    private UUID settlementId;
    private UUID paymentId;
}
