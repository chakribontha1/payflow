package com.example.PayFlow.merchant.entity;

import com.example.PayFlow.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "merchant_webhook_config",indexes = {
        @Index(name = "idx_merchant_webhook_config_merchant_id", columnList = "merchant_id,enabled")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantWebhhokConfig extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false,length = 500)
    private String TargetUrl;

    @Column(length = 255)
    private String webhookSecretHash;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(length = 500)
    private String eventTypes;
    // comma-separated list of event types that the merchant wants to receive webhooks for, e.g., "PAYMENT_SUCCEEDED,PAYMENT_FAILED"



}
