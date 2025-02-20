package com.hoonterpark.concertmanager.domain.kafka;



import com.hoonterpark.concertmanager.infrastructure.kafka.PaymentOutboxEvent;

import java.util.List;

public interface PaymentMessageOutboxRepository {
    public PaymentOutboxEvent save(PaymentOutboxEvent message);
    public PaymentOutboxEvent findById(Long id);
    public List<PaymentOutboxEvent> findByStatus(String status);
    public PaymentOutboxEvent findByAggregateId(Long aggregateId);
    public void delete(PaymentOutboxEvent message);
}
