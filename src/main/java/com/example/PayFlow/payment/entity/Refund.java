package com.example.PayFlow.payment.entity;

import com.example.PayFlow.common.entity.Money;
import com.example.PayFlow.common.enums.RefundStatus;
import com.example.PayFlow.merchant.entity.Merchant;
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
@Table(name = "refund")
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment paymentId;

    @Column(nullable = false)
    private UUID merchant;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
   private RefundStatus refundStatus = RefundStatus.PENDING;

    @Column(length = 100)
   private String bankRefernce;

    @Column(length = 100)
   private String errorCode;

    @Column(length = 500)
   private String errorDescription;

   @JdbcTypeCode(SqlTypes.JSON)
   @Column(columnDefinition = "jsonb")
   private Map<String,Object> notes;
   private LocalDateTime processedAt;


}
