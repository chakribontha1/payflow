package com.example.PayFlow.merchant.entity;

import com.example.PayFlow.common.enums.Enviroment;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "api_key",indexes = {

        @Index(name = "idx_api_key_merchant_env", columnList = "merchant_id, enironment, enabled")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false,length = 200,unique = true)
    private String keyid;

    @Column(nullable = false,length = 200)
    private String keySecretHash;

    @Column(length = 200)
    private String previoskeySecretHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 10)
    private Enviroment enironment;

    @Column(nullable = false,length = 20)
    @Builder.Default
    private boolean enabled = true;
    private java.time.LocalDateTime lastUsedAt;
    private java.time.LocalDateTime rotatedAt;
    private java.time.LocalDateTime gracePeriodExpiredAt;
}
