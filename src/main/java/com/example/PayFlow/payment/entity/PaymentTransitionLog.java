package com.example.PayFlow.payment.entity;

import com.example.PayFlow.common.entity.BaseEntity;
import com.example.PayFlow.common.enums.PaymentActor;
import com.example.PayFlow.common.enums.PaymentEvent;
import com.example.PayFlow.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payment_transition_log",indexes = {
        @Index(name = "idx_payment_transition_log_payment_id", columnList = "payment_id"),

})
public class PaymentTransitionLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_status",length = 30)
    private PaymentStatus fromstatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "event",nullable = false,length = 30)
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status",length = 30)
    private PaymentStatus tostatus;

    @Column(name = "actor",length = 100)
    @Enumerated(EnumType.STRING)
    private PaymentActor actor;

    @Column(name = "occurred_at",nullable = false)
    private LocalDateTime occurredAt;





}
