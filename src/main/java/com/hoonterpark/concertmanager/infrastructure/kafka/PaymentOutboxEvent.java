package com.hoonterpark.concertmanager.infrastructure.kafka;

import com.hoonterpark.concertmanager.domain.enums.PaymentOutBoxEventStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment_outbox")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentOutboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String aggregateType;

    @Column(nullable = false)
    private Long aggregateId;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private String status = PaymentOutBoxEventStatus.INIT.name();

    @Column(nullable = false)
    private Integer retryCount = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public PaymentOutboxEvent(String aggregateType, Long aggregateId, String eventType) {
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
    }
}
