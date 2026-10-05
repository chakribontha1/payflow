package com.example.PayFlow.payment.entity;

import com.example.PayFlow.common.entity.BaseEntity;
import com.example.PayFlow.common.entity.Money;
import com.example.PayFlow.common.enums.PaymentMethod;
import com.example.PayFlow.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payment",indexes = {
        @Index(name = "idx_order_id_merchant_id", columnList = "order_id,merchant_id"),
        @Index(name = "idx_order_merchant_id", columnList = "merchant_id"),

})
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderRecord order;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Column(nullable = false,length = 100)
    private String idempotencyKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 30)
    private PaymentStatus status;

    @Column(nullable = false)
    private PaymentMethod method;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> methodDetails;

    @Column(length = 100)
    private String bankReference;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 255)
    private String errorDescription;


    private LocalDateTime authorizedAt;

    private LocalDateTime capturedAt;
    private LocalDateTime failedAt;
    private LocalDateTime refundedAt;


}
