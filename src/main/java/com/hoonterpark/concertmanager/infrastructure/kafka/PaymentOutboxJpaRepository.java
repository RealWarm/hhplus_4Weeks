package com.hoonterpark.concertmanager.infrastructure.kafka;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentOutboxJpaRepository extends JpaRepository<PaymentOutboxEvent, Long> {
    List<PaymentOutboxEvent> findAllByStatus(String status);
    Optional<PaymentOutboxEvent> findByAggregateId(Long aggregateId);
}
