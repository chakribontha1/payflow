package com.example.PayFlow.operations.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.SQLType;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "dlq_event")
public class DLQevent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private UUID marchantId;

    @Column(length = 1000)
    private String finalError;

    @OneToOne(fetch = FetchType.LAZY)
    private WebhookEvent webhookEvent;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false,columnDefinition = "jsonb")
    private Map<String,Object> payload;

    private LocalDateTime movedAt;

    private LocalDateTime replayedAt;


}
