package com.hoonterpark.concertmanager.infrastructure.kafka;

import com.hoonterpark.concertmanager.domain.kafka.PaymentMessageOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentMessageOutboxRepositoryImpl implements PaymentMessageOutboxRepository {
    private final PaymentOutboxJpaRepository paymentOutboxJpaRepository;


    @Override
    public PaymentOutboxEvent save(PaymentOutboxEvent message) {
        return paymentOutboxJpaRepository.save(message);
    }

    @Override
    public PaymentOutboxEvent findById(Long id) {
        return paymentOutboxJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<PaymentOutboxEvent> findByStatus(String status) {
        return paymentOutboxJpaRepository.findAllByStatus(status);
    }

    @Override
    public PaymentOutboxEvent findByAggregateId(Long aggregateId) {
        return paymentOutboxJpaRepository.findByAggregateId(aggregateId).orElseThrow(() -> new RuntimeException("발행 내역이 없습니다."));
    }

    @Override
    public void delete(PaymentOutboxEvent message) {
        paymentOutboxJpaRepository.delete(message);
    }

}
